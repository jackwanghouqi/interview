package interview.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of integers, sort the array into a wave like array and return it, 
 * In other words, arrange the elements into a sequence such that a1 >= a2 <= a3 >= a4 <= a5.....
 * */

public class Wave {

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(5);a.add(1);a.add(3);a.add(2);a.add(4);;a.add(4);;a.add(4);
		ArrayList<Integer> wave = wave1(a);
		System.out.println(wave);
	}
	
	public static ArrayList<Integer> wave(ArrayList<Integer> a) {
	    Integer[] aa = a.toArray(new Integer[a.size()]);
	    Arrays.sort(aa);
	    
	    for(int i = 1; i < aa.length; i += 2) {
	        int temp = aa[i];
	        aa[i] = aa[i-1];
	        aa[i-1] = temp;
	    }
	    
	    for(int i = 0; i < a.size(); i ++) {
	        a.set(i,aa[i]);
	    }
	    return a;
	}
	
	
	public static ArrayList<Integer> wave1(ArrayList<Integer> a) {
		a.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
	    
	    for(int i = 1; i < a.size(); i += 2) {
	        int temp = a.get(i);
	        a.set(i, a.get(i-1));
	        a.set(i-1, temp);
	    }
	    
	    return a;
	}
	
	
	private static void msort(ArrayList<Integer> a) {
	    ArrayList<Integer> temp = new ArrayList<Integer>(a.size());
	    _msort(a, temp, 0, a.size()-1);
	}
	
	private static void _msort(ArrayList<Integer> a, ArrayList<Integer> temp, int left, int right) {
	    if(left < right) {
	        int center = (right+left)/2;
	        _msort(a,temp,left, center);
	        _msort(a,temp,center+1,right);
	        merge(a,temp,left, center, right);
	    }
	}
	
	private static void merge(ArrayList<Integer> a, ArrayList<Integer> temp, int lowerIndex, int middle, int higherIndex) {
	    for (int i = lowerIndex; i <= higherIndex; i++) {
            temp.add(i,a.get(i));
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (temp.get(i) <= temp.get(j)) {
                replace(a, k, temp.get(i));
                i++;
            } else {
                replace(a, k, temp.get(j));
                j++;
            }
            k++;
        }
        while (i <= middle) {
            replace(a, k, temp.get(i));
            k++;
            i++;
        }
	}
	
	private static void swap(ArrayList<Integer> a, int i, int j) {
	    int tempi = a.get(i);
	    int tempj = a.get(j);
	    replace(a, i, tempj);
        replace(a, j, tempi);
	}

	private static void replace(ArrayList<Integer> a, int i, int element) {
		a.set(i, element);
	}
	
	private static void sort(ArrayList<Integer> a) {
		   
	    for(int i = 0; i < a.size()-1; i ++) {
	        for(int j = i; j < a.size(); j ++) {
	            if(a.get(i) > a.get(j)) {
	                swap(a,i,j);
	            }
	        }
	    }
	}

}
