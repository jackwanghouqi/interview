package interview.wang.SlidingWindow;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 * https://leetcode.com/problems/contains-duplicate-ii/
 *
 * */
public class Duplicate {

    //【Solution 1】【linear search】  Time complexity : O(nmin(k,n))      Space complexity : O(1)
    public boolean containNearbyDuplicate(int[] nums, int k) {
        for(int i=0; i<nums.length; i++) {
            for(int j=Math.max(i-k,0); j<i; j++) {
                if(nums[i]==nums[j]) return true;
            }
        }
        return false;
    }

    //【Solution 2】【Binary search tree】   Time complexity : O(nlog(min(k,n)))   Space complexity : O(min(n,k))
    public boolean binarySearch(int[] nums, int k) {
        Set<Integer> set = new TreeSet<>();
        for(int i =0;i<nums.length;i++) {
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
            if(set.size() > k) set.remove(nums[i-k]);
        }
        return false;
    }

    //【Solution 3】【Hash Table】   Time complexity : O(n)   Space complexity : O(min(n,k))
    public boolean hashTable(int[] nums, int k){
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
            if(set.size() > k) set.remove(nums[i-k]);
        }
        return false;
    }

}
