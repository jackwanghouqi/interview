package interview.core;

public class TestThread3 {
	static Instance in = new Instance();

	public static void main(String[] args) {
		B b = new B();
		b.start();
		b.interrupt();
		System.out.println(b.isInterrupted());
	}
	
	static class B extends Thread {
		public synchronized void run() {
				in.sleep1();
		}
	}
}
