package interview.wang.数组;

import java.util.Arrays;

public class HouseRobber {
    /*【DP 2 index】 only calculate the previous two max*/
    public int rob(int[] nums) {
        int N = nums.length;
        if (N == 0) return 0;//【DP】int[] maxRobbedAmount = new int[nums.length + 1];
        int robNext=nums[N - 1], robNextPlusOne=0;////【DP】maxRobbedAmount[N] = 0;maxRobbedAmount[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; --i) {
            //【DP】maxRobbedAmount[i] = Math.max(maxRobbedAmount[i + 1], maxRobbedAmount[i + 2] + nums[i]);
            int current = Math.max(robNext, robNextPlusOne + nums[i]);
            robNextPlusOne = robNext;
            robNext = current;
        } // Time ：O(N)   space：O(1)
        return robNext; //【DP】return maxRobbedAmount[0];
    }

    private int[] memo;

    /*【Recursion】with Memoization*/
    public int robRecursion(int[] nums) {
        this.memo = new int[100];
        Arrays.fill(this.memo, -1);
        return this.robFrom(0, nums);
    }
    private int robFrom(int i, int[] nums) {
        if (i >= nums.length) return 0;
        if (this.memo[i] > -1) return this.memo[i];
        // Recursive
        int ans = Math.max(this.robFrom(i + 1, nums), this.robFrom(i + 2, nums) + nums[i]);
        this.memo[i] = ans;// Cache
        return ans;
    }

    /*【环状circle】【DP】*/
    public int robCircle(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int max1 = rob_simple(nums, 0, nums.length - 2);
        int max2 = rob_simple(nums, 1, nums.length - 1);
        return Math.max(max1, max2);
    }

    public int rob_simple(int[] nums, int start, int end) {
        int t1 = 0, t2 = 0;
        for (int i = start; i <= end; i++) {
            int temp = t1;
            int current = nums[i];
            t1 = Math.max(current + t2, t1);
            t2 = temp;
        }

        return t1;
    }
}
