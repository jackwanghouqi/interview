package interview.lab49;


public class Interview1 {
    public static void main(String[] args){
        System.out.println(new Integer("1").compareTo(new Integer("10")));
        System.out.println("1".compareTo("10"));

        System.out.println(new Integer("11").compareTo(new Integer("10")));
        System.out.println("11".compareTo("10"));
    }
}
