package interview.wang.排序检索;


/**
 * 不懂啊， 多看几遍！！！！！！！！！！！！！
 *
 * */

public class HeapSort {
    public void sort(int arr[]) {
        int n = arr.length;
        //【1】【Build heap】【Heapify】
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {  //O(N)
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0); //O(logN)
        }
    }

    void heapify(int arr[], int n, int i) {
        int largest = i; //root 初始位置
        int l = 2 * i + 1; // 左节点  TODO 不明白为什么这么分
        int r = 2 * i + 2; // 右节点

        //比较 左节点
        if (l < n && arr[l] > arr[largest])  largest = l;

        //比较 右节点
        if (r < n && arr[r] > arr[largest]) largest = r;
        //如果最大值不是 root ==> swap
        if (largest != i) {
            int swap = arr[i]; arr[i] = arr[largest]; arr[largest] = swap;
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int arr[] = { 3,2,1, 10, 8, 3, 9, 2, 4,10, 10 }; int n = arr.length;
        new HeapSort().sort(arr); for (int i = 0; i < n; i++) System.out.print(arr[i] + " ");
    }
}

/*
* https://www.geeksforgeeks.org/heap-sort/
* */