package interview.wang.排序检索;

/**
 * 思路：比item小的数值的总数决定了item的最终位置 ==> 可以通过计算右边小于当前值的数目计算当前值的最终位置。
 *
 * 时间复杂度 : always O(n^2)
 * 空间复杂度 : O(1) no extra space
 *
 * */

public class CycleSort {

    public static void cycleSort(int arr[], int n) {
        for (int cycle_start = 0; cycle_start <= n - 2; cycle_start++) {
            int item = arr[cycle_start]; //starting point
            int pos = cycle_start; //用来计算最终的正确位置
            for (int i = cycle_start + 1; i < n; i++)
                if (arr[i] < item) pos++; //【每发现一个小的数，最终位置移动一位】

            if (pos == cycle_start) continue; //位置没有移动
            while (item == arr[pos]) pos ++; //如果移动位置value相同则跳过。
            //【swap】
            if (item != arr[pos]) item = swap(item, arr, pos);
            // Rotate rest of the cycle
            while (pos != cycle_start) {
                pos = cycle_start;
                for (int i = cycle_start + 1; i < n; i++)
                    if (arr[i] < item) pos++;
                while (item == arr[pos]) pos++;
                //【swap】
                if (item != arr[pos]) item = swap(item, arr, pos);
            }
        }
    }

    private static int swap(int item, int[] arr, int pos) {
        int temp = item;
        item = arr[pos];
        arr[pos] = temp; //【写内存】【交换】
        return item;
    }

    public static void cycleSortII(int arr[], int n) {
        for (int cycle_start = 0; cycle_start <= n - 2; cycle_start++) {
            int item = arr[cycle_start]; //starting point
            int pos = -1; //用来计算最终的正确位置
            boolean started = false; //TODO 理解为什么第一次循环不同？为什么需要这个flag?
            while (pos != cycle_start) {
                pos = cycle_start;
                for (int i = cycle_start + 1; i < n; i++) {
                    if (arr[i] < item) pos++;
                }
                if (!started && pos == cycle_start) break; //位置没有移动
                started = true;
                while (item == arr[pos]) pos++;
                //【swap】
                if (item != arr[pos]) item = swap(item, arr, pos);
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = { 10, 8, 3, 9, 2, 4,10, 10 }; int n = arr.length;
        cycleSortII(arr, n); for (int i = 0; i < n; i++) System.out.print(arr[i] + " ");
    }
}
