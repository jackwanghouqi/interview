package interview.core.collections;

import java.util.*;

public class TestLinkedHashMap {

	public static void main(String[] args) {

		//testPutAndGet(new LinkedHashMap());
		//testPutAndGet(new TreeMap());
		//testPutAndGet(new HashMap());

		Integer i= new Integer(1);
		LinkedHashMap map = new LinkedHashMap();
		map.put("1", "aaa");
		map.put("2", "aaa");
		map.put("3", "aaa");
		map.put("4", "aaa");
		map.put("5", "aaa");
		map.put("6", "aaa");
		map.put("1", "bbb");
		Set keySet = map.keySet();
		System.out.println(keySet.getClass().getName());
		System.out.println(keySet);
		System.out.println(map.values());
		keySet.add("test"); //java.lang.UnsupportedOperationException
	}

	public static void testPutAndGet(Map map) {
		map.put(5, 1);
		map.put(-11000, 1);
		map.put(-3, 1);
		map.put(-2, 1);
		map.put(0, 1);
		map.put(-1,2);
		map.put(3,3);
		map.put(1,3);
		map.put(2,3);
		for (Object o : map.keySet()) {
			System.out.println(o);
		}
	}
	
	

}
