package interview.wang.Graph;

public class IslandMax {
    /*★★【BFS】 Time : O(n*m)  Space O(1)*/
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int max = 0;
        for(int i=0; i < grid.length; i++) {
            for(int j =0; j < grid[i].length; j++){
                if(grid[i][j] == 1) {
                    int count = countAndMask(grid, i, j, 0);
                    max = Math.max(max, count);
                }
            }
        }
        return max;
    }

    private int countAndMask(int[][] grid, int i, int j, int count) {
        int maxRow = grid.length-1;
        int maxColumn = grid[0].length-1;

        if(i <0 || j < 0 || i > maxRow || j > maxColumn || grid[i][j] ==0) return count;

        grid[i][j] = 0; // or 存储到 【HashSet】
        count++;
        count = countAndMask(grid, i-1, j, count);
        count = countAndMask(grid, i+1, j, count);
        count = countAndMask(grid, i, j-1, count);
        count = countAndMask(grid, i, j+1, count);

        return count;
    }

    public static void main(String[] args) {

    }
}
