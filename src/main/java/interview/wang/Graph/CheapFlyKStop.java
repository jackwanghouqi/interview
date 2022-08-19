package interview.wang.Graph;

import javafx.util.Pair;

import java.util.*;
/**
 * here are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 * 思路：
 *  （1） BFS O(E⋅K) E=number of flights 必须同时记录node以及对应stops
 *  （2） DFS 可以考虑，但是复杂度高O(V^2⋅K)  V=number of cities
 *  （3）【大爱克思戳】Dijkstra‘s Algorithm O(V2⋅log V)
 *  （4）Bellman-Ford O(K⋅E)
 *
 * */
public class CheapFlyKStop {
    /*【BFS】 O(K*E) O(V^2+V*K)*/
    public int findCheapestPriceBFS(int n, int[][] flights, int src, int dst, int k) {
        int adjMatrix[][] = new int[n][n];
        for (int[] flight: flights)  adjMatrix[flight[0]][flight[1]] = flight[2];
        // Shortest distances dictionary
        HashMap<Pair<Integer, Integer>, Long> distances = new HashMap<Pair<Integer, Integer>, Long>();
        distances.put(new Pair<Integer, Integer>(src, 0), 0L);
        int stops = 0;
        long result = Long.MAX_VALUE;
        LinkedList<Integer> bfsQueue = new LinkedList<Integer>(); bfsQueue.add(src);
        while (!bfsQueue.isEmpty() && stops < k + 1) {
            int length = bfsQueue.size();
            for (int i = 0; i < length; ++i) {
                int node = bfsQueue.poll();
                for (int nei = 0; nei < n; ++nei) {//neighbors
                    if (adjMatrix[node][nei] > 0) {
                        long dU = distances.getOrDefault(new Pair<Integer, Integer>(node, stops), Long.MAX_VALUE);
                        long dV = distances.getOrDefault(new Pair<Integer, Integer>(nei, stops + 1), Long.MAX_VALUE);
                        long wUV = adjMatrix[node][nei];
                        if (stops == k && nei != dst) continue;// when k stop, neighbor is not dst==>skip
                        long distance = dU + wUV;
                        if (distance < dV) {
                            distances.put(new Pair<Integer, Integer>(nei, stops + 1), distance);
                            bfsQueue.add(nei);
                            if (nei == dst) result = Math.min(result, distance);
                        }
                    }
                }
            }
            stops++;
        }
        return result == Long.MAX_VALUE ? -1 : (int) result;
    }

    /*【Bellman-Ford】 O(K*E) O(V)*/ //TODO 不懂啊 继续学习
    public int findCheapestPriceBellmanFord(int n, int[][] flights, int src, int dst, int K) {
        long[][] distances = new long[2][n];
        Arrays.fill(distances[0], Integer.MAX_VALUE);
        Arrays.fill(distances[1], Integer.MAX_VALUE);
        distances[0][src] = distances[1][src] = 0;
        for (int i = 0; i < K + 1; i++) {// K + 1 iterations of Bellman Ford
            for (int[] flight : flights) {
                int s = flight[0], d = flight[1], price = flight[2];
                long currentDistanceSrc = distances[1 - i & 1][s];
                long currentDistanceDst = distances[i & 1][d];
                if (currentDistanceSrc + price < currentDistanceDst) {
                    distances[i & 1][d] = currentDistanceSrc + price;
                }
            }
        }
        return distances[K & 1][dst] < Integer.MAX_VALUE ? (int) distances[K & 1][dst] : -1;
    }
    public static void main(String[] args) {
        System.out.println(new CheapFlyKStop().findCheapestPriceBellmanFord(4, new int[][]{{0,1,1},{0,2,5},{1,2,1},{2,3,1}},0,3,1));
    }
}
/*https://leetcode.com/problems/cheapest-flights-within-k-stops/*/