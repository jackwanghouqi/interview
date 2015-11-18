package interview.core;

class Instance {

	public synchronized void sleep1() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("sleep1");
		}
	}

	public synchronized void sleep2() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("sleep2");
		}
	}

	public synchronized void sleep3() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sleep1();
			System.out.println("sleep3");
		}
	}

	public synchronized void sleep4() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sleep5();
			System.out.println("sleep4");
		}
	}

	public void sleep5() {
		synchronized (this) {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("sleep5");
			}
		}
	}

	public void wait1() {
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("w1");
		}
	}
	
	public void wait2() {
		synchronized (this) {
			try {
				wait(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("w2");
		}
	}

	public void notify1() {
		synchronized (this) {
			notify();
			System.out.println("n1");
		}
	}
	
	public static void main(String[] args) {
		System.out.println(null instanceof Oh);
		System.out.println(-4>>>5);
	}
	
}
