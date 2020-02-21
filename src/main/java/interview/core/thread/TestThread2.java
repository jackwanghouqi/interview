package interview.core.thread;

public class TestThread2 {
	static Instance in = new Instance();

	public static void main(String[] args) {
		A a = new A();
		a.start();
		a.interrupt();
		/*System.out.println("--------------------------");
		B b = new B();
		b.interrupt();*/
	}

	static class A extends Thread {
		public synchronized void run() {
				in.wait2();
		}
	}
	
	static class B extends Thread {
		public synchronized void run() {
				in.sleep1();
		}
	}
}
