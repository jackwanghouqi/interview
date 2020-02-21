package interview.jpmc;
public class SpiralPrinter {

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
		printer.print(arr0);
		printer.print(arr1);
		printer.print(arr2);
	}

	public void print(String[][] arr) {

		int startX = 0, endX = arr[0].length - 1;
		int startY = 0, endY = arr.length - 1;

		while (startX <= endX && startY <= endY) // loop
		{
			int i;
			// top row
			for (i = startX; i <= endX; i++) {
				printElement(arr, startY, i);
			}

			// increase row start index
			startY++;

			// stop here if no more row to process. for the scenario with odd
			// number of rows
			if (startY > endY) {
				break;
			}
			// right column
			for (i = startY; i <= endY; i++) {
				printElement(arr, i, endX);
			}
			// decrease column end index
			endX--;

			// bottom row
			for (i = endX; i >= startX; i--) {
				printElement(arr, endY, i);
			}
			// decrease row end index
			endY--;

			// left column
			for (i = endY; i >= startY; i--) {
				printElement(arr, i, startX);
			}
			// increase column start index
			startX++;
		}
		System.out.println();
	}

	private void printElement(String[][] arr, int a, int b) {
		System.out.print(arr[a][b].toLowerCase() + " ");
	}

}