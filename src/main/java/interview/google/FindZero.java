package interview.google;

import java.util.function.Function;

public class FindZero {
    static double findZero(double x, int a, int b) {
        double result = Double.MAX_VALUE;
        if(b == a) {
            return Double.MAX_VALUE;
        }
        if(b > a) {
            int m = a + (b-a)/2;
            if(m == a) {
                return Double.MAX_VALUE;
            }
            if(result == x) {
                return result;
            } else {
                result = function(m);
                //check left
                if(a < m) {
                    double resultL = findZero(x, a, m);
                    result = Math.min(result, resultL);
                }
                //check right
                if(b > m) {
                    double resultR = findZero(x, m, b);
                    result = Math.min(result, resultR);
                }
            }
        }
        return result;
    }

    static double function(int x) {
        return Math.abs(x+3);
    }

    public static void main(String[] args) {
        System.out.println(findZero(0.1, 5, 11));
        System.out.println(1/2);
    }
}
