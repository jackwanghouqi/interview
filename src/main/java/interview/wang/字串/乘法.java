package interview.wang.字串;

import java.util.*;

public class 乘法 {
    /*【从右到左。一个数一个数按位置X10与另一个数按位置相乘，然后相加，最后反转】 O(M⋅(N+M)) O(N+M)*/
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        StringBuilder firstNumber = new StringBuilder(num1);
        StringBuilder secondNumber = new StringBuilder(num2);
        firstNumber.reverse(); secondNumber.reverse();//反转
        int N = firstNumber.length() + secondNumber.length(); //计算结果最大长度
        StringBuilder answer = new StringBuilder(); for (int i = 0; i < N; ++i) answer.append(0);//初始化
        for (int place2 = 0; place2 < secondNumber.length(); place2++) {
            int digit2 = secondNumber.charAt(place2) - '0';//第2个乘数 multiplier
            for (int place1 = 0; place1 < firstNumber.length(); place1++) {
                int digit1 = firstNumber.charAt(place1) - '0';//第1个乘数 multiplier
                int currentPos = place1 + place2;//计算位置
                int carry = answer.charAt(currentPos) - '0'; //当前值
                int multiplication = digit1 * digit2 + carry; //当前值+乘积
                answer.setCharAt(currentPos, (char)(multiplication % 10 + '0')); //更新当前值
                int value = (answer.charAt(currentPos + 1) - '0') + multiplication / 10; //计算进位值
                answer.setCharAt(currentPos + 1, (char)(value + '0'));//更新进位值
            }
        }
        if (answer.charAt(answer.length() - 1) == '0') answer.deleteCharAt(answer.length() - 1); //去除多余的0
        answer.reverse();//反转
        return answer.toString();
    }
}
