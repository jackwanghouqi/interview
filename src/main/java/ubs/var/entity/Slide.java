package ubs.var.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Slide {
	String riskFactorClass;
	Map<Integer, String> FoRiskFactors = new LinkedHashMap<>();
	
	Map<Double, Double> shockAndValues = new LinkedHashMap<>();
	
	TimeSeries tsAfterMapping;
	
	Map<Integer, Double> sysPnLVector = new LinkedHashMap<>();
	
	// slide type for getting the InterpolationType
	String slideType;
	
	public Slide(String riskFactorClass, Map<Double, Double> shockAndValues, String... rfs) {
		super();
		int i=1;
		for(String rf:rfs) {
			FoRiskFactors.put(i++, rf);
		}
		this.riskFactorClass = riskFactorClass;
		this.shockAndValues = shockAndValues;
	}
	
	
	public String getRiskFactorClass() {
		return riskFactorClass;
	}

	public Map<Integer, String> getFoRiskFactors() {
		return FoRiskFactors;
	}
	
	public Map<Double, Double> getShockAndValues() {
		return shockAndValues;
	}
	
	public double[] getShocks() {
		Set<Double> keySet = shockAndValues.keySet();
		double[] shocks = new double[keySet.size()];
		int i =0;
		for (double d : keySet) {
			shocks[i++] = d;
		}
		return shocks;
	}
	
	public double[] getValues() {
		Collection<Double> values1 = shockAndValues.values();
		double[] values = new double[values1.size()];
		int i =0;
		for (double d : values1) {
			values[i++] = d;
		}
		return values;
	}
	
	public void setShockAndValues(Map<Double, Double> shockAndValues) {
		this.shockAndValues = shockAndValues;
	}

	public TimeSeries getTsAfterMapping() {
		return tsAfterMapping;
	}

	public void setTsAfterMapping(TimeSeries tsAfterMapping) {
		this.tsAfterMapping = tsAfterMapping;
	}
	
	public double getDelta() {
		return 0.002;
	}
	
	public double getETFLamda() {
		return 1.0;
	}

	public Map<Integer, Double> getSysPnLVector() {
		return sysPnLVector;
	}

	public void addSysPnl(Integer cob , Double pnl) {
		this.sysPnLVector.put(cob, pnl);
	}
	
	public List<Double> getSortedSysPnLVector() {
		Set<Double> set = new TreeSet<>();
		set.addAll(sysPnLVector.values());
		List<Double> list = new ArrayList<>();
		for (Double value : set) {
			list.add(value);
		}
		return list;
	}

	@Override
	public String toString() {
		return "Slide [riskFactorClass=" + riskFactorClass + ", FoRiskFactors=" + FoRiskFactors + "]";
	}
	
	
}
