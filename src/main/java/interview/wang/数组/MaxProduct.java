package interview.wang.数组;

public class MaxProduct {
    /*【Brute Force】O(N^2) O(1)*/
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        int result = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int accu = 1;
            for (int j = i; j < nums.length; j++) {
                accu *= nums[j];
                result = Math.max(result, accu);
            }
        }
        return result;
    }

    /*【DP】O(N) O(1)*/
    public int maxProductDP(int[] nums) {
        if (nums.length == 0) return 0;
        int max_so_far = nums[0];
        int min_so_far = nums[0]; //负数有可能乘以负数成为最大
        int result = max_so_far;
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int temp_max = Math.max(curr, Math.max(max_so_far * curr, min_so_far * curr));
            min_so_far = Math.min(curr, Math.min(max_so_far * curr, min_so_far * curr));
            max_so_far = temp_max;
            result = Math.max(max_so_far, result);
        }
        return result;
    }
}
