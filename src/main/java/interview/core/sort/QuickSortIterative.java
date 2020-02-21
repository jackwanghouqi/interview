package interview.core.sort;

import java.util.Arrays;
import java.util.Stack;

/**
 * Java Program to implement Iterative QuickSort Algorithm, without recursion. * * @author WINDOWS 8
 * */
public class QuickSortIterative {
    public static void main(String args[]) {

        int[] unsorted = {34, 32, 43, 12, 11, 32, 22, 21, 32};
        System.out.println("Unsorted array : " + Arrays.toString(unsorted));

        iterativeQsort(unsorted);
        System.out.println("Sorted array : " + Arrays.toString(unsorted));
    }


    /*
     * iterative implementation of quicksort sorting algorithm.
     * 1. 用 stack 存储 起始和结束点
     * 2. 存储一个起始和结束点 （0，length）
     * 3. 循环所存储的点，每次抽取top两个点，分别为起始点和结束点
     * 4. 对次抽取的区间进行Partition （之前要检查起始点和结束点是否合理或是两个点以上的区间）
     * 5. 存储每次Partition之后的两对起始点和结束点
     */
    public static void iterativeQsort(int[] numbers) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(numbers.length);

        while (!stack.isEmpty()) {
            int end = stack.pop();
            int start = stack.pop();
            if (end - start < 2) {
                continue;
            }
            int p = start + ((end - start) / 2);
            p = partition(numbers, p, start, end);

            stack.push(p + 1);
            stack.push(end);

            stack.push(start);
            stack.push(p);

        }
    }

    /*
     * Utility method to partition the array into smaller array, and
     * comparing numbers to rearrange them as per quicksort algorithm.
     */
    private static int partition(int[] input, int position, int start, int end) {
        int l = start;
        int h = end - 2;
        int piv = input[position];
        swap(input, position, end - 1);

        while (l < h) {
            if (input[l] < piv) {
                l++;
            } else if (input[h] >= piv) {
                h--;
            } else {
                swap(input, l, h);
            }
        }
        int idx = h;
        if (input[h] < piv) {
            idx++;
        }
        swap(input, end - 1, idx);
        return idx;
    }

    /**
     * Utility method to swap two numbers in given array
     *
     * @param arr - array on which swap will happen
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
