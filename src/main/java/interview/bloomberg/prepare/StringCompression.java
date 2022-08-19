package interview.bloomberg.prepare;

public class StringCompression {
    public int compress(char[] chars) {
        if(chars.length <2) {
            return chars.length;
        }
        int temp = 0;
        char current = chars[temp];
        int count = 1;
        for(int i=1; i<chars.length; i++) {
            char toCompare = chars[i];
            if(current==toCompare) {
                count++;
            }

            if(i==chars.length-1 || current!=toCompare) {
                if(count>1) {
                    String countStr = count+"";
                    char[] newChars = countStr.toCharArray();
                    for (int j = 0; j < newChars.length; j++) {
                        chars[++temp] = newChars[j];
                    }
                    count =1;
                }
            }

            if(current!=toCompare ) {
                chars[++temp] =chars[i];
                current = chars[i];
            }

            if(i==chars.length-1){
                temp++;

            }
        }
        return temp;
    }


    public static void main(String[] args) {
        char[] chars = new char[]{'a','a','b','b','b','c','c','c','c','c'};
        int x = new StringCompression().compress(chars);
        System.out.println(chars);
        System.out.println(x);
        chars = new char[]{'a','a'};
        x = new StringCompression().compress(chars);
        System.out.println(chars);
        System.out.println(x);
    }
}
