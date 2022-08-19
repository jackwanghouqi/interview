package interview.wang.排序检索;

/**
 * 【1】有序 sorted
 * 【2】大体均匀分布 uniformly distributed
 * */
public class 插值搜索Interpolation {
    public static int interpolationSearch(int arr[], int lo, int hi, int x) {
        int pos;
        if (lo <= hi && x >= arr[lo] && x <= arr[hi]) {

            pos = lo + (((hi - lo) / (arr[hi] - arr[lo])) * (x - arr[lo])); //公式

            //正好
            if (arr[pos] == x)
                return pos;

            //偏左
            if (arr[pos] < x)
                return interpolationSearch(arr, pos + 1, hi, x);

            // 偏右
            if (arr[pos] > x)
                return interpolationSearch(arr, lo, pos - 1, x);
        }
        return -1;
    }
}
