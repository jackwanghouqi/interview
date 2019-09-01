package leetcode;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 * Input: 5
 * Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
 * 
 * 
 * 
 * FB output
 * 
[
     [1],
     [1,1],
     [1,2,1],
     [1,3,3,1],
     [1,4,6,4,1]
]
 * */

public class L118PascalTriangle {

	public static void main(String[] args) {
		System.out.println(generate(5));
	}
	
	public static ArrayList<ArrayList<Integer>> generate(int a) {
	    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	    if(a==0) return list;
	    list.add(new ArrayList<Integer>());
	    list.get(0).add(1);
	    
	    for(int i=1; i < a; i ++) {
	        ArrayList<Integer> row = new ArrayList<>(i+1);
	        list.add(row);
	        row.add(1);
	        
	        for(int j=1; j<i; j++) {
	            List<Integer> rowPrev = list.get(i-1);
	            row.add((rowPrev.get(j)+rowPrev.get(j-1)));
	        }
	        row.add(1);
	    }
	    return list;
	    
	}

}
