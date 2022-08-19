package interview.wang.数组;

import java.util.HashMap;
import java.util.Map;

/**
 * Example:
 *Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 *
 *
 * 思路：
 * (1) complement=sum-current  (2)力用HashMap 存储数值和该数值的索引
 * 复杂度：O(n)
 *
 * 思路2：【先sort】【从中间值的位置向两边双向查找】【左小移右】【右大移左】
 * 复杂度：不考虑Sort的复杂度O(n) 由于sort 最小 O(logN) 因此只有在已经排序的情况下考虑使用
 * */

public class Sum2 {
    public int[] twoSum(int[] nums, int target) {
        //Array 存储 数字和坐标
        Map<Integer, Integer> map = new HashMap<>();

        //loop 数组
        for (int i = 0; i < nums.length; i++) {
            //计算补充值
            int complement = target - nums[i];
            //寻找补充值
            if (map.containsKey(complement)) {
                //找到了，返回这组
                return new int[] { map.get(complement), i };
            }
            //存入map
            map.put(nums[i], i);
        }
        // In case there is no solution, we'll just return null
        return null;
    }
}

/**
 * https://leetcode.com/explore/interview/card/bloomberg/68/array-and-strings/2919/
 * */
