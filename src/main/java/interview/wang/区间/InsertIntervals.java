package interview.wang.区间;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * 思路：[unsorted？？排序] 【找到最后一个可能overlap的interval或者可以插入的点】【比较剩下的interval】
 * 时间复杂度 O(n)
 * */
public class InsertIntervals {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //【Sort】 need sort?
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int x = 0, n = intervals.length;
        LinkedList<int[]> result = new LinkedList<>();

        while(x < n && newInterval[0] > intervals[x][0])
            result.add(intervals[x++]);//【找到最后一个interval在newStart之前】

        if (result.isEmpty() || result.getLast()[1] < newInterval[0])
            result.add(newInterval);//【no overlap】
        else
            result.getLast()[1] = Math.max(result.getLast()[1], newInterval[1]);//【overlap】 --> 【merge】

        while (x < n) {
            int[] interval = intervals[x++];
            if(result.getLast()[1] < interval[0]) result.add(interval);//【no overlap】
            else result.getLast()[1] = Math.max(result.getLast()[1], interval[1]);//【overlap】
        }
        return result.toArray(new int[result.size()][2]);
    }
}
