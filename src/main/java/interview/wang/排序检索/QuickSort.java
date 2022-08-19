package interview.wang.排序检索;

/**
 * 思路：
 * (1) Divide and Conquer(分组克服)
 * (2) Partition (比Pivot小的全在Pivot左边，大的全在右边)
 * (3) Pivot (可以是 first / last/ median / Random)
 *
 * 复杂度：O(nlgn) --每次partition后 pivot 在中间
 *         O(n2)  --每次partition后 pivot 在最左或者最右边
 * */
public class QuickSort {
    void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            //Divide
            int pi = partition(arr, low, high);
            //Conquer
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    int partition(int[] arr, int low, int high) {
        //【Pivot】取最高位置
        int pivot = arr[high];
        int i = low; //i被动交换   j与P比较 如果小于则交换

        //所有小于Pivot的 【与i互换】
        for(int j = low+1; j < high; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        //【交换pivot至i】至此 ==> 【Pivot 左全小】 【右全大】
        swap(arr, i, high);
        return i; //交换后的 Pivot 位置
    }
    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

/**
 * 适合数组（in place） 链表其次
 * */