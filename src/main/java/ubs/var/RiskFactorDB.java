package ubs.var;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ubs.var.entity.HoldingPeriods;
import ubs.var.entity.RiskFactorsHeaderKey;
import ubs.var.entity.TimeSeries;
import ubs.var.util.PrintUtils;

/**
 * Phoenix DB
 * 
 * Historical risk factor shock timeseries db.
 * Stores all the risk factor change history for a given holding period (eg 1D or 10D)
 * 
 * 
 * */
public class RiskFactorDB {
	
	public static final String MARK_DASH = "-";
	public static final String MARK_SLASH = "/";
	public static final String MRM_RISKFACTORCLASS_EQ_PRICE = "EQ_PRICE";
	public static final String MRM_RISKFACTORCLASS_CM_PRICE = "CM_PRICE";
	public static final String MRM_RISKFACTORCLASS_FX_PRICE = "FX_PRICE";
	public static final String MRM_RISKFACTORCLASS_IR_PRICE = "IR_PRICE";
	
	public static final String MOCK_RISKFACTORS_EQ_PRICE = "SOFTBANK/JPY/JP/XTKS/JP34361000061/140292/ORDINARY/EQ/TELECOMUNICATION SERVICES/DS1402923/6770620/JAPAN";
	public static final String MOCK_RISKFACTOR_CM_PRICE_1 = "GOLD";
	public static final String MOCK_RISKFACTOR_CM_PRICE_2 = "USD";
	public static final String MOCK_RISKFACTOR_CM_PRICE_3 = "10Year";
	public static final String MOCK_EQ_TS_NAME = MRM_RISKFACTORCLASS_EQ_PRICE + MARK_DASH + MOCK_RISKFACTORS_EQ_PRICE;
	public static final String MOCK_CM_TS_NAME = MRM_RISKFACTORCLASS_CM_PRICE + MARK_DASH + MOCK_RISKFACTOR_CM_PRICE_1+MARK_SLASH+MOCK_RISKFACTOR_CM_PRICE_2+MARK_SLASH+MOCK_RISKFACTOR_CM_PRICE_3;
	
	//Suppose this test, we only care about the change for 1 day and 10 day
	//That means we can only calculate the VaR for 1D quantity or 10D quantity.
	protected static Map<String, TimeSeries> phoenix_1D = new HashMap<>();
	protected static Map<String, TimeSeries> phoenix_10D = new HashMap<>();
	
	private static Random random = new Random();
	
	static {
		mockCM(MOCK_RISKFACTOR_CM_PRICE_1, MOCK_RISKFACTOR_CM_PRICE_2, MOCK_RISKFACTOR_CM_PRICE_3);
		mockEQ("SOFTBANK", 	//name
				"JPY", //currency
				"JP", 	//Stock exchange country
				"XTKS", // DTN exchange code
				"JP34361000061", //ISIN
				"140292", //unkown1
				"ORDINARY", //unkown2
				"EQ", //type
				"TELECOMUNICATION SERVICES", //Industry sector
				"DS1402923", //unkown3
				"6770620", //sedol
				"JAPAN");
	}
	
	
	
	public static void main(String[] args) {
		TimeSeries timeSeries = getTimeSeries(MOCK_EQ_TS_NAME, HoldingPeriods.P1D);
		TimeSeries timeSeries2 = getTimeSeries(MOCK_CM_TS_NAME, HoldingPeriods.P1D);
		PrintUtils.print(timeSeries.fetch());
		PrintUtils.print(timeSeries2.fetch());

	}
	
	public static TimeSeries getTimeSeries(String tsName, HoldingPeriods holdingPeriods) {
		switch(holdingPeriods) {
		case P1D:
			return phoenix_1D.get(tsName);
		case P10D:
			return phoenix_10D.get(tsName);
		default:
			return null;
		}
	}
	
	public static TimeSeries getTimeSeries(RiskFactorsHeaderKey key, HoldingPeriods holdingPeriods) {
		return getTimeSeries(key.toString(), holdingPeriods);
	}
	
	protected static void mockCM(String commodity, String currency, String expiredTenor) {
		TimeSeries riskFactorShockHistory = new TimeSeries(MRM_RISKFACTORCLASS_CM_PRICE, commodity,currency,expiredTenor);
		mockTimeSeriesData(riskFactorShockHistory);
		phoenix_1D.put(riskFactorShockHistory.getName(), riskFactorShockHistory);
		mockTimeSeriesData(riskFactorShockHistory);
		phoenix_10D.put(riskFactorShockHistory.getName(), riskFactorShockHistory);
		PrintUtils.warn("Mocked "+riskFactorShockHistory.getName());
	}
	
	protected static void mockEQ(String name, String currency, String isoCountry, String stockExchange, String isin, String unkown1, String unkown2, String type, String sector, String unkown3, String sedol, String country) {
		TimeSeries riskFactorShockHistory = new TimeSeries(MRM_RISKFACTORCLASS_EQ_PRICE, name,currency,isoCountry,stockExchange,isin,unkown1,unkown2,type,sector,unkown3,sedol,country);
		mockTimeSeriesData(riskFactorShockHistory);
		phoenix_1D.put(riskFactorShockHistory.getName(), riskFactorShockHistory);
		mockTimeSeriesData(riskFactorShockHistory);
		phoenix_10D.put(riskFactorShockHistory.getName(), riskFactorShockHistory);
		PrintUtils.warn("Mocked "+riskFactorShockHistory.getName());
	}
	
	//TODO
	protected static void mockIR(String todo) {
		TimeSeries riskFactorShockHistory = new TimeSeries("IR_PRICE", todo);
		mockTimeSeriesData(riskFactorShockHistory);
		phoenix_1D.put(riskFactorShockHistory.getName(), riskFactorShockHistory);
		mockTimeSeriesData(riskFactorShockHistory);
		phoenix_10D.put(riskFactorShockHistory.getName(), riskFactorShockHistory);
		PrintUtils.warn("Mocked "+riskFactorShockHistory.getName());
	}
	
	//TODO
	protected static void mockFX(String todo) {
		TimeSeries riskFactorShockHistory = new TimeSeries("FX_PRICE", todo);
		mockTimeSeriesData(riskFactorShockHistory);
		phoenix_1D.put(riskFactorShockHistory.getName(), riskFactorShockHistory);
		mockTimeSeriesData(riskFactorShockHistory);
		phoenix_10D.put(riskFactorShockHistory.getName(), riskFactorShockHistory);
		PrintUtils.warn("Mocked " + riskFactorShockHistory.getName());
	}


	private static void mockTimeSeriesData(TimeSeries riskFactorShockHistory) {
		// for a one year period, mock a vector of historical changes for a set of risk factors
		int startCob = 20170101;
		for (int cob = startCob; cob<(startCob+250); cob++) {
			//TODO find a way to exclude the holidays and weekends.
			//TODO this cob is not real cob date. it should be changed.
			riskFactorShockHistory.put(cob, random.nextDouble());
		}
	}
}
