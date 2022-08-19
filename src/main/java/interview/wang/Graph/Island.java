package interview.wang.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 思路：遍历所有 M*N Grid, 一旦发现 Island就 【标注】 search所有neighbors 并【标注】
 *
 *  Search Neibhors 可以 【BST】【recursion】 如果用DFS则检索完4个neighbors先标注并存储他们的'1'neighbors
 *
 * */
public class Island {
    /*★★【BFS】 Time : O(n*m)  Space O(1)*/
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length ==0) return 0;

        int islandCounter = 0;

        for(int i=0;i<grid.length;i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] =='1') {
                    islandCounter++;
                    markVisited(grid, i, j);
                }
            }
        }
        return islandCounter;
    }

    private void markVisited(char[][] grid, int i, int j) {
        int maxRow = grid.length-1;
        int maxColumn = grid[0].length-1;
        if(i>maxRow || j > maxColumn || i < 0 || j < 0 || grid[i][j] == '0') return;
        grid[i][j] = '0';
        markVisited(grid, i-1, j);
        markVisited(grid, i+1, j);
        markVisited(grid, i, j-1);
        markVisited(grid, i, j+1);
    }

    /*★★【DFS】 Time : O(n*m)  Space O(1)~O(n)*/
    public int numIslandsDFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0'; // mark as visited
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
                            neighbors.add((row-1) * nc + col);
                            grid[row-1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row+1][col] == '1') {
                            neighbors.add((row+1) * nc + col);
                            grid[row+1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
                            neighbors.add(row * nc + col-1);
                            grid[row][col-1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col+1] == '1') {
                            neighbors.add(row * nc + col+1);
                            grid[row][col+1] = '0';
                        }
                    }
                }
            }
        }

        return num_islands;
    }
}
