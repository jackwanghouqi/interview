package interview.wang.Graph;

import java.util.*;

public class AlienDictionary {
    /*【BFS】 O(C) O(C)*/
    public String alienOrderBFS(String[] words) {
        // Step 1: Create data structures and find all unique letters.
        Map<Character, List<Character>> adjList = new HashMap<>();
        Map<Character, Integer> counts = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                counts.putIfAbsent(c, 0);
                adjList.putIfAbsent(c, new ArrayList<>());
            }
        }
        // Step 2: Find all edges.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i]; String word2 = words[i + 1];
            if (word1.length() > word2.length() && word1.startsWith(word2)) return "";//invalid
            // Find the first non match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }
        // Step 3: Breadth-first search.
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (Character c : counts.keySet()) if (counts.get(c).equals(0)) queue.add(c);
        while (!queue.isEmpty()) {
            Character c = queue.remove();
            sb.append(c);
            for (Character next : adjList.get(c)) {
                counts.put(next, counts.get(next) - 1);
                if (counts.get(next).equals(0)) queue.add(next);
            }
        }
        if (sb.length() < counts.size()) return "";
        return sb.toString();
    }

    /*【避免用这个方法 BFS Recursion】 O(C) O(C)*/
    public String alienOrderDFS(String[] words) {
        Map<Character, List<Character>> reverseAdjList = new HashMap<>();
        Map<Character, Boolean> seen = new HashMap<>();
        // Step 0: Put all unique letters into reverseAdjList as keys.
        for (String word : words) {
            for (char c : word.toCharArray()) {
                reverseAdjList.putIfAbsent(c, new ArrayList<>());
            }
        }
        // Step 1: Find all edges and add reverse edges to reverseAdjList.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            if (word1.length() > word2.length() && word1.startsWith(word2)) return "";//invalid
            // Find the first non match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    reverseAdjList.get(word2.charAt(j)).add(word1.charAt(j));
                    break;
                }
            }
        }
        // Step 2: DFS to build up the output list.
        StringBuilder output = new StringBuilder();
        for (Character c : reverseAdjList.keySet()) {
            if (!dfs(c, reverseAdjList,seen,output)) return "";
        }
        return output.toString();
    }
    private boolean dfs(Character c, Map<Character, List<Character>> reverseAdjList, Map<Character, Boolean> seen, StringBuilder output) {
        if (seen.containsKey(c)) return seen.get(c); // If (false), a cycle was detected.
        seen.put(c, false);
        for (Character next : reverseAdjList.get(c)) {
            boolean result = dfs(next, reverseAdjList,seen,output);
            if (!result) return false;
        }
        seen.put(c, true);
        output.append(c);
        return true; // Return true iff no cycles detected.
    }
}
