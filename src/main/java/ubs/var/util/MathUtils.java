package ubs.var.util;

import cern.jet.random.Normal;
import cern.jet.random.engine.MersenneTwister;

public class MathUtils {
	
	public static final int NUM_OF_DRAW = 100_000;

	public static void main(String[] args) {
		
	}
	
	public static double average(double[] n) {
		double sum = 0;
		double count = n.length;
		for (int i = 0; i < count; i++) {
			sum = sum + n[i];
		}
		return sum/count;
	}
	
	public static double sumSquare(double[] n) {
		if(n == null) return 0;
		double ret = 0;
		for (int i = n.length-1; i >=0 ; i--) {
			ret = ret + n[i]*n[i];
		}
		return ret;
	}
	
	public static double sumCube(double[] n) {
		if(n == null) return 0;
		double ret = 0;
		for (int i = n.length-1; i >=0 ; i--) {
			ret = ret + n[i]*n[i]*n[i];
		}
		return ret;
	}
	
	public static double sumQuartic(double[] n) {
		if(n == null) return 0;
		double ret = 0;
		for (int i = n.length-1; i >=0 ; i--) {
			ret = ret + n[i]*n[i]*n[i]*n[i];
		}
		return ret;
	}
	
	public static double standardDeviation(double[] n) {
		return standardDeviation(n, 0, n.length-1);
	}
	
	public static double standardDeviation(double[] n, int start, int end) {
		double mean =_average(n, start, end);
		return _standardDeviation(n, mean, start, end);
	}
	
	private static double _average(double[] n, int start, int end) {
		double sum = 0;
		for (int i = start; i <= end; i++) {
			sum = sum + n[i];
		}
		return sum/(end-start+1);
	}
	
	private static double _standardDeviation(double[] n, double mean, int start, int end) {
		double sum = 0;
		double count = end-start;
		for (int i = 0; i < end; i++) {
			double temp = n[i] - mean;
			sum = sum + (temp * temp);
		}
		return Math.sqrt(sum/count);
	}
	
	public static Normal normal() {
		Normal normal = new Normal(0, 1.0, new MersenneTwister());
		return normal;
	}
	
	public static double[] normalDraw() {
		return normalDraw(NUM_OF_DRAW);
	}
	
	public static double[] normalDraw(int number) {
		Normal nrnGenerator = normal();
		return normalDraw(number, nrnGenerator);
	}
	
	public static double[] normalDraw(int number, Normal nrnGenerator) {
		double[] draws = new double[number];
		for (int i = 0; i < draws.length; i++) {
			draws[i] = nrnGenerator.nextDouble();
		}
		return draws;
	}

}
