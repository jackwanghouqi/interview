package interview.wang.Java基本;

public class 位操作与或非 {
    /*【找出数组中唯一一个不重复的数】XOR 操作*/
    public int singleNumber(int[] nums) {
        int a = 0;
        for (int i : nums) a ^= i;
        return a;
    }

    /*【二进制 count 1】 与 操作*/
    public int CountBit(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1); // 只有最后一位是1 则结果 1 反之为 0
            n >>>= 1; //忽略符号的右移 左边一直补0 不管正负. 不存在 <<<
        }
        return count;
    }

    /*【找出0-消失的数】 XOR 操作*/
    public int missingNumber(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) missing ^= i ^ nums[i];
        return missing;
    }
    /*【找出0-消失的数】 加法 操作*/
    public int missingNumberSum(int[] nums) {
        int result = 0;
        for(int i=0; i<nums.length; i++) {
            result += i+1-nums[i];
        }
        return result;
    }

}
