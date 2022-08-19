package interview.wang.字串;

import java.util.Stack;

public class Split字符 {
    /*★★1849. Splitting a String Into Descending Consecutive Values   Time： < O(2^N) (找到 就停止)*/
    public boolean splitString(String s) {
        if(s==null || s.length() <2) return false;
        return backtrack(s, 0,new Stack<Long>());
    }

    private boolean backtrack(String s, int start, Stack<Long> stack){
        if(start > s.length()-1 && stack.size()>1) return true;
        for(int end=start; end <s.length(); end++) {
            String numberStr = s.substring(start, end+1);
            if(numberStr.length() > 18 && (numberStr.charAt(0)!='0'|| numberStr.charAt(1)!='0')) return false; //TODO 溢出 有其他办法吗？？？
            long currentInt = Long.valueOf(numberStr); //TODO 这里也可以用捕获异常的方式返回false，因为如果有超过一个Long MAX_VALUE的数 则不可能有另一个和它差1的数。
            if(stack.isEmpty() || stack.peek()-currentInt==1L) {
                Stack<Long> newStack= (Stack<Long>)stack.clone();
                newStack.push(currentInt);
                if(backtrack(s,end+1,newStack)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Split字符().splitString("1234"));
        System.out.println(String.valueOf(Long.MAX_VALUE).length());
        System.out.println("09308975484057363717".length());
    }
}
