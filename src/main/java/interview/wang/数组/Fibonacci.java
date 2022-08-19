package interview.wang.数组;

import java.util.HashMap;

/**
 * 循环法>=递归(带字典)>矩阵连乘>递归(不带字典)
 * Iterative>=Recursion(带字典)>Matrix>Recursion(不带字典)
 * */
public class Fibonacci {

    /*★ Iterative(Bottom-up)  O(n)  from 0, 1, 2, 3 ...*/
    /*space  O(n)  【变量替换】 O(1)*/
    public int fibI(int n) {
        if(n<=1) return n;
        int[] fibs = new int[n+1]; //【变量替换】int a = 0, b=1;
        fibs[1] = 1;
        for(int i= 2; i<=n; i++) {
            fibs[i] = fibs[i-1] + fibs[i-2];//【变量替换】a = b; b = a + b;
        }
        return fibs[n];
    }
    /*★ Recursion(Top-down)  O(n^2) 若用【memoization】 O(n)  from n, n-1, n-2 ...*/
    public int fibII(int n) {
        if(n<=1) return n;
        // 【memoization】 外部 HashMap ==>  if(fibsMap.containsKey(n)) return fibsMap.get(n);
        return fibII(n-1)+ fibII(n-2); //【memoization】 存储 ==> fibsMap.put(n, result);
    }
    //【memoization】
    HashMap<Integer, Integer> fibsMap = new HashMap<>();

    /*★★★ TODO 错误不要用 Matrix Exponentiation Time: O(logN) space: O(logN)*/
    public int fibIII(int n) {
        if (n <= 1) return n;
        int[][] A = new int[][]{{1, 1}, {1, 0}};
        matrixPower(A, n - 1);
        return A[0][0];
    }

    void matrixPower(int[][] A, int n) {
        if (n <= 1) return;
        matrixPower(A, n / 2);
        multiply(A, A);
        int[][] B = new int[][]{{1, 1}, {1, 0}};
        if (n % 2 != 0) multiply(A, B);
    }

    void multiply(int[][] A, int[][] B) {
        A[0][0] = A[0][0] * B[0][0] + A[0][1] * B[1][0];
        A[0][1] = A[0][0] * B[0][1] + A[0][1] * B[1][1];
        A[1][0] = A[1][0] * B[0][0] + A[1][1] * B[1][0];
        A[1][1] = A[1][0] * B[0][1] + A[1][1] * B[1][1];
    }


    public static void main(String[] args) {
        System.out.println(new Fibonacci().fibI(13));//5
    }
}

/*https://leetcode.com/problems/fibonacci-number/*/