package interview.core.sort;

public class MergeSort {

	public static void main(String[] args) {

	}
	
	// Main function that sorts arr[l..r] using
    // merge()
	void sort(int arr[], int l, int r) {
		if (l < r) {
			// Find the middle point
			int m = (l + r) / 2;

			// Sort first and second halves
			sort(arr, l, m);
			sort(arr, m + 1, r);

			// Merge the sorted halves
			merge(arr, l, m, r);
		}
	}
    
	void merge(int arr[], int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	/*public static void merge_sort(int[] arr) {
		int len = arr.length;
		int[] result = new int[len];
		merge_sort_recursive(arr, result, 0, len - 1);
	}
	
	static void merge_sort_recursive(int[] arr, int[] result, int start, int end) {
		if (start >= end)
			return;
		int len = end - start, mid = start + (len >> 1);
		int start1 = start, end1 = mid;
		int start2 = mid + 1, end2 = end;
		merge_sort_recursive(arr, result, start1, end1);
		merge_sort_recursive(arr, result, start2, end2);
		int k = start;
		while (start1 <= end1 && start2 <= end2)
			result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
		while (start1 <= end1)
			result[k++] = arr[start1++];
		while (start2 <= end2)
			result[k++] = arr[start2++];
		for (k = start; k <= end; k++)
			arr[k] = result[k];
	}


	public static void merge_sortNew(int[] arr) {
		int len = arr.length;
		int[] result = new int[len];
		int block, start;

		for (block = 1; block < len; block *= 2) {
			for (start = 0; start < len; start += 2 * block) {
				int low = start;
				int mid = (start + block) < len ? (start + block) : len;
				int high = (start + 2 * block) < len ? (start + 2 * block) : len;
				// 两个块的起始下标及结束下标
				int start1 = low, end1 = mid;
				int start2 = mid, end2 = high;
				// 开始对两个block进行归并排序
				while (start1 < end1 && start2 < end2) {
					result[low++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
				}
				while (start1 < end1) {
					result[low++] = arr[start1++];
				}
				while (start2 < end2) {
					result[low++] = arr[start2++];
				}
			}
			int[] temp = arr;
			arr = result;
			result = temp;
		}
		result = arr;
	}*/

}
