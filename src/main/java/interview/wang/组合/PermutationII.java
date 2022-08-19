package interview.wang.组合;

import java.util.*;

/**
 * 求一组重复数组的所有排列组合 （可能重复）
 * 思路：Backtrack + HashMap<number,count>
 *
 * Time complexity : O(∑(k=1~N)​P(N,k))   where P(N,k) = N!/(N-k)! = N(N-1)...N(N-k+1)
 *  阶乘 factorial
 *  Space complexity : O(N) not counting the result set O(N!) Factorial  o(N*N!)
 * */

public class PermutationII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        HashMap<Integer, Integer> counter = new HashMap<>();//【记录重复数量】
        for (int num : nums) {
            if (!counter.containsKey(num)) counter.put(num, 0);
            counter.put(num, counter.get(num) + 1); //count
        }

        this.backtrack(new LinkedList<>(), nums.length, counter, results);
        return results;
    }

    protected void backtrack( LinkedList<Integer> list, Integer n, HashMap<Integer, Integer> counter, List<List<Integer>> results) {
        if (list.size() == n) {
            results.add(new ArrayList<Integer>(list));// 插入copy
            return;
        }

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) { //每个位置选Unique的数字，所以遍历Map
            Integer num = entry.getKey();
            Integer count = entry.getValue();
            if (count == 0) continue;
            list.addLast(num);
            counter.put(num, count - 1);
            backtrack(list, n, counter, results);
            list.removeLast();
            counter.put(num, count);
        }
    }
}
