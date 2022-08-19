package interview.wang.Graph;
/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 * */
public class LongestPathInc {
    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    /*【DFS + Memoization】 O(M*N) O(M*N)*/
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int[][] cache = new int[matrix.length][matrix[0].length];
        int ans = 0;
        for (int i = 0; i < matrix.length; ++i)
            for (int j = 0; j < matrix[0].length; ++j)
                ans = Math.max(ans, dfs(matrix, i, j, cache));
        return ans;
    }
    private int dfs(int[][] matrix, int i, int j, int[][] cache) { //search from matrix[i][j]
        if (cache[i][j] != 0) return cache[i][j];
        int m=matrix.length, n = matrix[0].length;
        for (int[] d : dirs) { //left,right,up,down
            int x = i + d[0], y = j + d[1];
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j])
                cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
        }
        return ++cache[i][j];
    }
}
