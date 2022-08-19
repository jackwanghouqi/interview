package interview.wang.数组;

import java.util.Arrays;
/**
 * You are given an integer array nums and an integer target
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer
 * Return the number of different expressions that you can build, which evaluates to target
 * */
public class SumTarget {

    /*【Brute Force】 O(2^n) O(n)*/
    public int findTargetSumWays(int[] nums, int S) {
        return calculate(nums, 0, 0, S);
    }
    public int calculate(int[] nums, int i, int sum, int S) {
        if (i == nums.length) return sum == S ? 1:0; // count 1 or 0
        int result = calculate(nums, i + 1, sum + nums[i], S);
        result += calculate(nums, i + 1, sum - nums[i], S);
        return result;
    }

    /* 【Recursion with Memoization】 O(T*N) O(T*N)   t is total sum*/
    public int findTargetSumWaysRecursion(int[] nums, int S) {
        //int total = Arrays.stream(nums).map((num)->Math.abs(num)).sum();
        int total = Arrays.stream(nums).sum();//条件 0 <= nums[i] <= 1000 和 sum<1000
        int[][] memo = new int[nums.length][2 * total + 1];
        for (int[] row : memo) Arrays.fill(row, -1); //初始化 结果集
        return backtrack(nums, 0, 0, S, memo, total);
    }

    public int backtrack(int[] nums, int i, int sum, int S, int[][] memo, int total) {
        if (i == nums.length) return sum == S ? 1:0; // count 1 or 0
        if (memo[i][sum + total] != -1) return memo[i][sum + total]; //判断初始化
        int add = backtrack(nums, i + 1, sum + nums[i], S, memo, total);// count from backtrack
        int subtract = backtrack(nums, i + 1, sum - nums[i], S, memo, total);//count from backtrack
        memo[i][sum + total] = add + subtract;
        return memo[i][sum + total];
    }

    /* 【DP】 O(T*N) O(T*N)   t is total sum*/
    public int findTargetSumWaysDP(int[] nums, int S) {
        int total = Arrays.stream(nums).sum();
        int[][] dp = new int[nums.length][2 * total + 1];
        dp[0][nums[0] + total] = 1; dp[0][-nums[0] + total] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -total; sum <= total; sum++) {
                if (dp[i - 1][sum + total] > 0) {
                    dp[i][sum + nums[i] + total] += dp[i - 1][sum + total];
                    dp[i][sum - nums[i] + total] += dp[i - 1][sum + total];
                }
            }
        }
        return Math.abs(S) > total ? 0 : dp[nums.length - 1][S + total];
    }
    /* 【DP 1D】 O(T*N) O(T)   t is total sum*/
    public int findTargetSumWaysDP1D(int[] nums, int S) {
        int total = Arrays.stream(nums).sum();
        int[] dp = new int[2 * total + 1];
        dp[nums[0] + total] = 1;
        dp[-nums[0] + total] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2 * total + 1];
            for (int sum = -total; sum <= total; sum++) {
                if (dp[sum + total] > 0) {
                    next[sum + nums[i] + total] += dp[sum + total];
                    next[sum - nums[i] + total] += dp[sum + total];
                }
            }
            dp = next;
        }
        return Math.abs(S) > total ? 0 : dp[S + total];
    }
}
