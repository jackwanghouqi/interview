package interview.wang.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/**
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
 * 解法：
 *  (1) 【克ra思考】Kruskal's Algorithm  -- greedy 排序，完全按照全部边中最小边的顺序 试图连接。
 *  (2) 【普瑞姆】Prim's Algorithm -- 任取一点，每次都找最近的点加入 （离集合任意一点最近）
 *      初始化【0，MAX，MAX】。
 *      记录A点坐标visited 【T，F,F】 计算剩下点与A距离 【0，8，6】 发现C点最近。 minDis = 0+6=6
 *      记录C点坐标visited 【T，F,T】 计算剩下点与C距离，如果小于与A点距离则覆盖 【0，7，6】。插入B minDis = 6+7=13
 *      记录B点坐标visited 【T，T,T】 如果有多的元素，以此类推。
 * */
public class MinCostPath {
    /*【Kruskal's Algorithm】 O(N^2⋅log(N))    O(N^2)*/
    public int minCostConnectPointsKruskal(int[][] points) {  //input {[0,0],[2,2],[3,10],[5,2],[7,0]}
        int n = points.length; ArrayList<int[]> allEdges = new ArrayList<>();
        for (int i = 0; i < n; ++i) { //用index代表点 0==[0,0]
            for (int j = i + 1; j < n; ++j) { //用index代表点 1 ==[2,2]
                int weight = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                int[] currEdge = {weight, i, j}; // i 和 j 代表两个点 points【i】([0,0]) -- points【j】([2,2])
                allEdges.add(currEdge);//收集所有的边 N(N-1)/2 约为 O(N)
            }
        }
        Collections.sort(allEdges, (a, b) -> Integer.compare(a[0], b[0])); //排序确保先取短边
        UnionFind uf = new UnionFind(n);
        int mstCost = 0;
        int edgesUsed = 0;//only stop at n - 1 只有n-1条边。多了就没意义。
        for (int i = 0; i < allEdges.size() && edgesUsed < n - 1; ++i) { //allEdges 有 N(N-1)/2 个元素。约为 O(N)
            int node1 = allEdges.get(i)[1];
            int node2 = allEdges.get(i)[2];
            int weight = allEdges.get(i)[0];
            if (uf.union(node1, node2)) { //worst case 每次执行 O(log(N^2)) 约为 O(log(N))
                mstCost += weight;
                edgesUsed++;
            }
        }
        return mstCost;
    }

    public int minCostConnectPointsPrim(int[][] points) {
        int n = points.length; int mstCost = 0; int edgesUsed = 0;
        boolean[] inMST = new boolean[n];
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE); minDist[0] = 0;
        while (edgesUsed < n) { // 最多需要 n-1条边
            int currMinEdge = Integer.MAX_VALUE;  int curr = -1;
            for (int i = 0; i < n; ++i) { // Pick least weight node which is not in MST.
                if (!inMST[i] && currMinEdge > minDist[i]) { //在未标记的 距离集中找最小点作为Curr。首次会选中 0点
                    currMinEdge = minDist[i];
                    curr = i;
                }
            }
            mstCost += currMinEdge; edgesUsed++; inMST[curr] = true;
            for (int i = 0; i < n; ++i) {
                if(!inMST[i]) {//没有被选中过的点 需要计算curr与所有点的距离，提供下一次选择的依据
                    int weight = Math.abs(points[curr][0] - points[i][0]) + Math.abs(points[curr][1] - points[i][1]);
                    minDist[i] = minDist[i] > weight ? weight : minDist[i]; //只需保留跟已知点的最短距离。
                }
            }
        }
        return mstCost;
    }

    public static void main(String[] args) {
        System.out.println(new MinCostPath().minCostConnectPointsPrim(new int[][]{{0,0},{2,2},{3,10},{5,2},{7,0}}));
    }
}

class UnionFind {
    public int[] group;
    public int[] rank;
    public UnionFind(int size) {
        group = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; ++i) group[i] = i;
    }
    public int find(int node) {
        if (group[node] != node) group[node] = find(group[node]);
        return group[node];
    }
    public boolean union(int node1, int node2) {
        int group1 = find(node1);
        int group2 = find(node2);
        if (group1 == group2) return false;
        if (rank[group1] > rank[group2]) {
            group[group2] = group1;
        } else if (rank[group1] < rank[group2]) {
            group[group1] = group2;
        } else {
            group[group1] = group2;
            rank[group2] += 1;
        }
        return true;
    }
}
