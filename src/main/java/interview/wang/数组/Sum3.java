package interview.wang.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Example:
 *Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 *
 *思路：
 * (1) Traverse 所有数字。每选取一个数字，剩下的就是2 Sum 问题
 * (2) 第一层遍历的值作为 2 Sum的 sum 值。 代入计算
 * (3) 需要有一个 容器 list 存储match 的数组
 * (4) 如果数组有序，则可以加速寻找并且方便避开重复值 Arrays.sort(nums)
 *
 *  复杂度：O(n2)
 * */

public class Sum3 {

    public List<List<Integer>> threeSum(int[] nums) {
        //sort
        Arrays.sort(nums);
        //结果存储器
        List<List<Integer>> res = new ArrayList<>();

        //loop 因为sum = 0 ==> 至少一个 <=0 ==> 第一层只需 traverse 负数和 0
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)

            //第一个值， 或者 避开重复
            if (i == 0 || nums[i - 1] != nums[i]) {
                //2Sum
                twoSum(nums, i, res);
            }
        return res;
    }

    void twoSum(int[] nums, int i, List<List<Integer>> res) {
        // 无需check 之前计算过的值。
        int lo = i + 1, hi = nums.length - 1;

        //循环收缩区间
        while (lo < hi) {
            //从剩余数组两边开始求和
            int sum = nums[i] + nums[lo] + nums[hi];
            //小于0 ==> 左边数太小 ==>左边向右移动一格
            if (sum < 0) {
                ++lo;
            //大于0 ==> 右边数太大 ==>右边向左移动一格
            } else if (sum > 0) {
                --hi;
            } else {
                //存储
                res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                //避开重复
                while (lo < hi && nums[lo] == nums[lo - 1])
                    ++lo;
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new Sum3().threeSum(new int[]{-1,0,1,2,-1,-4});
        System.out.println(list);
    }
}


/**
 * https://leetcode.com/problems/3sum/solution/
 * */

/**
 * 思路：记录Diff
 * */
class Sum3Closest {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int sz = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < sz && diff != 0; ++i) {
            int lo = i + 1;
            int hi = sz - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (Math.abs(target - sum) < Math.abs(diff)) {
                    diff = target - sum;
                }
                if (sum < target) {
                    ++lo;
                } else {
                    --hi;
                }
            }
        }
        return target - diff;
    }
}
