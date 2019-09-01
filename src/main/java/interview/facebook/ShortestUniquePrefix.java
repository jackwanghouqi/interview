package interview.facebook;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Find shortest unique prefix to represent each word in the list.
 * NOTE : Assume that no word is prefix of another. In other words, the representation is always possible.
 * */

public class ShortestUniquePrefix {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("zebra");list.add("dog");list.add("duck");list.add("dove");
		System.out.println(findprefixes(list));
	}
	
	private static ArrayList<String> findprefixes(ArrayList<String> list) {
		TrieNode root = buildTrie(list);
		ArrayList<String> result = new ArrayList<String>();
		for(String str : list) {
			result.add(getPrefix(str, root));
		}
		return result;
	}
	
	private static TrieNode buildTrie(ArrayList<String> list) {
		TrieNode root = new TrieNode(' ');
		for(String str : list) {
			root.add(str);
		}
		return root;
	}
	
	public static String getPrefix(String str, TrieNode root) {
		String prefix = "";
		for(int i=0; i < str.length(); i++) {
			TrieNode node = root.getChild(str.charAt(i));
			prefix += node.character;
			if(node.freq == 1) return prefix;
			root = node;
		}
		return prefix;
	}
	
	static class TrieNode {
		HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
		int freq = 1;
		char character;
		TrieNode parent;
		
		public TrieNode(char character) {
			super();
			this.character = character;
		}

		public void add(String str) {
			if(str == null || str.length()==0) return;
			char character = str.charAt(0);
			if(!children.containsKey(character)) {
				children.put(character, new TrieNode(character));
				children.get(character).parent = this;
			} else {
				children.get(character).freq ++;
			}
			children.get(character).add(str.substring(1));
		}
		
		public TrieNode getChild(char character) {
			return children.get(character);
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + character;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TrieNode other = (TrieNode) obj;
			if (character != other.character)
				return false;
			return true;
		}
	}
	

}
