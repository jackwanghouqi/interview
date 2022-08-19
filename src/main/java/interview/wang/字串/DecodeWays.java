package interview.wang.字串;

import java.util.HashMap;
import java.util.Map;

public class DecodeWays {
    /*【Iterative】 O(N) O(1) */
    public int numDecodings(String s) {
        if (s==null || s.length() ==0 || s.charAt(0) == '0') return 0;
        int n = s.length();
        int twoBack = 1, oneBack = 1;
        for (int i = 1; i < n; i++) {
            int current = 0;
            if (s.charAt(i) != '0') current = oneBack;
            int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
            if (twoDigit >= 10 && twoDigit <= 26) current += twoBack;
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }

    /*【Iterative】 O(N) O(N) */
    public int numDecodingsMemo(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for(int i = 2; i < dp.length; i++) {
            if (s.charAt(i - 1) != '0') dp[i] = dp[i - 1];
            int twoDigit = Integer.valueOf(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) dp[i] += dp[i - 2];
        }

        return dp[s.length()];
    }

    /*【Recursive】 O(N) O(N)*/
    Map<Integer, Integer> memo = new HashMap<>();

    public int numDecodingsRecursive(String s) {
        return recursiveWithMemo(0, s);
    }

    private int recursiveWithMemo(int index, String str) {
        if (memo.containsKey(index)) return memo.get(index); //check
        if (index == str.length()) return 1;
        if (str.charAt(index) == '0') return 0;
        if (index == str.length() - 1) return 1;
        int ans = recursiveWithMemo(index + 1, str);
        if (Integer.parseInt(str.substring(index, index + 2)) <= 26) {
            ans += recursiveWithMemo(index + 2, str);
        }
        memo.put(index, ans);// Save
        return ans;
    }
}
