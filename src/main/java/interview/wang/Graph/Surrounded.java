package interview.wang.Graph;
/**
 * 130. Surrounded Regions
 * 思路：只寻找边缘(boarder)的 'O' 并 mask to 'T' ==> DFS 'O'->'T'
 *
 * O(N*M)
 * */
public class Surrounded {
    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0) return;
        int bottomIndex = board.length-1;
        int rightIndex = board[0].length-1;

        for(int i = 0; i <=rightIndex; i++) {
            dfs(board, 0, i);
            dfs(board, bottomIndex, i);
        }
        for(int i = 0; i <=bottomIndex; i++) {
            dfs(board, i, 0);
            dfs(board, i, rightIndex);
        }
        for(int i=0; i<=bottomIndex; i++) {
            for(int j=0; j<=rightIndex; j++) {
                if(board[i][j]=='O') board[i][j] = 'X';
                if(board[i][j]=='T') board[i][j] = 'O';
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if(i<0 || i > board.length-1 || j<0 || j >board[0].length-1 || board[i][j] != 'O') return;
        board[i][j] = 'T';
        dfs(board, i-1, j);
        dfs(board, i+1, j);
        dfs(board, i, j-1);
        dfs(board, i, j+1);
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'},
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'}};
        new Surrounded().solve(board);
        //print(board);
    }

    private static void print(char[][] board) {
        for (int i= 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]+ " ");
            }
            System.out.println();
        }

    }
}

/*https://leetcode.com/problems/surrounded-regions/*/