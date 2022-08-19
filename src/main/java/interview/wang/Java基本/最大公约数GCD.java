package interview.wang.Java基本;

public class 最大公约数GCD {
    public static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b%a, a);
    }

    // extended Euclidean Algorithm ax + by = gcd(a, b)
    public static int gcdExtended(int a, int b, int x, int y) {
        if (a == 0) {
            x = 0;
            y = 1;
            return b;
        }

        int x1 = 1, y1 = 1; // To store results of recursive call

        int gcd = gcdExtended(b % a, a, x1, y1);

        x = y1 - (b / a) * x1;
        y = x1;

        return gcd;
    }

    public static void main(String[] args)
    {
        int x = 1, y = 1;
        int a = 35, b = 15;
        int g = gcdExtended(a, b, x, y);
        System.out.println("gcd(" + a + " , " + b + ") = " + g);
        System.out.println("gcd".substring(1,2));
    }
}
