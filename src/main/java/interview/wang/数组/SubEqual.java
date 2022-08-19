package interview.wang.数组;

import java.util.HashSet;

/**
 * Given a non-empty array nums containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * */
public class SubEqual {

    /*【Brute Force】 O(N^2) O(N) */
    public boolean canPartition(int[] nums) {
        int totalSum = 0;                       for (int num : nums) totalSum += num;
        if (totalSum % 2 != 0) return false;    int subSetSum = totalSum / 2;
        int n = nums.length;                    return dfs(nums, n - 1, subSetSum);
    }

    public boolean dfs(int[] nums, int n, int subSetSum) {
        if (subSetSum == 0) return true;
        if (n == 0 || subSetSum < 0) return false;
        return dfs(nums, n - 1, subSetSum - nums[n - 1]) || dfs(nums, n - 1, subSetSum);
    }

    /*【DP Top-Down+ Memoization】 O(M*N) O(M*N) M=subSetSum*/
    public boolean canPartitionDP_Top(int[] nums) {
        int totalSum = 0;                       for (int num : nums) totalSum += num; // sum all elements
        if (totalSum % 2 != 0) return false;    int subSetSum = totalSum / 2;
        int n = nums.length;
        Boolean[][] memo = new Boolean[n + 1][subSetSum + 1];
        return dfs(nums, n - 1, subSetSum, memo);
    }
    public boolean dfs(int[] nums, int n, int subSetSum, Boolean[][] memo) {
        if (subSetSum == 0) return true; // Base Cases
        if (n == 0 || subSetSum < 0) return false; //invalid case
        // check if subSetSum for given n is already computed and stored in memo
        if (memo[n][subSetSum] != null) return memo[n][subSetSum];
        memo[n][subSetSum] = dfs(nums, n - 1, subSetSum - nums[n - 1], memo)
                || dfs(nums, n - 1, subSetSum, memo);
        // store the result in memo
        return memo[n][subSetSum];
    }

    /*【DP Top-Down+ Memoization】 O(M*N) O(M*N) M=subSetSum*/
    public boolean canPartitionDP_BottomUp(int[] nums) {
        int totalSum = 0;                       for (int num : nums) totalSum += num; // sum all elements
        if (totalSum % 2 != 0) return false;    int subSetSum = totalSum / 2;
        int n = nums.length;
        boolean dp[][] = new boolean[n + 1][subSetSum + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            int curr = nums[i - 1];
            for (int j = 0; j <= subSetSum; j++) {
                if (j < curr) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j] || (dp[i - 1][j - curr]);
            }
        }
        return dp[n][subSetSum];
    }

    /*【DP Top-Down+ Memoization 1D Array】 O(M*N) O(M) M=subSetSum*/
    public boolean canPartitionDP_BottomUp_1D(int[] nums) {
        int totalSum = 0;                       for (int num : nums) totalSum += num; // sum all elements
        if (totalSum % 2 != 0) return false;    int subSetSum = totalSum / 2;
        boolean dp[] = new boolean[subSetSum + 1];
        dp[0] = true;
        for (int curr : nums) {
            for (int j = subSetSum; j >= curr; j--) {
                dp[j] |= dp[j - curr];
            }
        }
        return dp[subSetSum];
    }

    /*【Smart --> find one matched sum in set】 O(N*M)    Space < O(N*M)  M=subSetSum*/
    public boolean smart(int[] nums) {
        int totalSum = 0;                       for (int num : nums) totalSum += num; // sum all array elements
        if (totalSum % 2 != 0) return false;    int subSetSum = totalSum / 2;
        HashSet <Integer> set = new HashSet<>();
        if(nums[0] == subSetSum) return true; //只要一个match,则剩下的肯定match
        set.add(nums[0]);
        for (int i=1; i <nums.length; i++) {
            HashSet<Integer> tempSet = new HashSet<>();
            for(int sum:set) {
                int newSum = sum+nums[i];
                if(newSum == subSetSum) return true;
                tempSet.add(newSum);
            }
            set.addAll(tempSet);
        }
        return false;
    }

}
