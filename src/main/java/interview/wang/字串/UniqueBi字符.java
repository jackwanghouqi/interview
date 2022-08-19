package interview.wang.字串;

import java.util.HashSet;

public class UniqueBi字符 {
    /*★★【Find Unique Binary String】 backtracking   Time:  <O(2^N)  Space: O(N) */
    public String findDifferentBinaryString(String[] nums) {
        if(nums.length ==0) return null;
        HashSet<String> numsSet = new HashSet<>();
        for(String num : nums) numsSet.add(num);

        String result = backtrack(nums[0].length(), numsSet, new StringBuilder());
        return result;
    }

    private String backtrack(int n, HashSet<String> numsSet, StringBuilder strCache) {
        if(strCache.length()==n) return numsSet.contains(strCache.toString())? null: strCache.toString();
        String result = backtrack(n, numsSet, strCache.append("0"));
        if(result != null) return result;
        strCache.deleteCharAt(strCache.length()-1);
        result = backtrack(n, numsSet, strCache.append("1"));
        strCache.deleteCharAt(strCache.length()-1);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new UniqueBi字符().findDifferentBinaryString(new String[]{"00","01"}));
        StringBuilder sb = new StringBuilder();
    }
}
