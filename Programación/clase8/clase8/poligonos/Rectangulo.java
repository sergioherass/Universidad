package clase8.poligonos;

public class Rectangulo extends Poligono {
	
	private double[] lados = new double[4];
	
	public Rectangulo(double l1, double l2, double l3, double l4) {
		lados[0] = l1;
		lados[1] = l2;
		lados[2] = l3;
		lados[3] = l4;
	}
	
	public String getTipo() {
		String res = "";
		if(lados[0]==lados[1] && lados[0]==lados[1] && lados[0]==lados[2] && lados[0]==lados[3])
			res = "cuadradado";
		else if((lados[0]==lados[1] && lados[2]==lados[3]) ||
				(lados[0]==lados[2] && lados[1]==lados[3]) ||
				(lados[0]==lados[3] && lados[2]==lados[1]))
			res = "rectangulo";
		return res;
	}

}
