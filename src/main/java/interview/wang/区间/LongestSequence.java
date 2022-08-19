package interview.wang.区间;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
* 思路：【HashSet存储所有数据】【按照连续+1的规则查询是否存在后续】【注意：如何确定开始数值】
* */
public class LongestSequence {

    public int longestConsective(int[] nums){
        Set<Integer> set = new HashSet<>();
        for(int num:nums) set.add(num);
         int result = 0;
         for(int num : nums) {
             if(!set.contains(num-1)) {//确定这个是开始数值（没有之前的数值）
                 int currentNum = num;
                 int currentLength = 1;
                 while(set.contains(++currentNum)) {
                     currentLength++;
                 }
                 result = Math.max(currentLength, result);
             }
         }
         return result;
    }
}
