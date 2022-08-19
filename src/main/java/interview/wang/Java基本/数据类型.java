package interview.wang.Java基本;

/*
 * 精度 Precision
 * */

public class 数据类型 {
    byte b = Byte.MAX_VALUE; // 8位 -128 ~ 127
    short s = Short.MAX_VALUE; //16位 -32768 ~ 32767
    int i = Integer.MAX_VALUE; //32位 -21,4748,3648 ～ 21,4748,3647
    long l = Long.MAX_VALUE; // 64位 -2(63)～2(63)-1，整型中能表示最大的类型
    float f = Float.MAX_VALUE; //32位  小数点后可以保留 7 位有效数字
    double d = Double.MAX_VALUE; //64位 小数点后可以保留 15 位有效数字

    char c = Character.MAX_VALUE; // 16位 '/uFFFF'  新建数组初始值'' == int 0
    boolean bool = true; // 8位 true or false  新建数组初始值false


    public static void main(String[] args) {
        Boolean bool = true;
        char[] l=new char[1];
        System.out.println((int)l[0]);
        prime质数();
        factorial阶乘(31); //计算 int 32会溢出
    }

    // 1不是质数，（破坏因数分解factorization的唯一解,因为可以不断乘以无数个1）
    private static void prime质数() {
        int i =0; int num =0; String  primeNumbers = "";
        for (i = 1; i <= 100; i++) {
            int counter=0;
            for(num =i; num>=1; num--) {
                if(i%num==0) counter++;
            }
            if (counter ==2) primeNumbers = primeNumbers + i + " ";
        }
        System.out.println("Prime numbers from 1 to 100 are :");
        System.out.println(primeNumbers);
    }

    private static void factorial阶乘(int n) {
        int result = 1;
        for(int i=1; i<=n; i++) {
            result *= i;
        }
        System.out.println(result);
    }

}
