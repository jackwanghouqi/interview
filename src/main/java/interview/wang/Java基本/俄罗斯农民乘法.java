package interview.wang.Java基本;

/**
 * 俄罗斯农民乘法 Russian peasant multiplication
 *  定义一个result
 * 【1】A 不断乘以2， B 不断除以2 (循环条件 B>0)
 * 【2】如果B是奇数（odd）, result = result + A
 * 【3】循环结束后，返回result.
 * */
public class 俄罗斯农民乘法 {
    static int russianPeasant(int a, int b) {
        int res = 0;

        while (b > 0) {
            if ((b & 1) != 0)
                res = res + a;
            a = a << 1; //a 乘以 2
            b = b >> 1; // b 除以 2
        }
        return res;
    }

    public static void main (String[] args)
    {
        System.out.println(russianPeasant(-18, 5));
        System.out.println(russianPeasant(20, 2));
    }
}
