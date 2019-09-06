package ubs.var;

import ubs.var.calc.Extrapolation;
import ubs.var.calc.Interpolation;
import ubs.var.entity.*;
import ubs.var.util.PrintUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * t		--	time, measured in units of days
 * h		--	forecast horizon
 *
 * Vt		--	value of a trade or a portfolio of trades at time t
 * ΔVt(h)	--	profit and loss of the portfolio over the time interval [t, t+h]
 *
 *
 * Lt(h)	--	loss of the portfolio over the time interval [t, t+h]    = -ΔVt(h)
 * Zt		--	Risk Factor value at time t. Zt=(Z1t, Z2t, Z3t, ... Zmt)   ===> m is number of risk factors
 *
 * f(z,t)	--	Valuation function of a trade at time t.  z = (z1, z2, ..., zm)
 *
 * δ		--	First order sensitivity (delta)
 * γ		--	Second order sensitivity (gamma)
 *
 *
 * VaR[α](L)-- 	Value-at-Risk of random variable L at confidence level α.  Eg. VaR 99% (USD 10day)
 * ETL[α](L)--	Expected Tail Loss of random variable L at confidence level α. Eg. ETL 99% (USD 10day)
 *
 *
 *
 * */
public class VaREngine {



	public static void main(String[] args) {
		//Request cob date
		int cobdate = CentralDB.MOCK_COB_DATE;

		//Request 10 day 99% USD (Suppose we don't have FX conversion issue, meaning we don't need to worry about currency. both trade currency and report currency are same)
		HoldingPeriods holdingPeriods = HoldingPeriods.P10D;
		float quantity = 0.99F;


		calculateSystematicVaR(cobdate, holdingPeriods, quantity);
	}

	public static void calculateSystematicVaR(int cobdate, HoldingPeriods holdingPeriods, float quantity) {
		//1. fetch the book which we want to calculate from central db
		List<Book> books = CentralDB.getBooks(cobdate);

		//2. map the slide to the time series from RiskFactor db
		for (Iterator<Book> iterator = books.iterator(); iterator.hasNext();) {
			Book book = iterator.next();
			List<Slide> slides = book.getSlides();
			for (Slide slide : slides) {
				String riskFactorClass = slide.getRiskFactorClass();
				Map<Integer, String> foRiskFactors = slide.getFoRiskFactors();
				RiskFactorsHeaderKey key = new RiskFactorsHeaderKey(riskFactorClass, foRiskFactors);
				TimeSeries timeSeries = RiskFactorDB.getTimeSeries(key, holdingPeriods);
				slide.setTsAfterMapping(timeSeries);
			}
		}
		//3. Interpolation with given ts shock to pnl
		for (Iterator<Book> iterator = books.iterator(); iterator.hasNext();) {
			Book book = iterator.next();
			List<Slide> slides = book.getSlides();
			for (Slide slide : slides) {
				TimeSeries timeSeries = slide.getTsAfterMapping();
				Map<Double, Double> shockAndValues = slide.getShockAndValues();
				double[] shocks = slide.getShocks();
				double[] values = slide.getValues();
				double[] buffer = new double[shockAndValues.size()];
				shockAndValues.keySet();
				int count = shockAndValues.size();
				double delta = slide.getDelta();
				double etfLamda = slide.getETFLamda();
				double cDelta = Interpolation.valueAt(shocks, values, buffer , count , delta, etfLamda, Interpolation.InterpolationType.POLYNOMIAL_INTERPOLARION, Extrapolation.ExtrapolationType.LINEAR_EXTRAPOLATION);
				Map<Integer, Double> timeseriesVector = timeSeries.fetch();
				for (Integer cob : timeseriesVector.keySet()) {
					Double timeseriesValue = timeseriesVector.get(cob);
					slide.addSysPnl(cob, cDelta*timeseriesValue);
				}
			}
		}

		//4. get 99% quantity index and value;
		for (Iterator<Book> iterator = books.iterator(); iterator.hasNext();) {
			Book book = iterator.next();
			List<Slide> slides = book.getSlides();
			for (Slide slide : slides) {
				List<Double> vector = slide.getSortedSysPnLVector();
				int size = vector.size();
				int id = Math.round(quantity*(size-1));
				Double result = vector.get(id);
				//ETL is just the average of that 1% scope
				PrintUtils.print(slide,holdingPeriods, quantity, result);
			}
		}
	}

}
