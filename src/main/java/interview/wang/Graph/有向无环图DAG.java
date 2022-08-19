package interview.wang.Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * Directed Acyclic Graph TODO 忘记了
 * 【1】不存在回路
 * 【2】保证顶点在终点前面 Active On Vertex network（AOV）
 * 【3】所有前置条件安排在对应后置条件之前
 * */

public class 有向无环图DAG {
        private int V;// No. of vertices
        private ArrayList<ArrayList<Integer>> adj; //Adjacency List
        // Constructor
        有向无环图DAG(int v) {
            V = v;
            adj = new ArrayList<ArrayList<Integer> >(v);
            for (int i = 0; i < v; ++i)
                adj.add(new ArrayList<Integer>());
        }
        void addEdge(int v, int w) { adj.get(v).add(w); }

        // A recursive function used by topologicalSort
        void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
            Integer i; visited[v] = true; // Mark the current node as visited.
            Iterator<Integer> it = adj.get(v).iterator();
            while (it.hasNext()) {
                i = it.next();
                if (!visited[i]) topologicalSortUtil(i, visited, stack);
            }
            stack.push(v);
        }

        public void topologicalSort() {
            Stack<Integer> stack = new Stack<Integer>();

            boolean visited[] = new boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;

            // Call the recursive helper
            // function to store
            // Topological Sort starting
            // from all vertices one by one
            for (int i = 0; i < V; i++)
                if (visited[i] == false)
                    topologicalSortUtil(i, visited, stack);

            // Print contents of stack
            while (stack.empty() == false)
                System.out.print(stack.pop() + " ");
        }

        // Driver code
        public static void main(String args[])
        {
            // Create a graph given in the above diagram
            有向无环图DAG g = new 有向无环图DAG(6);
            g.addEdge(5, 2);
            g.addEdge(5, 0);
            g.addEdge(4, 0);
            g.addEdge(4, 1);
            g.addEdge(2, 3);
            g.addEdge(3, 1);

            System.out.println("Following is a Topological sort of the given graph");
            // Function Call
            g.topologicalSort();
        }
}


/**
 * https://baike.baidu.com/item/拓扑排序/5223807
 * */