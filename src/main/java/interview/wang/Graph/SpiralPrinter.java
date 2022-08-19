package interview.wang.Graph;
public class SpiralPrinter {
	/*【spiral print】 O(M⋅N) O(1)*/
	public void spiralPrint(String[][] arr) {
		int startX = 0, endX = arr[0].length - 1; int startY = 0, endY = arr.length - 1;
		while (startX <= endX && startY <= endY) {
			int i;
			for (i = startX; i <= endX; i++) printElement(arr, startY, i);// 【top】 row
			startY++;// increase row start index
			if (startY > endY) break;// stop here if no more row to process. for the scenario with odd number of rows
			for (i = startY; i <= endY; i++) printElement(arr, i, endX);// 【right】 column
			endX--; // decrease column end index
			for (i = endX; i >= startX; i--) printElement(arr, endY, i);// 【bottom】 row
			endY--;// decrease row end index
			for (i = endY; i >= startY; i--) printElement(arr, i, startX);// 【left】 column
			startX++;// increase column start index
		}
		System.out.println();
	}

	/*【rotate】 O(M⋅N) O(1)*/
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < (n + 1) / 2; i ++) {
			for (int j = 0; j < n / 2; j++) {
				int temp = matrix[n - 1 - j][i];
				matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
				matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
				matrix[j][n - 1 - i] = matrix[i][j];
				matrix[i][j] = temp;
			}
		}
	}

	public static void main(String[] args) {
		String[][] arr0 = { 
				{ "1", "2",  "3" }, 
				{ "5", "6",  "7" },
				{ "9", "10", "11" }, 
				{ "a", "b",  "c" } };
		String[][] arr1 = { 
				{ "1", "2",  "3",  "4" }, 
				{ "5", "6",  "7",  "8" },
				{ "9", "10", "11", "12" } };
		String[][] arr2 = { 
				{ "A", "b", "c", "d", "e", "1" },
				{ "F", "g", "h", "l", "j", "2" },
				{ "K", "l", "m", "n", "o", "3" },
				{ "P", "q", "r", "s", "t", "4" } };
		SpiralPrinter printer = new SpiralPrinter();
		printer.spiralPrint(arr0);
		printer.spiralPrint(arr1);
		printer.spiralPrint(arr2);
	}
	private void printElement(String[][] arr, int a, int b) {
		System.out.print(arr[a][b].toLowerCase() + " ");
	}
}