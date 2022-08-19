package interview.wang.组合;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 注意：没有重复数。重复数请参考 PermutationII
 * Given an array nums of distinct integers, return all the possible permutations.
 *
 * Time complexity : O(∑(k=1~N)​P(N,k))   where P(N,k) = N!/(N-k)! = N(N-1)...N(N-k+1)
 *  阶乘 factorial
 * Space complexity : O(N!)
 * */
public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList();
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());

        int n = nums.length;
        backtrack(0, n, list, result);
        return result;
    }

    public void backtrack(int first, int n, List<Integer> list, List<List<Integer>> result) {
        if (first == n) // 【排列结束】
            result.add(new ArrayList<Integer>(list)); //注意: 要复制一个新的List

        for (int i = first; i < n; i++) {
            Collections.swap(list, first, i); //交换 注意：原始排列本身应该被包括==>所以第一次是本位交换（不变）
            backtrack(first+1, n, list, result); //继续交换下一个，直到排列结束
            Collections.swap(list, first, i); //复原，继续下一轮交换
        }
    }
}

/*
*  排列 阶乘 学习：
*   ABCDE 直线排列：5*4*3*2*1 = 5! (Factorial)
*   ABCDE 可重复排列：5*5*5*5*5 = 5exp5
*   ABCDE 环状排列：4*3*2*1 = 4! (Factorial) 原因：环状中，不管任何排序都可以看作A（或者任意一个元素）开始， 那么用来参照的A就等于无效了。
*
*  扩展：
*   AB必须相邻： 2! X 3 !
*   AB必须不相邻： 5! - (2! X 3 !)
*   A必须第一：等于环状情况 4！
*
* https://www.youtube.com/watch?v=1hqkR3bEw_Q
* https://www.youtube.com/watch?v=J4d1g9VBP-8
*
* TODO 继续做Back tracking的所有题 https://www.youtube.com/watch?v=pfiQ_PS1g8E&list=PLot-Xpze53lf5C3HSjCnyFghlW0G1HHXo
* */
