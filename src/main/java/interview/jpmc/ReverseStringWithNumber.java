package interview.jpmc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

public class ReverseStringWithNumber {
	/**
	 * Iterate through each line of input.
	 * 
	 * 	input: 1,2,3,4,5,6,7,8,9;4 
		output: 4,3,2,1,8,7,6,5,9
		input: 1,2,3,4,5;3
		output: 3,2,1,4,5
	 */
	public static void main(String[] args) throws IOException {
		InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
		BufferedReader in = new BufferedReader(reader);
		String line;
		while ((line = in.readLine()) != null) {
			try {
				if (line.trim().equals("") || !line.contains(";"))
					continue;
				String[] split = line.split(";");
				if (split.length != 2)
					continue; // or do something else?
				String[] split2 = split[0].split(",");
				int multiplier = Integer.parseInt(split[1]);
				int[] x = new int[split2.length];
				for (int i = 0; i < x.length; i++) {
					x[i] = Integer.parseInt(split2[i]);
				}
				
				reverse(x, multiplier);
				// create string
				StringJoiner sj = new StringJoiner(",");
				for (int i = 0; i < x.length; i++) {
					sj.add("" + x[i]);
				}
				System.out.println(sj);
			} catch (Exception e) {
				throw new IOException(e);
			}
		}
	}

	private static void reverse(int[] x, int multiplier) {
		// increment with multiplier
		for (int i = multiplier - 1; i < x.length; i += multiplier) {
			// match
			if (x[i] % multiplier == 0) {
				int start = i - multiplier + 1;
				int end = i;
				// swap
				for (int j = start; j < ((end - start) / 2) + start + 1; j++) {
					int temp = x[j];
					x[j] = x[end - (j - start)];
					x[end - (j - start)] = temp;
				}
			}
		}
	}
}