package gestionpedidos.transportes;

import gestionpedidos.mapa.Mapa;
import anotacion.Programacion2;
@Programacion2 (
		nombreAutor1 = "Sergio",
		apellidoAutor1 = "Heras Alvarez",
		emailUPMAutor1 = "sergio.heras.alvarez@alumnos.upm.es",
		nombreAutor2 = "",
		apellidoAutor2 = "", 
		emailUPMAutor2 = ""
	)

public abstract class Furgoneta extends Transporte{
	private double tara;
	
	protected Furgoneta(String codigo, Mapa mapa, double tara) {
		
		super(codigo,mapa);
		this.tara = tara;
		
	}
	
	protected Furgoneta() {
	}

	public double getTara() {
		return tara;
	}
}
