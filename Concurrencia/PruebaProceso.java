package prueba;

import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.Parallel;

public class PruebaProceso {
	static private Any2OneChannel c1 = Channel.any2one();
	static private Any2OneChannel c2 = Channel.any2one();
	
	static class A implements CSProcess{


		public void run() {
			String s = (String) c2.in().read();
			c1.out().write("A");
			System.out.print(s);
		}
		
	}
	
	static class B implements CSProcess{


		public void run() {
			c2.out().write("B");
			String s = (String) c1.in().read();

			System.out.print(s);
			
		}
		
	}
	
	public static void main(String[] args) {
		CSProcess sistema = new Parallel (new CSProcess[] {new A(), new B()});
		sistema.run();
		
	}

}
