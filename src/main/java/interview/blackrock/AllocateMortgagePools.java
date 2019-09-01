package interview.blackrock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllocateMortgagePools {
    static int[][] aggregatePools(int[] poolList) {
        ArrayList<int[]> list = new ArrayList<>();
        Map<Integer, Integer> tempPoolCountMap = new HashMap<>();
        for(int pool:poolList) {
            if(pool < 1000) {
                if(tempPoolCountMap.containsKey(pool)) {
                    tempPoolCountMap.put(pool, tempPoolCountMap.get(pool)+1);
                } else {
                    tempPoolCountMap.put(pool, 1);
                }
            } else {
                list.add(new int[]{pool});
            }
        }
        for(int i : tempPoolCountMap.keySet()) {
            if(tempPoolCountMap.containsKey(1000-i) && tempPoolCountMap.get(1000-i) != 0) {
                int[] newLot = new int[]{i, 1000-i};
                list.add(newLot);
                removeEle(tempPoolCountMap, i);
                removeEle(tempPoolCountMap, 1000-i);
            } else {
                for(int y : tempPoolCountMap.keySet()) {
                    if(y < 1000-i ) {
                        if(tempPoolCountMap.containsKey(1000-i-y) && tempPoolCountMap.get(1000-i-y) != 0) {
                            int[] newLot = new int[]{i, y, 1000-i-y};
                            list.add(newLot);
                            removeEle(tempPoolCountMap, i);
                            removeEle(tempPoolCountMap, 1000-i);
                        }
                    }
                }
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    private static void removeEle(Map<Integer, Integer> tempPoolCountMap, int i) {
        tempPoolCountMap.put(i, tempPoolCountMap.get(i)-1);
    }


    public static void main(String[] args) throws IOException {

        int[][] res;

        int[] inputPools = {12000,400,600,100};

        res = aggregatePools(inputPools);
        for(int res_i = 0; res_i < res.length; res_i++) {
            int[] res_line = res[res_i];
            for(int res_j = 0; res_j < res_line.length; res_j++) {
                System.out.print(String.valueOf(res_line[res_j]));
                System.out.print(String.valueOf(" "));
            }
            System.out.println();
        }
    }

    static int[] parseInputFromTheInput(String inputString){
        String[] ss = inputString.split("\\,");
        int[] a = new int[ss.length];
        for(int i=0; i<ss.length; i++){
            a[i] = Integer.valueOf(ss[i]);
        }
        return a;
    }
}
