package interview.wang.Graph;

import javafx.util.Pair;

import java.util.*;

public class RottingOrange {
    /*【BFS】*/
    public int orangesRottingBFS(int[][] grid) {
        int freshCount = 0;
        LinkedList<int[]> cache = new LinkedList<>();
        for(int i=0; i < grid.length; i++) {
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 2) {// 存 2 ==> rotten index
                    cache.add(new int[]{i,j}); //用【Pair】会更快
                } else if(grid[i][j] == 1) {
                    freshCount++; // 存 1 ==> fresh count
                }
            }
        }
        int time=0;
        while(freshCount > 0 && !cache.isEmpty()){
            int currentCount = cache.size();
            int currentFreshCount = freshCount;
            for (int i = 0; i < currentCount; i++) {
                int[] c = cache.removeFirst();
                currentFreshCount -= mark(grid, c[0]-1, c[1], cache)? 1:0;
                currentFreshCount -= mark(grid, c[0]+1, c[1], cache)? 1:0;
                currentFreshCount -= mark(grid, c[0], c[1]-1, cache)? 1:0;
                currentFreshCount -= mark(grid, c[0], c[1]+1, cache)? 1:0;
            }
            if(currentFreshCount < freshCount) {
                time++;
                freshCount = currentFreshCount;
            } else {
                break;
            }
        }
        return freshCount == 0 ? time : -1;
    }
    private boolean mark(int[][] grid, int i, int j, LinkedList<int[]> cache) {
        if(i<0 || i > grid.length-1 || j<0 || j>grid[0].length-1|| grid[i][j]!=1) return false;
        grid[i][j] = 2;
        cache.addLast(new int[]{i, j});
        return true;
    }

    /*【BFS】//另一种写法*/
    public int orangesRotting(int[][] grid) {
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque();
        int freshOranges = 0;
        int ROWS = grid.length, COLS = grid[0].length;

        for (int r = 0; r < ROWS; ++r)
            for (int c = 0; c < COLS; ++c)
                if (grid[r][c] == 2) queue.offer(new Pair(r, c));
                else if (grid[r][c] == 1) freshOranges++;

        queue.offer(new Pair(-1, -1));
        int minutesElapsed = -1; int[][] directions = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> p = queue.poll();
            int row = p.getKey();
            int col = p.getValue();
            if (row == -1) {
                minutesElapsed++; // We finish one round of processing
                if (!queue.isEmpty()) queue.offer(new Pair(-1, -1));
            } else {
                for (int[] d : directions) {
                    int neighborRow = row + d[0];
                    int neighborCol = col + d[1];
                    if (neighborRow >= 0 && neighborRow < ROWS && neighborCol >= 0 && neighborCol < COLS) {
                        if (grid[neighborRow][neighborCol] == 1) {
                            grid[neighborRow][neighborCol] = 2;
                            freshOranges--;
                            queue.offer(new Pair(neighborRow, neighborCol));
                        }
                    }
                }
            }
        }
        return freshOranges == 0 ? minutesElapsed : -1;
    }

    /*【Set Matrix Zeroes】 (1)先将第一行和第一列置0 (2)再根据第一行和第一列把整行整列置0 (M×N) O(1)*/
    public void setZeroes(int[][] matrix) {
        Boolean isCol = false; int R = matrix.length; int C = matrix[0].length;
        for (int i = 0; i < R; i++) {
            if (matrix[i][0] == 0) isCol = true;//是否要把第一列置0
            for (int j = 1; j < C; j++) {
                if (matrix[i][j] == 0) matrix[0][j] = 0; matrix[i][0] = 0; //只修改第一行和第一列的点
            }
        }
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
            }
        }
        if (matrix[0][0] == 0) { //是否要把第一行置0
            for (int j = 0; j < C; j++) matrix[0][j] = 0;
        }
        if (isCol) {
            for (int i = 0; i < R; i++) matrix[i][0] = 0;
        }
    }


    public static void main(String[] args) {
        int[][] testcase = new int[][]{
                {2,1,1},
                {1,1,0},
                {0,1,1}};
        System.out.println(new RottingOrange().orangesRotting(testcase));
    }
}
