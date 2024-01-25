package clase1;

public class Carrera {
	
	public static int x = 0;
	
	public static class Inc extends Thread {
		
		public void run() {
			x = x + 1;
		}
	}
	
	public static class Dec extends Thread {
		public void run() {
			x = x - 1;
		}
	}
	
	public static void main(String[] a) throws Exception {
		
		Inc i = new Inc();
		Dec d = new Dec();
		
		i.start();
		d.start();
		i.join();
		d.join();
		
		System.out.println(x);
	}
}