package interview.facebook;

import java.util.ArrayList;

/**
 * Give a N*N square matrix, return an array of its anti-diagonals. Look at the example for more details.
 * 
 * */

public class AntiDiagonal {

	public static ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> a) {
	    ArrayList<ArrayList<Integer>> result = new  ArrayList<ArrayList<Integer>>();
	    if(a.size() == 0) {
	        return result;
	    }
	    
	    for(int i = 0; i < a.get(0).size(); i ++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            int x = i;
            int y = 0;
            while(x >=0 ) {
                list.add(a.get(y).get(x));
                x--;
                y++;
            }
            result.add(list);
	    }
	    for(int j = 1; j < a.size(); j ++) {
	        ArrayList<Integer> list = new ArrayList<Integer>();
            int x = a.get(0).size()-1;
            int y = j;
            while(y < a.size() ) {
                list.add(a.get(y).get(x));
                x--;
                y++;
            }
            result.add(list);
	    }
	    return result;
	}
	
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> A = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(10);
        temp.add(20);
        temp.add(30);
        A.add(new ArrayList<Integer>(temp));
        temp.clear();
        
        temp.add(50);
        temp.add(60);
        temp.add(70);
        A.add(new ArrayList<Integer>(temp));
        temp.clear();
        
        temp.add(90);
        temp.add(100);
        temp.add(110);
        A.add(new ArrayList<Integer>(temp));
        temp.clear();
        
        for(ArrayList<Integer> t : A)
            System.out.println(t);
        System.out.println();
        ArrayList<ArrayList<Integer>> result  = diagonal(A);
        for(ArrayList<Integer> t : result)
            System.out.println(t);
	}
}
