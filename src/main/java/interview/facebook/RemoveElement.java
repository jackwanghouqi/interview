package interview.facebook;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Given an array and a value, remove all the instances of that value in the array. 
 * Also return the number of elements left in the array after the operation.
 * It does not matter what is left beyond the expected length.
 * */

public class RemoveElement {

	public static void main(String[] args) {
		
		ArrayList<Integer> a = new ArrayList<>();
		a.add(5);a.add(1);a.add(3);a.add(3);a.add(2);a.add(1);
		int length = removeElement(a, 5);
		
		System.out.println(a);
		System.out.println(length);
	}
	
	public static int removeElement(ArrayList<Integer> a, int b) {
	    /*int i;
	    for(i=0;i<a.size();i++) {
	        if(b==a.get(i)) {
	            int tempId = getNextValidIndex(a,i,b);
	            if(tempId == i || tempId >=a.size())
	                return i;
	            int tempValue = a.get(tempId);
	            a.set(tempId,a.get(i));
	            a.set(i,tempValue);
	        }
	    }
	    return i;*/
		HashSet<Integer> ids = new HashSet<>();
		ids.add(b);
		a.removeAll(ids);
		return a.size();
	}
	
	/*private static int getNextValidIndex(ArrayList<Integer> a, int tempId, int b) {
	    while(tempId < a.size() && a.get(tempId) == b) {
	        tempId++;
	    }
	    return tempId;
	}*/

}
