package ubs.var.calc;

public class Extrapolation {

	public static double valueAt(ExtrapolationType type, double[] shocks, double[] values, double currShock, int index) {
		double pnlValue = 0;
		int leftIndex = 0;
		int rightIndex = 0;
		switch(type) {
		case FLAT_EXTRAPOLATION : 
			pnlValue = values[index];
			break;
		case LINEAR_EXTRAPOLATION : 
			if(index==0) {
				leftIndex = index;
				rightIndex = index + 1;
			} else {
				leftIndex = index - 1;
				rightIndex = index;
			}
			pnlValue = values[leftIndex] + ((currShock - shocks[leftIndex])/(shocks[rightIndex] - shocks[leftIndex])) * (values[rightIndex] - values[leftIndex]);
			break;
		case LOGNORMAL_EXTRAPOLATION : 
			currShock = Math.exp(currShock)-1;
			if(index==0) {
				leftIndex = index;
				rightIndex = index + 1;
			} else {
				leftIndex = index - 1;
				rightIndex = index;
			}
			double leftShock = Math.exp(shocks[leftIndex])-1;
			double rightShock = Math.exp(shocks[rightIndex])-1;
			pnlValue = values[leftIndex] + ((currShock - leftShock)/(rightShock - leftShock)) * (values[rightIndex] - values[leftIndex]);
			break;
		case ZERO_EXTRAPOLATION :
			pnlValue = 0;
			break;
		default : 
			throw new IllegalArgumentException("Unkown extrapolation type : " + type);
		}
		return pnlValue;
	}

	public static enum ExtrapolationType {
		FLAT_EXTRAPOLATION,LINEAR_EXTRAPOLATION,HYBRID_EXTRAPOLATION,LOGNORMAL_EXTRAPOLATION,ZERO_EXTRAPOLATION
	}
}
