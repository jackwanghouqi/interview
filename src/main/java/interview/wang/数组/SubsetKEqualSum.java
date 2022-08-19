package interview.wang.数组;

import java.util.*;
/**
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 * 问：(1) k 的范围是什么？ 如果 > 32 则不能用bit进制。只能用String
 *     (2) nums[i] 的范围是什么？ 考虑 sum 后会不会溢出。
 *
 * */
public class SubsetKEqualSum {
    /*★★【二进制bit】  Time:  O(N*(2^N) Space: O(2^N) */
    /*思路：创建mask数组并看作二进制 设 nums=[3，2，1] k=2 则 mask 为 【000】【001】【010】【011】【100】【101】【110】【111】
        若 将 011 视为 取 sums中第1和第2个数，则 【011】value=1+2=3  所以 【111】value=1+2+3=6
        思考：（1）要确定【011】是否溢出 sum/k 要先判断【001】和【010】是否溢出
              （2）如果溢出(>sum/k)，则记录为-1 不再继续找基于这个组合的其他可能。
              （3）循环mask数组，每轮计算当前mask位置从nums多加入一个数的所有可能。如：【001】 下一个可能取 【011】和【101】
              （4）已知 【001】=0（3=sum/k 取模为0） 则mask【011】=mask【001】+nums[2]=2  mask【101】=mask【001】+nums[3]=1
              （5）不断累计以上计算，则如果有解，则最终【111】会被设置为0 否则无解.
    * */
    public boolean canPartitionKSubsets(int[] arr, int k) {
        int sum = 0;
        int n = arr.length;
        for (int i = 0; i < n; ++i) sum += arr[i];
        if (sum % k != 0) return false;
        int targetSum = sum / k;
        int[] subsetSum = new int[(1 << n)]; // n 个  bit
        for (int i = 0; i < subsetSum.length; ++i) subsetSum[i] = -1;
        subsetSum[0] = 0; // index 0 means: no element selected

        for (int mask = 0; mask < (1 << n); mask++) {
            if (subsetSum[mask] == -1) continue;
            for (int i = 0; i < n; i++) {
                //not yet computed && not overflow(>targetSum) ==> record
                if (subsetSum[mask | (1 << i)] ==-1 && (mask & (1 << i)) == 0 && subsetSum[mask] + arr[i] <= targetSum) {
                    subsetSum[mask | (1 << i)] = (subsetSum[mask] + arr[i]) % targetSum;
                }
            }
            if (subsetSum[(1 << n) - 1] == 0) return true;
        }
        return subsetSum[(1 << n) - 1] == 0;
    }

    /*★★【backtracking】    Time:  O(N*N!  Space: O(N) */
    //Enhance ===> String 或者 bit数  做 Memoization.
    public boolean canPartitionKSubsetsII(int[] nums, int k) {
        int target = 0;
        for(int i=0; i<nums.length; i++) {
            target += nums[i];
        }
        if(target%k != 0) return false;
        target = target/k;
        Arrays.sort(nums);
        return backtrack(0, nums, new boolean[nums.length], 0, target, k);
    }

    private boolean backtrack(int start, int[] nums, boolean[] used, int sum, int target, int k) {
        if(k==1)  return true; // no need to check the last subset
        if(sum == target) return backtrack(0, nums, used, 0, target, k-1);

        for(int i =start; i< nums.length; i++) {
            if(used[i]) continue;
            if(sum+ nums[i] > target) return false;
            used[i] = true;
            if(backtrack(i +1, nums, used, sum+nums[i], target, k)) return true;
            used[i] =false;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new SubsetKEqualSum().canPartitionKSubsets(new int[]{4,3,2,3,5,2,1}, 4));
    }
}


/*
https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
* */