package interview.lab49;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class OnlineTest {
    public static void main(String[] args) {

        // 1) for loop with null reference should throw NullPointerException
        // 2) IllegalArgumentException and NullPointerException
        // 3) In Lambda, "this" stands for
        // 4) Stream of String --> count function call twice with Throw IllegalStateException
        try {
            Stream<String> test = Stream.of("test", "test");
            test.count();
            test.count();

            throw new ClassCastException();
        } catch (IllegalArgumentException e1) {
            System.out.println("IllegalArgumentException");
        } catch (RuntimeException e1) {
            System.out.println(e1.getMessage());
            System.out.println(e1.getClass());
        }

    }
}
