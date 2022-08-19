package interview.wang.数组;

import java.util.*;

public class SumCombination {
    /*★★【Combination Sum from distinct integers】 backtracking   Time:  O(N^(T/M +1) Space: O(T/M) Target minimal value in the candidates */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        //can there be 0? no! no! no!
        Arrays.sort(candidates); // Sort is better
        backtrack(candidates, 0, result, target,new LinkedList<Integer>());
        return result;
    }
    public void backtrack(int[] candidates, int start, List<List<Integer>> result, int target, LinkedList<Integer> tempList) {
        if(target==0) {
            result.add(new ArrayList<>(tempList));
            return;
        } else if (target > 0) {
            for(int i=start; i< candidates.length; i++) {
                int currentInt = candidates[i];
                if(target < currentInt) return; // Sorted. can stop here
                tempList.add(currentInt);
                backtrack(candidates, i, result, target-currentInt, tempList);
                tempList.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new SumCombination().combinationSum(new int[]{2,3,6,7}, 7));
        System.out.println(new int[]{1,2}.equals(new int[]{1,2}));
    }
}
