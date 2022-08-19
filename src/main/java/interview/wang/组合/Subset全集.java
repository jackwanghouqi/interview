package interview.wang.组合;

import java.util.*;
/**
 * 思路：排序不考虑 （如果考虑，参考Permutation）
 *       假设 现在已经发现 n 个 子集合，如果加上一个元素 X ，子集合总数就会多出一倍(之前 每个集合 + X )
 *      设 N个元素，所有的可能性就是 2^N
 * */
public class Subset全集 {
    /*★★【每次扩展加一个元素 Cascading(Iterative)】  Time:  O(N*(2^N) Space: O(log) */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());//包括空集
        for(int num : nums) { //loop 每次集合加一倍， O(2^N)
            List<List<Integer>> tempList = new LinkedList<>(); //【Link比Array快】
            for (List<Integer> list : result) {
                tempList.add(new ArrayList<>(list){{add(num);}}); // copy all array cost O(N)
            }
            result.addAll(tempList);
        }
        return result;
    }

    /*★★【有重复元素】【每次扩展加一个元素 Cascading(Iterative)】  Time:  O(N*(2^N) Space: O(log) */
    public List<List<Integer>> subsetsII(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if(nums.length ==0) return result;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<List<Integer>> tempList = new LinkedList<>(); //【Link比Array快】
            for(List<Integer> list:result) {
                tempList.add(new ArrayList<>(list){{add(num);}});
            }
            result.addAll(tempList);
            while(i < nums.length-1 && num==nums[i+1]) {//for duplicated numbers only add to the previous List.
                i++;
                List<List<Integer>> duptempList = new LinkedList<>();
                for(List<Integer> list:tempList) {
                    duptempList.add(new ArrayList<>(list){{add(num);}});
                }
                result.addAll(duptempList);
                tempList = duptempList;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Subset全集().subsets(new int[]{0,1,2,1}));
    }
}
