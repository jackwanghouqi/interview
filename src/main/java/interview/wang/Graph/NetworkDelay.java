package interview.wang.Graph;

import javafx.util.Pair;
import java.util.*;

/**
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
 * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
 * 思路：
 *      直到最后计算出所有最短路径，才能比较出最长延迟时间
 * */
public class NetworkDelay {
    /*【BFS with HashMap】  O(N⋅E) O(N⋅E) E=total edges*/
    public int networkDelayTimeBFS(int[][] times, int n, int k) {
        Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();
        for (int[] time : times) {
            int source = time[0]; int dest = time[1]; int travelTime = time[2];
            adj.putIfAbsent(source, new ArrayList<>()); adj.get(source).add(new Pair(travelTime, dest));
        }
        int[] signalReceivedAt = new int[n + 1]; Arrays.fill(signalReceivedAt, Integer.MAX_VALUE); signalReceivedAt[k] = 0;
        Queue<Integer> q = new LinkedList<>(); q.add(k);
        while (!q.isEmpty()) {
            int currNode = q.remove();
            if (!adj.containsKey(currNode)) continue;
            for (Pair<Integer, Integer> edge : adj.get(currNode)) {  // Broadcast the signal to adjacent nodes
                int time = edge.getKey();
                int neighborNode = edge.getValue();
                int arrivalTime = signalReceivedAt[currNode] + time;
                if (signalReceivedAt[neighborNode] > arrivalTime) {
                    signalReceivedAt[neighborNode] = arrivalTime;
                    q.add(neighborNode);
                }
            }
        }
        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++)   answer = Math.max(answer, signalReceivedAt[i]);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    /*【Dijkstra's Algorithm 】O(N+ElogN) O(N+E) E=total edges */ /*https://leetcode.com/explore/featured/card/graph/622/single-source-shortest-path-algorithm/3862/*/
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();
        for (int[] time : times) {
            int source = time[0]; int dest = time[1]; int travelTime = time[2];
            adj.putIfAbsent(source, new ArrayList<>()); adj.get(source).add(new Pair(travelTime, dest));
        }
        int[] signalReceivedAt = new int[n + 1]; Arrays.fill(signalReceivedAt, Integer.MAX_VALUE);
        PriorityQueue<Pair<Integer, Integer>> pQueue = new PriorityQueue<Pair<Integer,Integer>> (Comparator.comparing(Pair::getKey));
        pQueue.add(new Pair(0, k));
        signalReceivedAt[k] = 0;// Time for starting node is 0
        while (!pQueue.isEmpty()) {
            Pair<Integer, Integer> topPair = pQueue.remove();
            int currNode = topPair.getValue(); int currNodeTime = topPair.getKey();
            if (!adj.containsKey(currNode) || currNodeTime > signalReceivedAt[currNode]) continue;
            for (Pair<Integer, Integer> edge : adj.get(currNode)) {
                int time = edge.getKey();  int neighborNode = edge.getValue();
                if (signalReceivedAt[neighborNode] > currNodeTime + time) {
                    signalReceivedAt[neighborNode] = currNodeTime + time;
                    pQueue.add(new Pair(signalReceivedAt[neighborNode], neighborNode));
                }
            }
        }
        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) answer = Math.max(answer, signalReceivedAt[i]);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public static void main(String[] args) {
        System.out.println('A'-'Z');
    }
}
