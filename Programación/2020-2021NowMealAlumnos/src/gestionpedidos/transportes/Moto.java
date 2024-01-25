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
public class Moto extends Transporte{
	
	private double eurosPKm = 2.00;
	public static final double TARIFA_MIN = 10.00;
	
	
	public Moto (String codigo, Mapa mapa) {
		
		super(codigo,mapa);
	}
	
	
	public Moto() {
	}

	public double getEurosPKm() {
		return eurosPKm;
	}

	public void setEurosPKm(double eurosPKm) {
		this.eurosPKm = eurosPKm;
	}

	public double coste(String codPosOrigen, String codPosDestino) {
		return super.getMapa().distancia(codPosOrigen, codPosDestino)*getEurosPKm() + TARIFA_MIN;
	}






	
}
