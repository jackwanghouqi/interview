package interview.wang.区间;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 思路：【排序(开始时间)】【顺序比较前面的overlap】【大于merge，小于->新比较区间】
 * */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        //【Sort】
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) { //【没有overlap】
                merged.add(interval);//【新的last】
            } else {//【overlap】
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);//合并到last
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    //练习
    public int[][] mergeII(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for(int[] interval : intervals) {
            if(merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.getLast()[1]=Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}

