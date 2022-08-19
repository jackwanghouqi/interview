package interview.wang.字串;
/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *  three operations permitted (1)Insert a character (2)Delete a character (3)Replace a character
 *
 *  思路：对于每个word1和word2的element,word1元素有三种选择，replace(如果相同则不操作)，插入，或删除。
 *  不能在一开始确定哪一种合适，但是可以根据前面的最优计算当前的最优。
 *  用二维数组列举并存储每两个对应值所计算的当前最优值。
 *  直到最后一个当前最优值 也就是结果。
 * */
public class EditCost {

    /*【DP】 O(mn) O(mn)*/
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        if (n * m == 0) return n + m; //if one string length is 0 return the sum(another length+0).
        int [][] d = new int[n + 1][m + 1];// array to store the convertion history
        for (int i = 0; i < n + 1; i++) d[i][0] = i;//表示若 word2 empty，word1 只能删除 最优次数必然跟i大小一致
        for (int j = 0; j < m + 1; j++) d[0][j] = j;//表示若 word1 empty，word1 只能插入 最优次数必然跟j大小一致
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) { //取前后左右的值。
                int left = d[i - 1][j] + 1;//删除
                int down = d[i][j - 1] + 1;//插入
                int left_down = d[i - 1][j - 1];//replace 或 no operation
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) //特殊情况，如果相等则不累计操作数
                    left_down += 1; //必是replace
                d[i][j] = Math.min(left, Math.min(down, left_down)); //取三种操作最少的

            }
        }
        return d[n][m];
    }
}
