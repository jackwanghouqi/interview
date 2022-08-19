package interview.wang.Java基本;

import java.math.BigDecimal;

public class Java二进制转换 {

    private static char[] array = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static String 进制n基(int number, int n) {
        StringBuilder result = new StringBuilder();
        while(number >0) {
            result.insert(0, array[number%n]);
            number /= n;
        }
        return result.toString();
    }


    public static BigDecimal BigDecimal(double number) {
        BigDecimal decimal = new BigDecimal(String.valueOf(number)); //String保留精度
        return decimal;
    }

    public static BigDecimal BigDecimal损失精度(double number) {
        BigDecimal decimal = new BigDecimal(number);
        return decimal;
    }


    public static void main(String[] args) {
        System.out.println(15);//十进制:15

        System.out.println(0b101);//二进制:5  （0b开头的）
        System.out.println(0e1011);//0.0
        System.out.println(011);//八进制:9   (0开头的)
        System.out.println(0x11C);//十六进制:284   （0x开头的）

        System.out.printf("%010x\n",7);//0000000007   按10位十六进制输出，向右靠齐，左边用0补齐
        System.out.printf("%010o\n",13);//0000000015    按10位八进制输出，向右靠齐，左边用0补齐

        System.out.printf("%x\n",7);//7   按16进制输出
        System.out.printf("%o\n",13);//15   按8进制输出

        System.out.println(Integer.toBinaryString(11));//1011 二进制
        System.out.println(Integer.toOctalString(11));//13 八进制
        System.out.println(Integer.toHexString(11));//b 十六进制

        System.out.println(Double.POSITIVE_INFINITY);
        System.out.println(Double.NEGATIVE_INFINITY);
        System.out.println(Double.POSITIVE_INFINITY-1);
        System.out.println(Double.POSITIVE_INFINITY-Double.POSITIVE_INFINITY);
        System.out.println(-Double.POSITIVE_INFINITY == Double.NEGATIVE_INFINITY);

        System.out.println(BigDecimal(0.1)); // 0.1
        System.out.println(BigDecimal损失精度(0.1)); //0.1000000000000000055511151231257827021181583404541015625
        System.out.println(0.1D); // 0.1
    }
}
