package ubs.var.entity;

import java.util.Map;
import java.util.StringJoiner;

public class RiskFactorsHeaderKey {
	String riskFactorClass;
	String RiskFactorName;
	String tsname;
	
	public RiskFactorsHeaderKey(String riskFactorClass, String RiskFactorName) {
		this.riskFactorClass = riskFactorClass;
		this.RiskFactorName = RiskFactorName;
		tsname = riskFactorClass+"-"+RiskFactorName;
	}
	
	public RiskFactorsHeaderKey(String riskFactorClass, String... riskFactors) {
		StringJoiner sj = new StringJoiner("/");
		for (String riskfactor : riskFactors) {
			sj.add(riskfactor);
		}
		this.riskFactorClass = riskFactorClass;
		this.RiskFactorName = sj.toString();
		tsname = riskFactorClass+"-"+RiskFactorName;
	}
	
	public RiskFactorsHeaderKey(String riskFactorClass, Map<Integer, String> foRiskFactors) {
		
		StringJoiner sj = new StringJoiner("/");
		for (Integer key : foRiskFactors.keySet()) {
			String riskfactor = foRiskFactors.get(key);
			sj.add(riskfactor);
		}
		this.riskFactorClass = riskFactorClass;
		this.RiskFactorName = sj.toString();
		tsname = riskFactorClass+"-"+RiskFactorName;
	}
	
	@Override
	public String toString() {
		return tsname;
	}
}
