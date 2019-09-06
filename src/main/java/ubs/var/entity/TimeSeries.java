package ubs.var.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Vector;

public class TimeSeries {
	RiskFactorsHeaderKey riskFactorsHeaderKey;

	public TimeSeries(String riskFactorClass, String... riskfactors) {
		StringJoiner sj = new StringJoiner("/");
		for (String riskfactor : riskfactors) {
			sj.add(riskfactor);
		}
		riskFactorsHeaderKey = new RiskFactorsHeaderKey(riskFactorClass, sj.toString());
	}

	private Vector<Integer> tsCob = new Vector<>();

	Map<Integer, Double> timeSeriesVector = new LinkedHashMap<>();

	public void put(int cob, double shock) {
		tsCob.add(cob);
		timeSeriesVector.put(cob, shock);
	}

	public Map<Integer, Double> fetch() {
		return timeSeriesVector;
	}

	public String getName() {
		return riskFactorsHeaderKey.toString();
	}
	
}