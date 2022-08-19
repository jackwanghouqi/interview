package interview.wang.Graph;

import java.util.*;
/**
 * 思路：将已知 Matched 的 单元坐标【i，j】 存在Queue中。 BFS Queue中的元素。
 * Time ： O(M * N)
 * Space : O(M * N)
 * TODO 比较复杂
 * */
public class WaterFlow {
    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private int numRows;
    private int numCols;

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();
        numRows = matrix.length; numCols = matrix[0].length;

        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();
        for (int i = 0; i < numRows; i++) {
            pacificQueue.offer(new int[]{i, 0});
            atlanticQueue.offer(new int[]{i, numCols - 1});
        }
        for (int i = 0; i < numCols; i++) {
            pacificQueue.offer(new int[]{0, i});
            atlanticQueue.offer(new int[]{numRows - 1, i});
        }

        // 【BFS】
        boolean[][] pacificReachable = bfs(pacificQueue, matrix);
        boolean[][] atlanticReachable = bfs(atlanticQueue, matrix);

        List<List<Integer>> commonCells = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    commonCells.add(List.of(i, j));
                }
            }
        }
        return commonCells;
    }

    //【BFS】
    private boolean[][] bfs(Queue<int[]> queue, int[][] matrix) {
        boolean[][] reachable = new boolean[numRows][numCols];
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            reachable[cell[0]][cell[1]] = true; //存储当前
            for (int[] dir : DIRECTIONS) { // 【4个 方向】 【BFS】   如果DFS，用递归
                int newRow = cell[0] + dir[0]; int newCol = cell[1] + dir[1];
                if (newRow<0 || newRow>=numRows || newCol<0 || newCol>=numCols || reachable[newRow][newCol]) continue;
                if (matrix[newRow][newCol] < matrix[cell[0]][cell[1]]) continue;
                //【Matched】
                queue.offer(new int[]{newRow, newCol});//存储 Matched
            }
        }
        return reachable;
    }


    /***************************************************************************************************/
    //【DFS】
    private void dfs(int row, int col, boolean[][] reachable, int[][] matrix) {
        // This cell is reachable, so mark it
        reachable[row][col] = true;
        for (int[] dir : DIRECTIONS) { // Check all 4 directions
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            // Check if new cell is within bounds
            if (newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols) {
                continue;
            }
            // Check that the new cell hasn't already been visited
            if (reachable[newRow][newCol]) {
                continue;
            }
            // Check that the new cell has a higher or equal height,
            // So that water can flow from the new cell to the old cell
            if (matrix[newRow][newCol] < matrix[row][col]) {
                continue;
            }
            // If we've gotten this far, that means the new cell is reachable
            dfs(newRow, newCol, reachable, matrix);
        }
    }
}
/*https://leetcode.com/problems/pacific-atlantic-water-flow/*/