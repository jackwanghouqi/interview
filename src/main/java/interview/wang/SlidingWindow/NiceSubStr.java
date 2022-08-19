package interview.wang.SlidingWindow;

import java.util.HashSet;
import java.util.Set;
/**
 * Longest Nice Substring (contains both upper and lower case)
 * https://leetcode.com/problems/longest-nice-substring/
 *
 */

public class NiceSubStr {
    public String longestNiceSubstring(String s) {
        if(s.length() <2) return "";
        Set<Character> set = new HashSet<>();
        for(char c: s.toCharArray()) set.add(c);

        for(int i=0;i<s.length();i++) {
            if(set.contains(Character.toLowerCase(s.charAt(i))) && set.contains(Character.toUpperCase(s.charAt(i)))) continue;
            String s1 = longestNiceSubstring(s.substring(0,i));
            String s2 = longestNiceSubstring(s.substring(i+1));
            return s1.length() < s2.length()? s2:s1;
        }
        return s;
    }
}
