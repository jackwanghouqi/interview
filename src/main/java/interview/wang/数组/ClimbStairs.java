package interview.wang.数组;

/**
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Solution 1: recursion
 * Solution 2: 从下到上计算要到达每层台阶 所有的可能。避免Recursion.
 * */
public class ClimbStairs {

    /*【Recursion with Memoization】 O(n) O(n)*/
    public int climbStairsRecursion(int n) {
        int memo[] = new int[n + 1];
        return _climbStairs(0, n, memo);
    }
    public int _climbStairs(int i, int n, int memo[]) {
        if (i > n) return 0;
        if (i == n) return 1;
        if (memo[i] > 0) return memo[i];
        memo[i] = _climbStairs(i + 1, n, memo) + _climbStairs(i + 2, n, memo);
        return memo[i];
    }

    /*【DP】 O(n) O(n)*/
    public int climbStairsDP(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= n; i++) dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    /*【Fibonacci】 O(n) O(1)*/
    public int climbStairs(int n) {
        if (n == 1) return 1;
        int first = 1, second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    /*【Cost】【Bottom-Up, Constant Space】 O(n) O(1)*/
    public int minCostClimbingStairs(int[] cost) {
        int downOne = 0, downTwo = 0;
        for (int i = 2; i < cost.length + 1; i++) {
            int temp = downOne;
            downOne = Math.min(downOne + cost[i - 1], downTwo + cost[i - 2]);
            downTwo = temp;
        }
        return downOne;
    }

    /*【Cost】【Bottom-Up Dynamic Programming (Tabulation)】 O(n) O(n)*/
    public int minCostClimbingStairsTabulation(int[] cost) {
        int minimumCost[] = new int[cost.length + 1]; //Top floor is +1;

        for (int i = 2; i < minimumCost.length; i++) {
            int takeOneStep = minimumCost[i - 1] + cost[i - 1];
            int takeTwoSteps = minimumCost[i - 2] + cost[i - 2];
            minimumCost[i] = Math.min(takeOneStep, takeTwoSteps);
            System.out.println("total cost:"+minimumCost[i]+" cost[i - 1]="+cost[i - 1]+" takeTwoSteps="+takeTwoSteps);
        }

        return minimumCost[minimumCost.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(new ClimbStairs().minCostClimbingStairsTabulation(new int[]{5,6,6,1}));
    }
}

/*
https://leetcode.com/problems/climbing-stairs/
https://leetcode.com/problems/min-cost-climbing-stairs/
*/