package interview.wang.区间;

import java.util.ArrayList;
import java.util.List;

public class SummaryRange {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if(nums.length==0) return result;//注意检查输入
        int start = nums[0];
        int end = nums[0];
        for(int i=1; i<nums.length;i++) {
            if(nums[i] -end > 1) {
                result.add(formatRange(start, end));
                start = nums[i];
            }
            end = nums[i];
        }
        result.add(formatRange(start, end));
        return result;
    }

    private String formatRange(int lower, int upper) {
        return (lower == upper) ? String.valueOf(lower) : "["+lower + "," + upper+"]";
    }

    public static void main(String[] args) {
        SummaryRange tool = new SummaryRange();
        int[] test = new int[] {5,11,35,77,78,79,88,89,100,101,102,103, 109};
        List<String> strings = tool.summaryRanges(test);
        System.out.println(strings);
    }
}
