package interview.wang.区间;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a 【list】 of disjoint 【intervals】.
 *  思路：
 *      1.直接套用InsertIntervals （存储int[][] 插入[a,a]）
 *      2. TreeSet存储顺序-->读取转换成Sorted Array遍历即可
 * */

public class StreamIntervals {
    TreeSet<Integer> treeSet = new TreeSet<>();

    public void addNum(int val) { // O(logN)
        treeSet.add(val);
    }

    public int[][] getIntervals() { // O(N)
        List<int[]> res = new ArrayList<>();
        Object[] arr = treeSet.stream().toArray();
        int idx = 0;
        while(idx < arr.length){
            int start = (int)arr[idx++];
            int end = start+1;
            while (idx < arr.length && (int)arr[idx] == end){
                idx++;
                end++;
            }
            res.add(new int[]{start, end-1});
        }
        return res.toArray(new int[][]{});
    }




    public static void main(String[] args) {
        StreamIntervals tool = new StreamIntervals();
        tool.addNum(5); print(tool.getIntervals());
        tool.addNum(1); print(tool.getIntervals());
        tool.addNum(3); print(tool.getIntervals());
        tool.addNum(2); print(tool.getIntervals());
        tool.addNum(6); print(tool.getIntervals());
    }
    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++)  System.out.print("["+arr[i][0]+","+arr[i][1]+"]"); System.out.println();
    }
}
