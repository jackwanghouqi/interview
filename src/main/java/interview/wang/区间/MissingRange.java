package interview.wang.区间;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges
 * 思路：遍历每个值，并比较前面的值
 * O(n)
 * */
public class MissingRange {
    /*条件：all elements are in the inclusive range*/
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int prev = lower - 1;
        for (int i = 0; i <= nums.length; i++) {
            int curr = (i < nums.length) ? nums[i] : upper + 1;
            if (prev + 1 <= curr - 1) {
                result.add(formatRange(prev + 1, curr - 1));
            }
            prev = curr;
        }
        return result;
    }

    /*条件：nums中的数值 有可能在给定区间外*/
    public List<String> findMissingRanges_Extention(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int prev = lower-1;
        for(int i = 0; i< nums.length; i++) {
            int curr = nums[i];
            if(curr>=lower && curr <=upper &&  curr - prev >1) { //【特殊：判断区间】
                result.add(formatRange(prev+1,curr-1));
            }
            prev = curr;
        }
        if(upper-prev> 0) result.add(formatRange(prev+1,upper));//【特殊：区间外判断】
        return result;
    }

    private String formatRange(int lower, int upper) {
        return (lower == upper) ? String.valueOf(lower) : "["+lower + "," + upper+"]";
    }

    public static void main(String[] args) {
        MissingRange tool = new MissingRange();
        int[] test = new int[] {5,11,35,77,78,79,88,89,100};
        List<String> list = tool.findMissingRanges_Extention(test, 1, 101);
        System.out.println(list);
        list = tool.findMissingRanges(test, 1, 101);
        System.out.println(list);
        list = tool.findMissingRanges_Extention(test, 5, 88);
        System.out.println(list);
        list = tool.findMissingRanges(test, 5, 88);
        System.out.println(list);
    }
}
