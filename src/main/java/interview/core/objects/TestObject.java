package interview.core.objects;

public class TestObject {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(o.hashCode());
        Object o2 = new Object();
        System.out.println(o2.hashCode());
        Object o3 = new TestObject();
        System.out.println(o3.hashCode());
        Object o4 = new TestObject();
        System.out.println(o4.hashCode());
    }
}

abstract class ATestObject {
    private void testParentObjectFunctions() {
        System.out.println(this.hashCode());
    }
}
