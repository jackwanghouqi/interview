package interview.wang.排序检索;

/**
 * 思路：
 * (1) 分组(Divide) 递归(Recursion) 合并(merge)
 * (2) Copy 2段（sorted） ==> 新 数组 ==> 顺序插回
 *
 * 复杂度：O(nlgn)
 * */

public class MergeSort {
	void sort(int arr[], int l, int r) {
		//如果一个元素 ==> 无操作
		if (l < r) {
			// 中间值 （middle point）
			int m = (l + r) / 2;
			// 递归
			sort(arr, l, m);
			sort(arr, m + 1, r);
			// Merge
			merge(arr, l, m, r);
		}
	}
    
	void merge(int arr[], int l, int m, int r) {
		// 【1】【divide】
		int nL = m - l + 1;  int nR = r - m; //左右 长度
		int L[] = new int[nL]; //左 数组
		int R[] = new int[nR];//右 数组

		// 【2】【Copy data】
		for (int i = 0; i < nL; ++i) //循环copy左边
			L[i] = arr[l + i];
		for (int j = 0; j < nR; ++j)//循环copy右边
			R[j] = arr[m + 1 + j];

		// 【3】【初始值】
		int i = 0, j = 0;  //左右 初始
		int k = l;        //子数组 索引

		// 【4】【循环 直到 其中一个数组遍历结束】
		while (i < nL && j < nR) {
			if (L[i] <= R[j]) { //左小 --> 插到K位置
				arr[k] = L[i];
				i++;
			} else {            //右小 --> 插到K位置
				arr[k] = R[j];
				j++;
			}
			k++; //下一个 K位置
		}

		// 【5】【Copy 左】
		while (i < nL) {
			arr[k] = L[i];
			i++;
			k++;
		}

		// 【6】【Copy 右】
		while (j < nR) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}
}

/**
 * 适合链表， 数组需要额外空间
 * */