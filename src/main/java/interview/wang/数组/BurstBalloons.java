package interview.wang.数组;

/**
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.
 * If you burst the i balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins
 *  if nums[i - 1] and nums[i + 1] not valid then ignore or consider nums[i - 1]=1 or nums[i + 1]=1
 *  Return the maximum coins you can collect by bursting the balloons wisely.
 * */
public class BurstBalloons {
    /*【DP (Top-Down)】 O(N^3) O(N^2)*/
    public int maxCoinsDPTD(int[] nums) {
        int n = nums.length + 2;
        int[] newNums = new int[n]; newNums[0] = 1; newNums[n - 1] = 1; //add left and right as 1
        System.arraycopy(nums, 0, newNums, 1, n - 2);//init all =0
        int[][] memo = new int[n][n];
        return dp(memo, newNums, 1, n - 2);// can not burst first one and last one which are fake
    }
    public int dp(int[][] memo, int[] nums, int left, int right) {
        if (right - left < 0) return 0; //all done
        if (memo[left][right] > 0) return memo[left][right]; //check cache
        int result = 0;
        for (int i = left; i <= right; i++) {
            int gain = nums[left - 1] * nums[i] * nums[right + 1]; //假设 nums[i] is the last burst one
            int remaining = dp(memo, nums, left, i - 1) + dp(memo, nums, i + 1, right);
            result = Math.max(result, remaining + gain);
        }
        memo[left][right] = result;//cache
        return result;
    }

    /*【DP (Bottom-Up)】 O(N^3) O(N^2)*/
    public int maxCoinsDPBU(int[] nums) {
        int n = nums.length + 2;
        int[] newNums = new int[n]; newNums[0] = 1;  newNums[n - 1] = 1;
        System.arraycopy(nums, 0, newNums, 1, n - 2);
        int[][] dp = new int[n][n];
        for (int left = n - 2; left >= 1; left--) {
            for (int right = left; right <= n - 2; right++) {
                for (int i = left; i <= right; i++) {
                    int gain = newNums[left - 1] * newNums[i] * newNums[right + 1];// 假设newNums[i] is the last burst one
                    int remaining = dp[left][i - 1] + dp[i + 1][right];
                    dp[left][right] = Math.max(remaining + gain, dp[left][right]);// update
                }
            }
        }
        return dp[1][n - 2];// excluding the first one and the last one
    }
}
