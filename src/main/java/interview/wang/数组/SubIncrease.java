package interview.wang.数组;

import java.util.*;
/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * */
public class SubIncrease {
    /*【DP】O(N^2) O(N)  存储每个值对应的最大sub长度*/
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        int longest = 0;
        for (int c: dp) longest = Math.max(longest, c);
        return longest;
    }

    /* 只关心长度【Build a Subsequence(Always REPLACE the smaller number in the elements)】 O(N^2) O(N)*/
   /*can be enhanced by below approach with Binary Search*/
    public int smartWay(int[] nums) {
        ArrayList<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > sub.get(sub.size() - 1)) sub.add(num);
            else {
                int j = 0;// Find the first element in sub that is greater than or equal to num
                while (num > sub.get(j)) j += 1;
                sub.set(j, num); //【Replace with smaller one】 Note: this is because we only care about the size.
            }
        }
        return sub.size();
    }

    /*【With Binary Search】 O(N*log(N)) O(N)*/
    public int smartWayWithBinarySearch(int[] nums) {
        ArrayList<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > sub.get(sub.size() - 1)) { sub.add(num);
            } else {
                int j = binarySearch(sub, num);
                sub.set(j, num);
            }
        }
        return sub.size();
    }
    private int binarySearch(ArrayList<Integer> sub, int num) {
        int left = 0; int right = sub.size() - 1;
        int mid = (left + right) / 2;
        while (left < right) {
            mid = (left + right) / 2;
            if (sub.get(mid) == num) return mid;
            if (sub.get(mid) < num) left = mid + 1; else right = mid;
        }
        return left;
    }
}
/*https://leetcode.com/problems/longest-increasing-subsequence/*/