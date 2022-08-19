package interview.wang.SlidingWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Given an integer array nums and two integers k and t, return true if there are two distinct indices i and j in the array
 * such that abs(nums[i] - nums[j]) <= t and abs(i - j) <= k.
 *
 * 思路：1 binary search   Time complexity: O(nlog(min(n,k)))     Space complexity: O(min(n,k))
 *       2. bucket         Time complexity: O(n)                  Space complexity: O(min(n,k))
 *
 * */
public class DuplicateIII {
    //【Solution1】 Binary search tree
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();

        for(int i=0; i< nums.length; i++) {
            Integer s = set.ceiling(nums[i]);
            if(s!=null && s <=(long)nums[i]+t) return true;
            Integer g = set.floor(nums[i]);
            if(g!=null && nums[i]<=(long)g+t) return true;
            set.add(nums[i]);
            if(set.size()> k) set.remove(nums[i-k]);
        }
        return false;
    }

    //【Solution2】 Bucket
    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }
    public boolean bucket(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long)t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            // check if bucket m is empty, each bucket may contain at most one element
            if (d.containsKey(m))
                return true;
            // check the neighbor buckets for almost duplicate
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            // now bucket m is empty and no almost duplicate in neighbor buckets
            d.put(m, (long)nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }

    public static void main(String[] args) {
        DuplicateIII tool = new DuplicateIII();
        System.out.println(tool.containsNearbyAlmostDuplicate(new int[]{2147483640,2147483641}, 1, 100));
    }
}
