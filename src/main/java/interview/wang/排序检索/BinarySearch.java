package interview.wang.排序检索;

/**
 * 【Sorted Array】
 * */
public class BinarySearch {
    /*Iterative search*/
    int binarySearch(int[] arr, int key, int low, int high) {
        if(arr == null || arr.length == 0) return -1; //无效值
        while(low< high) {
            int mid = (low + high)/2;
            if (key == arr[mid]) return mid; //return the index
            else if (key > arr[mid]) low = mid + 1;
            else high = mid - 1;
        }
        return -1; //无效值
    }
    /*Recursive search*/
    int binarySearchRecursive(int[] arr, int key, int low, int high) {
        if(low > high) return -1;//无效值
        int mid = (low + high) / 2;
        if(key == arr[mid]) return mid;
        else if(key > arr[mid]) return binarySearch(arr, key, mid + 1, high);
        else return binarySearch(arr, key, low, mid - 1);
    }
    /*First*/
    static int first(int[] arr, int low, int high, int key) {
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low + 1) / 2;//TODO 为什么不是 (low + high)/2 ？
            int midVal = arr[mid];
            if (midVal < key) { low = mid + 1; }
            else if (midVal > key) { high = mid - 1; }
            else if (midVal == key) { ans = mid; high = mid - 1; }
        } return ans;
    }
    /*Last*/
    static int last(int[] arr, int low, int high, int key)  {
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low + 1) / 2;//TODO 为什么不是 (low + high)/2 ？
            int midVal = arr[mid];
            if (midVal < key) { low = mid + 1; }
            else if (midVal > key) { high = mid - 1; }
            else if (midVal == key) { ans = mid; low = mid + 1; }
        } return ans;
    }
    /*least greater （not equal!）*/
    static int leastGreater(int[] arr, int low, int high, int key) {
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low + 1) / 2;
            int midVal = arr[mid];
            if (midVal < key) { low = mid + 1; }
            else if (midVal > key) { ans = mid; high = mid - 1; }
            else if (midVal == key) { low = mid + 1; }
        } return ans;
    }
    /*least Lesser*/
    static int greatestLesser(int[] arr, int low, int high, int key) {
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low + 1) / 2;
            int midVal = arr[mid];
            if (midVal < key) { ans = mid; low = mid + 1; }
            else if (midVal > key) { high = mid - 1; }
            else if (midVal == key) { high = mid - 1; }
        } return ans;
    }
    /*Bisection平分 (按照给定函数)*/
    static final float EPSILON = (float)0.01; //容错值 -- 因为按照函数计算可能无法太精确
    static void bisection(double a, double b) {
        if (func(a) * func(b) >= 0) { System.out.println("Not assumed right a and b"); return; }
        double c = a;
        while ((b-a) >= EPSILON) {
            c = (a+b)/2;//middle
            if (func(c) == 0.0) {break;}
            else if (func(c)*func(a) < 0) {b = c;}
            else {a = c;}
        }
        System.out.printf("The value of root is : %.4f" ,c);
    }
    static double func(double x) { return x*x*x - x*x + 2; }
}
