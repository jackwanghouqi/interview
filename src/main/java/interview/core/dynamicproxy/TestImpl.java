package interview.core.dynamicproxy;
public class TestImpl implements TestIF {
public String hello(String name) {
   return String.format("Hello %s, this is %s", name, this);
}
}