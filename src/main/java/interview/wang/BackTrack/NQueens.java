package interview.wang.BackTrack;

import java.util.*;

public class NQueens {
    /*★★★【Backtrak】每行，列，对角线，反对角线都是唯一。 Time:  O(N!)    Space: O(N^2) for result  */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new LinkedList<>();
        backtrack(result, 0, n, new HashSet<Integer>(), new HashSet<Integer>(), new HashSet<Integer>(), new HashMap<Integer,Integer>());
        return result;
    }

    private void backtrack(List<List<String>> result, int row, int n, HashSet<Integer> columns,HashSet<Integer> diagonals,HashSet<Integer> antiDiagonals, Map<Integer,Integer> grids) {
        if(row == n) {
            result.add(generateBoardResult(grids, n));
            return;
        }
        for(int i = 0; i < n; i++) {
            if(!columns.contains(i) && !diagonals.contains(row-i) && !antiDiagonals.contains(row+i)) {
                grids.put(row,i);
                columns.add(i);
                diagonals.add(row-i);
                antiDiagonals.add(row+i);
                backtrack(result, row+1, n, columns, diagonals, antiDiagonals, grids);
                grids.remove(row);
                columns.remove(i);
                diagonals.remove(row-i);
                antiDiagonals.remove(row+i);
            }
        }
    }

    public List<String> generateBoardResult(Map<Integer,Integer> grids, int n) {
        List<String> board = new ArrayList<>(n);
        for(int i=0;i<n;i++) {
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<n;j++) {
                sb.append(grids.get(i)==j ? "Q":".");
            }
            board.add(sb.toString());
        }
        return board;
    }

    public int solveNQueensII(int n) {
        return backtrack(0, n, new HashSet<Integer>(), new HashSet<Integer>(), new HashSet<Integer>(), new HashMap<Integer,Integer>());
    }

    private int backtrack(int row, int n, HashSet<Integer> columns,HashSet<Integer> diagonals,HashSet<Integer> antiDiagonals, Map<Integer,Integer> grids) {
        if(row == n) {
            return 1;
        }
        int count =0;
        for(int i = 0; i < n; i++) {
            if(!columns.contains(i) && !diagonals.contains(row-i) && !antiDiagonals.contains(row+i)) {
                grids.put(row,i);
                columns.add(i);
                diagonals.add(row-i);
                antiDiagonals.add(row+i);
                count+= backtrack(row+1, n, columns, diagonals, antiDiagonals, grids);
                grids.remove(row);
                columns.remove(i);
                diagonals.remove(row-i);
                antiDiagonals.remove(row+i);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new NQueens().solveNQueensII(4));
    }
}
