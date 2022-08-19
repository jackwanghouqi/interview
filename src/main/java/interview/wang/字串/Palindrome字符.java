package interview.wang.字串;
import java.util.*;
public class Palindrome字符 {
    /**********************************求最长的****************************************************/
    /*★★【Longest Palindromic Substring】   复杂度： O(n2)*/
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i); int len2 = expand(s, i, i + 1);
            int len = Math.max(len1, len2);//比较单数对称和双数对称
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    private int expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) { l--; r++; }
        return r - l;
    }
    /**************************************求全部的************************************************/
    /*★★【All possible palindrome combinations】 backtrack + Dynamic programming   Time : O(N⋅2^N) Space : O(N^2) */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if(s == null || s.length() ==0) return result;
        //[A,B,B,A]
        boolean[][] cache = new boolean[s.length()][s.length()]; //[【start】【end】]

        dfs(result, s, 0, new LinkedList<>(), cache);
        return result;
    }
    void dfs(List<List<String>> result, String s, int start, LinkedList<String> currentList, boolean[][] cache) {
        if (start >= s.length()) result.add(new ArrayList<>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || cache[start + 1][end - 1])) {
                cache[start][end] = true;
                currentList.add(s.substring(start, end + 1));
                dfs(result, s, end + 1, currentList, cache);
                currentList.removeLast();
            }
        }
    }

    /*☆☆【All possible palindrome combinations】 brute force (backtrack)     Time : O(N⋅2^N) Space : O(N)*/
    public List<List<String>> partitionBruteForce(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        dfs(0, result, new LinkedList<String>(), s);
        return result;
    }
    void dfs(int start, List<List<String>> result, List<String> currentList, String s) {
        if (start >= s.length()) result.add(new ArrayList<String>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                currentList.add(s.substring(start, end + 1));
                dfs(end + 1, result, currentList, s);
                currentList.remove(currentList.size() - 1);
            }
        }
    }
    boolean isPalindrome(String s, int low, int high) {
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }


}
