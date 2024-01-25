package aed.recursion;

import aed.recursion.Explorador;
import aed.recursion.Lugar;
import aed.recursion.Punto;

public class PruebaLab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lugar lugarInicial = new Lugar(new Punto[][] { { new Punto(null,false,false,false,true),new Punto(null,false,false,true,true),
			new Punto(null,true,false,true,true),new Punto(null,false,false,true,false),new Punto(null,true,false,false,false),
			new Punto(null,true,false,false,false),new Punto(null,false,false,false,false) }, 
			{ new Punto(null,false,false,false,true),
				new Punto(null,false,false,true,false),new Punto(null,true,true,false,true),new Punto(null,true,false,true,false),
				new Punto(null,false,true,false,false),new Punto(null,false,true,false,true),new Punto(null,true,false,true,false) }, 
			{ new Punto(null,false,false,false,false),new Punto(null,false,false,false,false),new Punto(null,false,true,false,false),
					new Punto(null,false,true,false,true),new Punto(null,true,false,true,false),new Punto(null,true,false,false,true),
					new Punto(null,true,true,true,false) }, { new Punto(null,true,false,false,true),
						new Punto(null,true,false,true,false),new Punto(null,true,false,false,true),
						new Punto(null,false,false,true,true),new Punto(null,true,true,true,true),
						new Punto(null,true,true,true,false),new Punto(null,false,true,false,false) }, 
			{ new Punto(null,true,true,false,false),new Punto(null,true,true,false,true),new Punto(null,true,true,true,true),
							new Punto(null,true,false,true,false),new Punto(null,false,true,false,true),
							new Punto(null,false,true,true,true),new Punto(null,false,false,true,false) }, 
			{ new Punto(54,false,true,false,false),new Punto(null,false,true,false,false),new Punto(null,false,true,false,false),
								new Punto(null,false,true,false,false),new Punto(null,false,false,false,false),
								new Punto(null,false,false,false,false),new Punto(null,false,false,false,false) } },
				0,0);

		System.out.println(Explorador.explora(lugarInicial));
	}

}
