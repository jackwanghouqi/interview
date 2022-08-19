package interview.wang.排序检索;

import java.util.Collections;
import java.util.Vector;

/**
 * 思路：计算区间range，按照区间分成不同bucket。分别对各个Bucket排序。
 *
 * 优点：reduced the range for each sorting.
 *
 * */

public class BucketSort {
    static void bucketSort(float arr[], int n) {
        if (n <= 0) return; //n = arr.length;
        Vector<Float>[] buckets = new Vector[n];
        for (int i = 0; i < n; i++) buckets[i] = new Vector<Float>();

        for (int i = 0; i < n; i++) {
            float idx = arr[i] * n; //【按照值 计算bucket位置】
            buckets[(int)idx].add(arr[i]); // 放入bucket
        }
        //【分别对bucket排序】
        for (int i = 0; i < n; i++) Collections.sort(buckets[i]);
        //【将bucket数值按bucket次序放回数组】
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
    }

    public static void main(String args[]) {
        float arr[] = { (float)0.897, (float)0.565, (float)0.656, (float)0.1234, (float)0.665, (float)0.3434 };
        int n = arr.length;
        bucketSort(arr, n);
        for (float el : arr) System.out.print(el + " ");
    }
}

/*
* https://www.geeksforgeeks.org/bucket-sort-2/
* https://www.youtube.com/watch?v=geVyIsFpxUs
* */