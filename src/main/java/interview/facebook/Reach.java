package interview.facebook;

import java.util.HashMap;
import java.util.List;

/**
 * You are in an infinite 2D grid where you can move in any of the 8 directions :
 * You are given a sequence of points and the order in which you need to cover the points. 
 * Give the minimum number of steps in which you can achieve it. You start from the first point.
 * 
 * Input : [(0, 0), (1, 1), (1, 2)]
 * Output : 2
 * 
 * */

public class Reach {

	public static void main(String[] args) {
		Point[] points = new Point[] {new Point(0, 0), new Point(1, 2), new Point(3, 2), new Point(4, 44), new Point(5, 5)};
		System.out.println(reach(points));
	}
	
	public static int reach(Point[] points) {
		int count = 0;
		for(int i=0; i<points.length-1; i++) {
			int xDiff = Math.abs(points[i].x - points[i+1].x);
			int yDiff = Math.abs(points[i].y - points[i+1].y);
			count += Math.max(xDiff, yDiff);
		}
		return count;
	}
	
	private static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public int majorityElement(final List<Integer> a) {
	    HashMap<Integer, Integer> map = new HashMap<>();
	    for(Integer x : a) {
	        if(!map.containsKey(x)) {
	            map.put(x,1);
	        }
	        map.put(x, map.get(x)+1);
	    }
	    int majorityCount = a.size()/2;
	    for(Integer x : a) {
	        if(map.get(x)>majorityCount) {
	            return x;
	        }
	    }
	    //Shouldn't reach here unless the list not valid!
	    //Should throw exception?
	    
	    return 0;
	}

}
