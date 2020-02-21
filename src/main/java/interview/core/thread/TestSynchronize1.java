package interview.core.thread;

import interview.core.thread.Instance;

public class TestSynchronize1 {

	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		a.start();
		b.start();
	}
	
	static class A extends Thread {
		Instance in = new Instance();
		public void run() {
			in.sleep1();
		}
	}

	static class B extends Thread {
		Instance in = new Instance();
		public void run() {
			in.sleep2();
		}
	}
}

