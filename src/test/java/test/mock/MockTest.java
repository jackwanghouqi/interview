package test.mock;

import java.util.ArrayList;
import java.util.List;

public class MockTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add(null);
		System.out.println(list.size());
	}
}
