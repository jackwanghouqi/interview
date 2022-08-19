package interview.wang.排序检索;

/**
 * 思路：【找最小值】【swap】
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 *
 * */
public class SelectionSort {
    void sort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            //【找最小值】
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx]) min_idx = j;

            //【swap】
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String args[]) {
        SelectionSort ob = new SelectionSort(); int arr[] = {64,25,12,22,11};
        ob.sort(arr); ob.printArray(arr);
    }
    void printArray(int arr[]) {
        int n = arr.length; for (int i=0; i<n; ++i) System.out.print(arr[i]+" "); System.out.println();
    }
}
