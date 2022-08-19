package interview.wang.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *思路：
 * (1) 2 sum 问题。
 * (2) 排序后 K Sum 第一遍取值 降为 K-1 sum问题。
 * (3) 递归
 *
 * 复杂度：O(n(K-1))
 * */
public class SumK {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    public List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();

        // 循环到最后
        if (start == nums.length) {
            return res;
        }

        // 数组有序，所以计算平均值进行比较
        long average_value = target / k;
        //终止条件 （1）当前值大于平均值  （2）平均值小于最大值
        if  (nums[start] > average_value || average_value > nums[nums.length - 1]) {
            return res;
        }

        //直到k=2 计算返回 2sum
        if (k == 2) {
            return twoSum(nums, target, start);
        }

        //k>2 loop
        for (int i = start; i < nums.length; ++i) {
            if (i == start || nums[i - 1] != nums[i]) { //第一个 或者不重复
                //对每个i递归求subSet
                for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    //对每个subset 新建arryList 加入result list
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    //把求得的subset合并到新建的arryList
                    res.get(res.size() - 1).addAll(subset);
                }
            }
        }

        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, long target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = nums.length - 1;

        while (lo < hi) {
            int currSum = nums[lo] + nums[hi];
            if (currSum < target || (lo > start && nums[lo] == nums[lo - 1])) {
                ++lo;
            } else if (currSum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) {
                --hi;
            } else {
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
            }
        }

        return res;
    }
}
