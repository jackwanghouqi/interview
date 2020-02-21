package interview.core.thread;

public class TestThread1 {
	static Instance in = new Instance();

	public static void main(String[] args) {
		A a = new A();
		a.start();
		B b = new B();
		b.start();
	}

	static class A extends Thread {

		public synchronized void run() {
				in.wait1();
		}
	}

	static class B extends Thread {
		public synchronized void run() {
			in.notify1();
		}
	}

}
