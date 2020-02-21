package interview.core.objects;

public class TestVolatile {
    static volatile int number = 1;
    public static void main(String[] args) {
        number++;
        System.out.println(number);
    }
}
