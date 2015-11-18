package interview.core;

public class TestSynchronize2 {
	static Instance in = new Instance();
	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		a.start();
		b.start();
	}
	static class A extends Thread {
		
		public void run() {
			in.sleep1();
		}
	}

	static class B extends Thread {
		public void run() {
			in.sleep2();
		}
	}
}
