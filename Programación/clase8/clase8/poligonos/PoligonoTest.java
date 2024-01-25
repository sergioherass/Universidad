package clase8.poligonos;

public class PoligonoTest {

	public static void main(String[] args) {
		
		Triangulo t1 = new Triangulo(1,1,1);
		System.out.println("El polígono introducido es un " + t1.getTipo());
		
		Triangulo t2 = new Triangulo(1,1,2);
		System.out.println("El polígono introducido es un " + t2.getTipo());
		
		Triangulo t3 = new Triangulo(1,2,3);
		System.out.println("El polígono introducido es un " + t3.getTipo());
		
		Rectangulo r1 = new Rectangulo(1,1,1,1);
		System.out.println("El polígono introducido es un " + r1.getTipo());
		
		Rectangulo r2 = new Rectangulo(1,1,2,2);
		System.out.println("El polígono introducido es un " + r2.getTipo());
		
		Rectangulo r3 = new Rectangulo(1,1,2,3);
		System.out.println("El polígono introducido es un " + r3.getTipo());

	}

}
