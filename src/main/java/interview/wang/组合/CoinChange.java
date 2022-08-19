package interview.wang.组合;

import java.util.Arrays;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Coin Change  == return fewest amount of change
 * Coin Change 2 == return number of all combinations
 *
 * */
    public class CoinChange {
    /*【Coin Change】【DP】Top down  (Recursion)*/
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    /*【Coin Change】【DP】Bottom up*/
    public int coinChangeBottomup(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /*【Coin Change 2】【DP】 O(N×amount) O(amount) */
    /**
     * 思路：每种货币在每个x=[0~amount]数值上的可能性累计之前的可能性,如果正好在amount有数值则match到...
     *  比如，amount=4, 货币为 2,4 的话. 对于Coin2来说可以满足 2和4. 所以在 amount2 和amount4 标注1
     *  然后在看Coin4，只能满足 4，但是之前已经标注了1，这里就+1. 最终结果在4上就是2种combinations
     *  而如果amount=5，则5不可能被2和4标注，则结果为0
     *  扩展---而如果amount=6，则6可以被2标注1，在6-4=2的位置被2标注了1，而当前也被2标注了1.则在coin4循环的时候变为2
     *  理解为： 6-4 位置的值保留的是Coin4之外的组合数目。如果6-4位置>1则说明4可以完美替换之前6-2=4的组合==>当前位置追加6-4的值
     *
     * （1）use 第一个Coin 把所有可能性填满 如果最后dp[amount+1] 为1 则用一个Coin的情况符合
     * （2）选择第二个coin累加前面的可能性。如果最后dp[amount+1] >0 则该数值即为两个Coin的组合数
     * */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int x = coin; x < amount + 1; ++x) {
                System.out.print("dp["+x+"]="+dp[x]);
                System.out.println("\tdp["+x+"-"+coin+"]="+dp[x - coin]);
                dp[x] += dp[x - coin];
                System.out.println("dp["+x+"]="+dp[x]);
                System.out.println("=====================");
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new CoinChange().change(4, new int[]{2,4}));
    }
}
