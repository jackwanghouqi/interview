package interview.wang.排序检索;

import java.util.Arrays;

public class CountingSort {
    /*O(n)
    条件： 【range of input data is not significantly greater than the number of objects to be sorted】
    思路：【找范围】【记录】【count】【累计值转化为最终位置】[复制]*/
    static void countSort(int[] arr) { //[4,4,1,2,7]
        int max = Arrays.stream(arr).max().getAsInt(); //7 O(n)
        int min = Arrays.stream(arr).min().getAsInt();//1 O(n)
        int range = max - min + 1; //7-1+1=7
        int position[] = new int[range]; // [0,0,0,0,0,0,0] 7个index对应范围  value对应真实的地址
        int output[] = new int[arr.length];//[0,0,0,0,0] 5个输出位置
        for (int i = 0; i < arr.length; i++) {//【记录每个元素重复数】 O(n)
            position[arr[i] - min]++; //对应值的 【位置】 上 累加 1 [0,0,0,(1),0,0,0]
        }  //count= [1,1,0,2,0,0,1]
        for (int i = 1; i < position.length; i++) {//【累计每个元素重复数, 得到具体坐标】 O(n)
            position[i] += position[i - 1]; // [1,(2),0,2,0,0,1]
        } //count= [1,2,2,4,4,4,5]
        for (int i = arr.length - 1; i >= 0; i--) {//【计算】 O(n)
            int p = arr[i] - min; // 真实地址
            output[position[p] - 1] = arr[i];  // [0,0,0,0,(7)] [0,(2),0,0,7] [(1),2,0,0,7]
            position[p]--; //[1,2,2,4,4,4,(4)]      [1,(1),2,4,4,4,4]     [(0),1,2,4,4,4,4]
        } //output=【1,2,4,4,7】 count=[0,1,2,2,4,4,4]
        for (int i = 0; i < arr.length; i++) { arr[i] = output[i]; } //【复制】O(n)
    }

    /* O(n+k) 确定 正数  且范围是0~256*/
    void sort(char arr[]) {
        int n = arr.length;
        char output[] = new char[n];
        int count[] = new int[256];
        for (int i = 0; i < 256; ++i) count[i] = 0;
        for (int i = 0; i < n; ++i) ++count[arr[i]]; // store count of each character
        for (int i = 1; i <= 255; ++i) count[i] += count[i - 1];
        // Build the output character array
        for (int i = n - 1; i >= 0; i--) { // To make it stable we are operating in reverse order.
            output[count[arr[i]] - 1] = arr[i];
            --count[arr[i]];
        }
        for (int i = 0; i < n; ++i) arr[i] = output[i]; // Copy
    }

    public static void main(String args[]) {
        char arr[] = { 'g', 'e', 'e', 'k', 's', 'f', 'o', 'r', 'g', 'e', 'e', 'k', 's' };
        new CountingSort().sort(arr);
        for(int i = 0; i < arr.length; ++i)System.out.print(arr[i]);System.out.println("");
        int[] arr2 = { -5, -10, 0, -3, 8, 5, -1, 10 };
        countSort(arr2);
        for(int i = 0; i < arr.length; i++){System.out.print(arr[i]+" ");}System.out.println("");
    }
}

/*https://www.geeksforgeeks.org/counting-sort/
* */