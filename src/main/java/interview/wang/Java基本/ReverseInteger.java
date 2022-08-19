package interview.wang.Java基本;

public class ReverseInteger {
    /*【反转十进制】 O(log(x)) O(1) */
    public int reverse十进制(int x) {
        int rev = 0;
        int checkOverflowPoint = Integer.MAX_VALUE/10;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > checkOverflowPoint || (rev == checkOverflowPoint && pop > 7)) return 0; //2147483647
            if (rev < checkOverflowPoint || (rev == checkOverflowPoint && pop < -8)) return 0; //-2147483648
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /*【反转二进制】 O(1) O(1) */
    public int reverse二进制(int x) {
        int ret = 0, power = 31;
        while (x != 0) {
            ret += (x & 1) << power;
            x = x >>> 1;
            power -= 1;
        }
        return ret;
    }
}
/*https://leetcode.com/problems/reverse-integer/*/