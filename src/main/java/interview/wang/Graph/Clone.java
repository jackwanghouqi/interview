package interview.wang.Graph;

import java.util.*;

/**
 * Clone (Deep copy) graph
 * 思路：用HashMap存储【Old_Node,New_Node】 DFS
 *
 * Time: O(N+M) N nodes, M edges
 * Space: O(N)
 *
 *
 * */

public class Clone {
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        HashMap<Node, Node> nodeMap = new HashMap<>();//【Old_Node,New_Node】
        return cloneGraph(node, nodeMap);
    }

    /*★ 【DFS】 HashMap*/
    private Node cloneGraph(Node node, HashMap<Node, Node> nodeMap) {
        Node nodeCloned = new Node(node.val);
        nodeMap.put(node, nodeCloned);
        for(Node neighbor : node.neighbors) {
            if(!nodeMap.containsKey(neighbor)) cloneGraph(neighbor, nodeMap);
            Node neighborCloned = nodeMap.get(neighbor);
            nodeCloned.neighbors.add(neighborCloned);
        }
        return nodeCloned;
    }
}

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
