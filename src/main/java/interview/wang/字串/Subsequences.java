package interview.wang.字串;

import java.util.Arrays;

/**
 * Given two strings s and t, return the number of distinct subsequences of s which equals t.
 * */
public class Subsequences {
    /*【backtrack + Memo】 O(M*N) O(M*N) M= length of s, n = length of t*/
    public int numDistinct(String s, String t) {
        int[][] temp = new int[s.length()][t.length()];
        for(int[] rows : temp) Arrays.fill(rows, -1);
        return backtrack(s, t, temp, 0, 0);
    }
    private int backtrack(String s, String t, int[][] temp, int i, int j) {
        if(j==t.length()) return 1;
        if(i==s.length()) return 0;
        if(temp[i][j] != -1) return temp[i][j];
        temp[i][j] = 0;
        if(s.charAt(i) == t.charAt(j)) {
            temp[i][j] += backtrack(s, t, temp, i+1, j+1);
        }
        temp[i][j] += backtrack(s, t, temp, i+1, j);
        return temp[i][j];
    }

    /*【Space optimized DP】 O(M*N) O(N) M= length of s, n = length of t*/
    public int numDistinctDP(String s, String t) {
        int M = s.length();
        int N = t.length();
        int[] dp = new int[N];
        int prev = 1;
        for (int i = M - 1; i >= 0; i--) {
            prev = 1;
            for (int j = N - 1; j >= 0; j--) { //遍历t字符 比较 s当前字符
                int old_dpj = dp[j];
                if (s.charAt(i) == t.charAt(j)) {
                    dp[j] += prev;
                    System.out.println("prev="+prev+" dp["+j+"]="+dp[j] + " {"+t.charAt(j)+"}");
                }
                if(prev != old_dpj) System.out.println("prev= from "+prev+" to "+old_dpj);
                prev = old_dpj;
                print(i, j, s,t,dp);
            }
        }
        return dp[0];
    }

    /*【Space optimized DP】 O(M*N) O(M*N) M= length of s, n = length of t*/
    public int numDistinctDPFull(String s, String t) {
        int M = s.length(),  N = t.length();
        int[][] dp = new int[M + 1][N + 1];
        Arrays.fill(dp[M], 0);
        for (int i = 0; i <= M; i++) dp[i][N] = 1;

        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j]; // we always need this result
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] += dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new Subsequences().numDistinctDP("abit","bbit"));
    }
    private void print(int i, int j, String s, String t, int[] dp){
        System.out.print("["+i+"]["+j+"]["+s.charAt(i)+"]["+t.charAt(j)+"]{");
        for (int d : dp) {
            System.out.print(d+",\t");
        }
        System.out.println("}");
    }
}
