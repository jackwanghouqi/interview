package interview.core.sort;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5,7,6,3,0,1,2,5};
        sort2(arr);
        for (int number:arr) {
            System.out.println(number);
        }
    }

    static void sort(int arr[]) {
        int n = arr.length;
        for(int i=0; i < n-1; i++) {
            for(int j=0; j < n-i-1; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                }
            }
        }
    }

    static void sort2(int arr[]) {
        int n = arr.length;
        for(int i=0; i < n-1; i++) {
            for(int j=n-1; j > i; j--) {
                if(arr[j] < arr[j-1]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;

                }
            }
        }
    }
}
