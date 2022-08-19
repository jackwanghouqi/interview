package interview.wang.区间;

/**
 * given a set of closed intervals. Design an efficient algorithm for finding a minimum sized set of numbers that cover an interval
 * 思路：Greedy 每次找最大满足区间。直到到达或超过指定区间
 * */

public class CoverInterval {
    public int coverInterval(int[][] intervals, int[] interval) {
        int min = interval[0], max =interval[0], result = 0;
        while(max < interval[1]){
            for(int i = 0; i < intervals.length; i++){
                int l = intervals[i][0];
                int r = intervals[i][1];
                if(l <= min && r > max) max = r;
            }
            if(min == max) return -1;
            min = max;
            result++;

        }
        return result;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][] {{0,1},{1,3},{1,4},{0,3},{2,9},{3,10},{1,10},{55,100}};
        int[] interval = new int[] {1,55};
        CoverInterval tool = new CoverInterval();
        System.out.println(tool.coverInterval(intervals,interval));
    }
}
