package interview.core;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.Set;

public class TestLinkedHashMap {

	public static void main(String[] args) {
		/*LinkedHashMap map = new LinkedHashMap();
		map.put("1", "aaa");
		map.put("2", "aaa");
		map.put("3", "aaa");
		map.put("4", "aaa");
		map.put("5", "aaa");
		map.put("6", "aaa");
		Set keySet = map.keySet();
		keySet.add("test"); //java.lang.UnsupportedOperationException
		System.out.println(keySet.getClass().getName());
		System.out.println(keySet);*/
		
		PriorityQueue p = new PriorityQueue(5, new Comparator(){

			@Override
			public int compare(Object arg0, Object arg1) {
				return 0;
			}});
		p.add(new TestLinkedHashMap());
		p.add(new TestLinkedHashMap());
		
	}
	
	

}
