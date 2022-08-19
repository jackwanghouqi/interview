package interview.wang.排序检索;

import java.util.Arrays;

/**
 * 前提：正数数字比较
 * 思路：对每一个数的基数（digit in number）进行counting sort
 * stable 因为 choose base is fixed. eg. b=10
 * 时间复杂度: O(d(n+b)) base = 10 or 2 or 4 or 8 or any number
 * 空间复杂度：O(n)
 * */
public class RadixSort {
    static void radixsort(int arr[], int n) {
        int m = getMax(arr, n);
        //【counting sort】 on each digit
        for (int exp = 1; m / exp > 0; exp *= 10) //O(d)
            countSort(arr, n, exp); //O(n+10)
    }
    static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)  mx = arr[i];
        return mx;
    }
    static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n];
        int i;
        int count[] = new int[10];  Arrays.fill(count, 0);

        for (i = 0; i < n; i++)  count[(arr[i] / exp) % 10]++; //【累加记录出现次数】
        for (i = 1; i < 10; i++) count[i] += count[i - 1]; //【顺序累计以计算位置】
        for (i = n - 1; i >= 0; i--) { // loop 原数组
            int countPosition = (arr[i] / exp) % 10; //【计算count位置】
            output[count[countPosition] - 1] = arr[i]; //【找到output匹配位置】
            count[countPosition]--; //【reduce count on that position】
        }
        for (i = 0; i < n; i++) arr[i] = output[i]; //Copy
    }


    public static void main(String[] args) {
        int arr[] = { 170, 45, 75, 90, 802, 24, 2, 66 };     int n = arr.length;
        radixsort(arr, n);       print(arr, n);
    }
    static void print(int arr[],int n){for(int i=0;i<n;i++) System.out.print(arr[i]+" ");}
}

/*
* https://www.geeksforgeeks.org/radix-sort/
* https://www.youtube.com/watch?v=XiuSW_mEn7g
* */