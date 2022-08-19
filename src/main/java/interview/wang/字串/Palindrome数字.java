package interview.wang.字串;

public class Palindrome数字 {
    /*★★【Half Half 比较】  O(n)   如果 n = number of digits O(log10(n))*/
    public boolean isPalindrome(int x) {
        // 排除 负数 或者 尾数为0的非0数
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        //【左】<正序>   【右】<倒序>
        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        //对称(odd)   12321 ==> x = 12  revertedNumber = 123
        //对称(even)  5115 ==> x = 51  revertedNumber = 51
        //不对称      5114 ==> x = 5  revertedNumber = 411
        return x == revertedNumber || x == revertedNumber/10;
    }

    /*☆【转换为 String】 O(n)*/
    public boolean isPalindromeII(int x) {
        String str = String.valueOf(x);
        int start = 0;
        int end = str.length() - 1;
        while(start < end){
            if(str.charAt(start++) != str.charAt(end--)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String binaryString = Integer.toBinaryString(12345);
        System.out.println(binaryString);
    }
}
