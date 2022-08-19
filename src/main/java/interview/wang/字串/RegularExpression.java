package interview.wang.字串;
/**
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * */
public class RegularExpression {
    /*【DP (Top-Down)】 O(TP) O(TP)*/
    public boolean isMatchDPTD(String text, String pattern) {
        int[][] memo = new int[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern, memo);
    }
    public boolean dp(int i, int j, String text, String pattern, int[][] memo) {
        if (memo[i][j] != 0) return memo[i][j] == 1;
        boolean result;
        if (j == pattern.length()){
            result = i == text.length();
        } else{
            boolean firstMatch = (i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                result = dp(i, j+2, text, pattern, memo) || firstMatch && dp(i+1, j, text, pattern, memo);
            } else {
                result = firstMatch && dp(i+1, j+1, text, pattern, memo);
            }
        }
        memo[i][j] = result ? 1 : -1;
        return result;
    }

    /*【DP (Top-Down)】 O(TP) O(TP)*/
    public boolean isMatchDPBU(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean firstMatch = (i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || firstMatch && dp[i+1][j];
                } else {
                    dp[i][j] = firstMatch && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
