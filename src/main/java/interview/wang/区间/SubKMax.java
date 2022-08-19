package interview.wang.区间;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Given an array and an integer K.
 * We need to find the maximum of every segment of length K which has no duplicates in that segment.
 * 【思路】 1.like sliding window. every time move 1 element (remove1 then add 1)
 *          2. Use hashmap to store k-1 elements (循环 {+1-->判断-->-1})
 *          3.每次移动后都要删除最后一位
 *
 * */
public class SubKMax {
    /*【】O(N Log K)*/
    static void findMax(int[] A, int N, int K) {
        //save same element count
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < K - 1; i++)
            if (countMap.containsKey(A[i]))
                countMap.put(A[i], 1 + countMap.get(A[i]));
            else
                countMap.put(A[i], 1);
       //Add all elements(count 1) to tree map
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        for (Map.Entry x : countMap.entrySet()) {
            if ((Integer)x.getValue() == 1)
                treeSet.add((Integer)x.getKey());
        }
        for (int i = K - 1; i < N; i++) {
            // Process K-th element of current window
            if (countMap.containsKey(A[i])) countMap.put(A[i], 1 + countMap.get(A[i]));
            else countMap.put(A[i], 1);

            if (countMap.get(A[i]) == 1) treeSet.add(A[i]);
            else treeSet.remove(A[i]);
            // If there are no distinct elements in current window
            if (treeSet.size() == 0) System.out.println("Nothing");
            // Set is ordered and last element of set gives us maximum element.
            else System.out.println(treeSet.last());
            // Remove first element of current window before next iteration.
            int x = A[i - K + 1];
            countMap.put(x, countMap.get(x) - 1);
            if (countMap.get(x) == 1) treeSet.add(x);
            if (countMap.get(x) == 0) treeSet.remove(x);
        }
    }
    public static void main(String args[]) {
        int[] a = { 1, 2, 2, 3, 3 };
        int n = a.length;
        int k = 3;
        findMax(a, n, k);
    }
}

//TODO 继续学习
/**
 * https://www.geeksforgeeks.org/binary-search-tree-data-structure/
 * */