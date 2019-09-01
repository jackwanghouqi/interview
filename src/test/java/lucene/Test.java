package lucene;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class Test {

	public static void main(String[] args) throws ParseException {
		String str = "tears";
		
		char[] dst = new char[str.length()];
		str.getChars(0, str.length(), dst , 0);
		Arrays.sort(dst);
		System.out.println(dst);
		
		StringJoiner sj = new StringJoiner(" ");
		sj.add(1+"");
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		System.out.println(sdf.parse("20180101"));*/
		
		
		countIntersection(new int[] {2,4,6,9,27}, new int[] {1,3,6,8,9,10,26,31});
		
	}
	
	public static void countIntersection(int[] a, int[] b) {
		int x=0, y=0;
		while(x<a.length&&y<b.length) {
			if(a[x] == b[y]) {
				System.out.println(a[x]);
				x++;y++;
			} else if(a[x] > b[y]) {
				y++;
			} else {
				x++;
			}
		}
	}

}
