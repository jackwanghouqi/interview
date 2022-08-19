package interview.wang.字串;

import java.util.*;

public class IPAddresses {
    /*★★【return all possible valid IP addresses】 backtracking   Time: 最大 O(3^12)，   */
    public List<String> restoreIpAddresses(String s) {
        List<String> results = new LinkedList<>();
        if(s.length()<4 || s.length()> 12) return results;
        backtrack(0, 0, s, new LinkedList<>(), results);

        return results;
    }

    //backtrack recursively
    private void backtrack(int start, int counter, String s, LinkedList<String> tempList, List<String> results){
        //if(s.length()-start > (4-counter)*3) return;
        if(counter == 4) {
            if(start == s.length()) {
                StringBuilder sb = new StringBuilder();
                for (String str : tempList)  sb.append(str).append(".");
                sb.deleteCharAt(sb.length()-1);
                results.add(sb.toString());
            }
            return ;
        }

        for(int i=0; i <3; i++) {
            int end = start+i+1; //end for substring
            //validate substring
            if(isValidIPInteger(s, start, end)) {
                tempList.add(s.substring(start, end));
                backtrack(end, counter+1, s, tempList, results);
                tempList.removeLast();
            }
        }
    }

    private boolean isValidIPInteger(String s, int start, int end) {
        if(end>s.length()) return false;
        String intStr = s.substring(start, end);
        if(intStr.startsWith("0") && intStr.length() > 1) return false;
        if(end-start <3) return true;
        int number = Integer.valueOf(intStr);
        return number >=0 && number <256;
    }

    public static void main(String[] args) {
        System.out.println(new IPAddresses().restoreIpAddresses("101023" ));
    }
}
