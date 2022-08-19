package interview.wang.区间;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;

/**
 * You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.
 * inclusive range [birthi, deathi - 1]
 *
 * 思路：用HashMap存储year和population
 *
 * */
public class MaxPopulationYear {
    public int maximumPopulation(int[][] logs) {
        Arrays.sort(logs, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int res = 0;
                if (a[0] > b[0]) res = 1;
                else if (a[0] < b[0]) res = -1;
                return res;
            }
        });
        LinkedHashMap<Integer, Integer> tally = new LinkedHashMap<Integer, Integer>();
        for (int[] log : logs) {
            for (int i = log[0]; i < log[1]; i++) {
                tally.put(i, tally.getOrDefault(i, 0) + 1);
            }
        }
        Integer max = tally.get(logs[0][0]);
        Integer MaxPopYear = logs[0][0];
        for (Integer year : tally.keySet()) {
            if (max < tally.get(year)) {
                max = tally.get(year);
                MaxPopYear = year;
            }
        }
        return MaxPopYear;
    }

    public int maximumPopulationII(int[][] logs) {
        int[] years = new int[100];
        //Increase count of year
        for (int i=0;i<logs.length;i++){
            for (int j=logs[i][0];j<logs[i][1];j++){
                years[ j-1950 ]++;
            }
        }
        //Search for the year which has largest count.
        int count = 0;
        int year = logs[0][0];
        for (int i=0;i<100;i++){
            if (years[i] > count){
                count = years[i];
                year = i;
            }
        }
        return year+1950;
    }
}
