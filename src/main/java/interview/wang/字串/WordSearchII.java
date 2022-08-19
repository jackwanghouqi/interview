package interview.wang.字串;

import java.util.*;
//TODO 总是超时， 学学 Tries Node

public class WordSearchII {

    public List<String> findWords(char[][] board, String[] words) {
        ArrayList<String> result = new ArrayList<String>();
        // Step 1). Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;

            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;  // store words in Trie
        }

        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root, board, result);
                }
            }
        }

        return result;
    }

    private void backtracking(int row, int col, TrieNode parent, char[][] board, ArrayList<String> result) {
        Character letter = board[row][col];
        TrieNode currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= board.length || newCol < 0
                    || newCol >= board[0].length) {
                continue;
            }
            if (currNode.children.containsKey(board[newRow][newCol])) {
                backtracking(newRow, newCol, currNode, board, result);
            }
        }

        // End of EXPLORATION, restore the original letter in the board.
        board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }
    static class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        String word = null;
        public TrieNode() {}
    }





























    /*超时*/
    public List<String> findWordsII(char[][] board, String[] words) {
        List<String> results =new LinkedList<>();
        ArrayList<String> cache =new ArrayList<>();
        for(String word : words) cache.add(word);
        for(int i=0; i<board.length;i++) {
            for(int j=0; j < board[i].length;j++) {
                for(int x = cache.size()-1; x>=0; x--) {
                    String word = cache.get(x);
                    if(word.charAt(0)==board[i][j]) {
                        HashSet<Long> usedCellSet = new HashSet<Long>();
                        usedCellSet.add(calculateId(i,j));
                        if(backtrack(board, i, j, 0, results, word, usedCellSet)) cache.remove(x);
                    }
                }
            }
        }
        return results;
    }

    private boolean backtrack(char[][] board, int i, int j, int start, List<String> results, String word, HashSet<Long> usedCellSet) {
        if(start++ >= word.length()-1) {results.add(word); return true;}
        int rowMax = board.length-1;
        int columnMax = board[i].length-1;

        if(j-1>=0 && move(board, start, results, word, usedCellSet, i, j-1)) return true;
        if(j+1<=columnMax && move(board, start, results, word, usedCellSet, i, j+1)) return true;
        if(i-1>=0 && move(board, start, results, word, usedCellSet, i-1, j)) return true;
        if(i+1<=rowMax && move(board, start, results, word, usedCellSet, i+1, j)) return true;

        return false;
    }

    private boolean move(char[][] board, int start, List<String> results, String word, HashSet<Long> usedCellSet, int x, int y) {
        if(board[x][y]==word.charAt(start) && !usedCellSet.contains(calculateId(x,y))) {
            HashSet<Long> newSet = new HashSet<>(usedCellSet);
            newSet.add(calculateId(x,y));
            if(backtrack(board,x,y,start,results,word,newSet)) return true;
        }
        return false;
    }


    private long calculateId(int x, int y) {
        return x*100L+y;
    }

    public static void main(String[] args) {
        //char[][] test = new char[][]{{'o','a','a','n'},{'e','t','a','e'}, {'i','h','k','r'},{'i','f','l','v'}};
        //System.out.println(new WordSearchII().findWords(test, new String[]{"oath","pea","eat","rain"}));
        char[][] test = new char[][]{{'a','b','c'},{'a','e','d'}, {'a','f','g'}};
        //System.out.println(new WordSearchII().findWords(test, new String[]{"eaabcdgfa"}));
        System.out.println(new WordSearchII().findWords(test, new String[]{"eaafgdcba"}));
    }
}
