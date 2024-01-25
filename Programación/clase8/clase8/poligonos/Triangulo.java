package clase8.poligonos;

public class Triangulo extends Poligono {

	private double[] lados = new double[3];

	public Triangulo(double l1, double l2, double l3) {
		lados[0] = l1;
		lados[1] = l2;
		lados[2] = l3;
	}

	public String getTipo() {
		String res = "";
		if(lados[0]==lados[1] && lados[0]==lados[2]) {
			res = "triángulo equilátero";
		} else if(lados[0]==lados[1] ||lados[0]==lados[2] ||lados[2]==lados[1]) {
			res = "triángulo isósceles";
		} else {
			res = "triángulo escaleno";
		}
		return res;
	}

}
