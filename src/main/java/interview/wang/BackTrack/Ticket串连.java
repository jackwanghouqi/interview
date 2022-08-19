package interview.wang.BackTrack;

import java.util.*;

public class Ticket串连 {
    /*★★【Backtrak+HashMap】  Time:  O(N*LogN) Space: O(N) */
    public List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, ArrayList<String>> ticketsMap = new HashMap<>();
        for(List<String> ticket : tickets) {
            if(!ticketsMap.containsKey(ticket.get(0))) ticketsMap.put(ticket.get(0), new ArrayList<String>());
            ticketsMap.get(ticket.get(0)).add(ticket.get(1));
        }
        for(List<String> destList : ticketsMap.values()) {
            if(destList.size()>1) Collections.sort(destList, Collections.reverseOrder());
        }

        int count = tickets.size();
        LinkedList<String> result = new LinkedList<>();
        result.add("JFK");
        backtrack(ticketsMap, "JFK", 0, count, result) ;
        return result;
    }

    private boolean backtrack(HashMap<String, ArrayList<String>> ticketsMap, String port, int start, int count, LinkedList<String> result) {
        if(start == count) return true;

        ArrayList<String> destList = ticketsMap.get(port);
        if(destList == null) return false;
        for(int i = destList.size()-1; i>=0; i--) {
            String dest = destList.get(i);
            result.add(dest);
            destList.remove(i);
            if(backtrack(ticketsMap, dest, start+1, count, result)) return true;
            result.removeLast();
            destList.add(i, dest);
        }
        return false;
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new LinkedList<>();
        tickets.add(new LinkedList<>());
        tickets.add(new LinkedList<>());
        tickets.add(new LinkedList<>());
        tickets.add(new LinkedList<>());
        tickets.get(0).add("MUC");tickets.get(0).add("LHR");
        tickets.get(1).add("JFK");tickets.get(1).add("MUC");
        tickets.get(2).add("SFO");tickets.get(2).add("SJC");
        tickets.get(3).add("LHR");tickets.get(3).add("SFO");
        System.out.println(new Ticket串连().findItinerary(tickets));
    }
}

/*https://leetcode.com/problems/reconstruct-itinerary/*/