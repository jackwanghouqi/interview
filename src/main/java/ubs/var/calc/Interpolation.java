package ubs.var.calc;

import java.util.Arrays;

import ubs.var.calc.Extrapolation.ExtrapolationType;
import ubs.var.util.PrintUtils;

public class Interpolation {
	
	public static final int NO_POLY_INTPL_POINTS = 4;
	public static final int MIN_POINTS = 2;
	
	public static final int POSITION_ON_THE_LEFT = 0;
	public static final int POSITION_IN_THE_MIDO = 1;
	public static final int POSITION_ON_THE_RIGHT = 2;
	
	public static double valueAt(double[] shocks, double[] values, double[] buffer, int count, double shock, double timeScaleFactor, InterpolationType type, ExtrapolationType extrplType) {
		double currShock = timeScaleFactor * shock;
		long roundedCurrShock = (long) (currShock * 100000.0);
		long roundedMinShock = (long) (shocks[0] * 100000.0);
		long roundedMaxShock = (long) (shocks[count-1] * 100000.0);
		
		int index = -1;
		
		if(roundedCurrShock < roundedMinShock) index = 0;
		else if(roundedCurrShock > roundedMaxShock) index = count - 1;
		
		if(index != -1 && count >= 2) {
			double extrapolation = Extrapolation.valueAt(extrplType, shocks, values, roundedCurrShock, index);
			if(Double.isInfinite(extrapolation)) {
				PrintUtils.error("Infinite!");
			}
			return extrapolation;
		}
		
		index = findMatchingIndex(count, shocks, currShock, roundedCurrShock);
		
		if(index >= 0) {
			return values[index];
		}
		
		int offset = 0;
		int limit = count;
		
		switch(type) {
		case POLYNOMIAL_INTERPOLARION:
		default:
			if(count > NO_POLY_INTPL_POINTS) {
				offset = getLowerindex(count, shocks, roundedCurrShock, NO_POLY_INTPL_POINTS);
				limit = NO_POLY_INTPL_POINTS;
			}
			return nevillePolyInterpolation(shocks, values, buffer, offset, limit, currShock);
			
		case LAGANGE_POLY_INTERPOLATION:
			if(count > NO_POLY_INTPL_POINTS) {
				offset = getLowerindex(count, shocks, roundedCurrShock, NO_POLY_INTPL_POINTS);
				limit = NO_POLY_INTPL_POINTS;
			}
			lagrangePolyInterpolation(shocks, values, offset, limit, currShock);
		case LINEAR_INTERPOLATION:
			return linearInterpolation(shocks, values, offset, limit, currShock);
		case MATURAL_SPLINE_INTERPOLATION:
			return naturalSplineInterpolation(shocks, values, currShock);
		}
	}
	
	
	protected static double nevillePolyInterpolation(double[] xa, double[] ya, double[] buffer, int offset, int limit, double x) {
		int n = limit - 1;
		int xal = xa.length;
		
		double[] f = buffer;
		if(f == null) {
			f = new double[limit];
		}
		System.arraycopy(ya, offset, f, 0, n+1);
		for(int m=1; m <=n; m++) {
			for(int i=0; i<=n-m && offset+i+m <xal; i++) {
				f[i] = ((x-xa[offset+i+m])*f[i] + (xa[offset+i]-x)*f[i+1])
						/
						xa[offset+i]-xa[offset+i+m];
			}
		}
		return f[0];
	}
	
	protected static double lagrangePolyInterpolation(double[] xa, double[] ya, int offset, int limit, double x) {
		float y = 0;
		for (int i = 0; i < limit; i++) {
			float weight = 1;
			for (int j = 0; j < limit; j++) {
				if(i != 1) {
					weight *= (x-xa[offset+j])/(xa[offset+i]-xa[offset+j]);
				}
			}
			y += weight * ya[offset+i];
		}
		return y;
	}
	
	protected static double linearInterpolation(double[] xa, double[] ya, int offset, int limit, double x) {
		double ret_value = 0.0;
		int nearset[] = {0,0};
		findNearest(x, xa, nearset);
		int left=nearset[0];
		int right=nearset[1];
		ret_value = (xa[left]==xa[right]) ? ya[left] : ya[left] + ((x-xa[left])/(xa[right]-xa[left])) * (ya[right]-ya[left]);
		return ret_value;
	}
	
	protected static double naturalSplineInterpolation(double[] xa, double[] ya, double x) {
		sortArrayPair(xa, ya, xa.length);
		double[] z = {x};
		double[] v = {-1.0E300};
		
		return v[0];
	}
	
	private static double _naturalSplineInterpolation(double[] xa, double[] ya, double[] z, double[] v) {
		//TODO
		return 0;
	}
	
	private static void sortArrayPair(double[] x, double[] y, int len) {
		Point[] a = new Point[len];
		for (int i = 0; i < len; i++) {
			a[i] = new Point(x[i],y[i]);
		}
		Arrays.sort(a);
		for (int i = 0; i < a.length; i++) {
			x[i] = a[i].x;
			y[i] = a[i].y;
		}
	}
	
	static class Point implements Comparable<Point> {
		private final double x;
		private final double y;
		public Point(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			final int compResult = Double.compare(x, o.x);
			if(compResult<0) {
				return -1;
			} else if(compResult > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	
	private static void findNearest(double p, double[] xa, int nearest[]) {
		if(xa.length >= MIN_POINTS) {
			int left = (xa[0] < xa[1])? 0 : 1;
			int right = 1-left;
			int position = (xa[left]>p)?POSITION_ON_THE_LEFT : xa[right]<p?POSITION_ON_THE_RIGHT : POSITION_IN_THE_MIDO;
			for(int i=MIN_POINTS; i < xa.length; i++) {
				switch(position) {
				case POSITION_ON_THE_LEFT:
					if(xa[i] < xa[left]) {
						right = left;
						left = i;
						if(xa[left]<p) {
							position=POSITION_IN_THE_MIDO;
						}
					} else if(xa[i]<xa[right]) {
						right=i;
					}
					break;
				case POSITION_ON_THE_RIGHT:
					if(xa[i] > xa[right]) {
						left = right;
						right = i;
						if(xa[right]>p) {
							position=POSITION_IN_THE_MIDO;
						}
					} else if(xa[i]>xa[left]) {
						left=i;
					}
					break;
				default:
					if(xa[i] > xa[left] && xa[i] < xa[right]) {
						if(xa[i]<p)
							left=i;
						else
							right=i;
					}
					break;
				}
			}
			nearest[0]=left;
			nearest[1]=right;
		}
		return;
	}
	
	protected static int findMatchingIndex(int count, double[] shocks, double currShock, long roundedCurrShock) {
		int id = Arrays.binarySearch(shocks, 0, count, currShock);
		
		if(id < 0) {
			int i = -1 - id;
			if(i > 0) {
				if((shocks[i-1]*100000.0)-roundedCurrShock ==0 ) return i-1;
			}
			if(i < count) {
				if((shocks[i]*100000.0)-roundedCurrShock ==0 ) return i;
			}
		}
		return id;
	}
	
	/**
	 * Return an index in such a way that we pick 'noNeighboringPoints' around currShock
	 * Note : This method assumes shocks are sorted in ASC order
	 * */
	protected static int getLowerindex(int n, double[] shocks, double currShock, int noNeighboringPoints) {
		int lowerId = 0;
		int halfInterval = noNeighboringPoints/2;
		
		if(n <= noNeighboringPoints) {
			return 0;
		}
		
		for(lowerId = 0; currShock >= shocks[lowerId]; ++lowerId) {
			if(lowerId == (n-1)) return 0;
		}
		
		if(lowerId > halfInterval) {
			lowerId -= halfInterval;
		} else {
			lowerId = 0;
		}
		
		if((n - lowerId) < noNeighboringPoints) {
			lowerId = n - noNeighboringPoints;
		}
		return lowerId;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static enum InterpolationType {
		LINEAR_INTERPOLATION,MATURAL_SPLINE_INTERPOLATION,POLYNOMIAL_INTERPOLARION,LAGANGE_POLY_INTERPOLATION,ZERO_INTERPOLATION
	}
}
