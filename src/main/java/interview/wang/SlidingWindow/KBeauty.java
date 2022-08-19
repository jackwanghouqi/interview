package interview.wang.SlidingWindow;

public class KBeauty {
    public int divisorSubstrings(int num, int k) {
        String numStr = String.valueOf(num);
        int count = 0;
        for(int i=0; i < numStr.length()-k+1; i++) {
            String subStr = numStr.substring(i, k+i);
            System.out.println(subStr);
            int x=Integer.valueOf(subStr);
            if(x!=0 && num%x ==0) count++; //TODO 判断 O 为除数的情况！！！！！
        }
        return count;
    }

    public static void main(String[] args) {
        KBeauty kb = new KBeauty();
        System.out.println(kb.divisorSubstrings(240, 2));
    }
}
