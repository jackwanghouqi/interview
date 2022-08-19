package interview.wang.Graph;

import java.util.*;
/**
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 * */
public class SwimInWater {
    /*【BFS】 O(N^2logN) O(N^2)*/
    int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        Set<Integer> seen = new HashSet();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((k1, k2) -> grid[k1 / N][k1 % N] - grid[k2 / N][k2 % N]);
        pq.offer(0); int ans = 0;
        while (!pq.isEmpty()) {
            int k = pq.poll(); // 只用一个 k 用来代表 一个 grid。k除以N 结果为 row, 余数为column
            int row = k / N, column = k % N;
            ans = Math.max(ans, grid[row][column]);
            if (row == N-1 && column == N-1) return ans;
            for (int i = 0; i < directions.length; ++i) {
                int neighborRow = row + directions[i][0], neighborColumn = column + directions[i][1];
                int neighborK = neighborRow * N + neighborColumn;
                if (0 <= neighborRow && neighborRow < N && 0 <= neighborColumn && neighborColumn < N && !seen.contains(neighborK)) {
                    pq.offer(neighborK);
                    seen.add(neighborK);
                }
            }
        }
        throw null;
    }
    /*【注意：必须条件 0 <= grid[i][j] < n^2】【Binary Search and DFS】 知道上限则可假设中间值。O(N^2logN) O(N^2)*/
    public int swimInWaterDFS(int[][] grid) {
        int lo = grid[0][0], hi = grid.length * grid.length;
        while (lo < hi) {
            int middle = lo + (hi - lo) / 2;
            if (possible(middle, grid)) {
                hi = middle;
            } else {
                lo = middle + 1;
            }
        }
        return lo;
    }
    public boolean possible(int middle, int[][] grid) {
        int N = grid.length;
        Set<Integer> seen = new HashSet(); seen.add(0);
        Stack<Integer> stack = new Stack(); stack.add(0);
        while (!stack.empty()) {
            int k = stack.pop();
            int row = k / N, column = k % N;
            if (row == N-1 && column == N-1) return true;
            for (int i = 0; i < directions.length; ++i) {
                int cr = row + directions[i][0], cc = column + directions[i][1];
                int ck = cr * N + cc;
                if (0 <= cr && cr < N && 0 <= cc && cc < N && !seen.contains(ck) && grid[cr][cc] <= middle) {
                    stack.add(ck);
                    seen.add(ck);
                }
            }
        }
        return false;
    }
}
/*https://leetcode.com/problems/swim-in-rising-water/*/