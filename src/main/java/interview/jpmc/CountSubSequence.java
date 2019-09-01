package interview.jpmc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CountSubSequence {
	/**
	 * Programming Challenge Description:
A subsequence of a given sequence S consists of S with zero or more elements deleted. Formally, a sequence Z = z1z2..zk is a subsequence of X = x1x2...xm, if there exists a strictly increasing sequence of indices (i) of X such that for all j=1,2,...k we have Xi = Zj. E.g. Z=bcdb is a subsequence of X=abcbdab with corresponding index sequence <2,3,5,7>
Input:
Your program should read lines from standard input. Each line contains two comma separated strings. The first is the sequence X and the second is the subsequence Z.
Output:
Print out the number of distinct occurrences of Z in X as a subsequence.
Test 1
Test Input Download Test Inputbabgbag,bag
Expected Output Download Test Output5
Test 2
Test Input Download Test Inputrabbbit,rabbit
Expected Output Download Test Output3
	 * 
	 * 
	 */
	public static void main(String[] args) throws IOException {
		InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
		BufferedReader in = new BufferedReader(reader);
		String line;
		while ((line = in.readLine()) != null) {
			try {
				if (line.trim().equals("") || !line.contains(","))
					System.out.println("Invalid Input! Try again.");
				String[] split = line.split(",");
				if (split.length != 2)
					System.out.println("Invalid Input!  Try again.");
				String xStr = split[0];
				String zStr = split[1];
				
				int count = match(xStr, zStr);
				System.out.println(count);
			} catch (Exception e) {
				throw new IOException(e);
			}
		}
	}
	
	private static int match(String xStr, String zStr) {
		if(zStr.length()==0) return 0; //not expected
		char firstChar = zStr.charAt(0);
		if(zStr.length()==1) {
			return matchOneChar(xStr, firstChar);
		}
		int count = 0;
		for (int i = 0; i < xStr.length(); i++) {
			if(xStr.charAt(i) == firstChar) {
				count += match(xStr.substring(i+1), zStr.substring(1));
			}
		}
		return count;
	}
	
	private static int matchOneChar(String xStr, char c) {
		int count = 0;
		for (int i = 0; i < xStr.length(); i++) {
			if(xStr.charAt(i) == c) count++;
		}
		return count;
	}

}