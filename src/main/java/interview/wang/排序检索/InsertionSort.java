package interview.wang.排序检索;

/**
 * 思路：
 * (1) 左边数组sorted，右边逐个比较 -- 插入       135789(6) ==>  135_789  (6) ==>  135(6)789
 * (2) 一开始左边第一个sorted
 *
 * 复杂度：O(n) ~ O(n2)
 * */
public class InsertionSort {
    public void sort(int arr[]) {
        //循环 第2个
        for (int i = 1; i < arr.length; ++i) {

            int key = arr[i]; //存值
            int j = i - 1; //开始比较

            //不断右移 直到 j=0 或 i 就位
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];//右移
                j--;
            }
            arr[j + 1] = key;//插入
        }
    }
}
