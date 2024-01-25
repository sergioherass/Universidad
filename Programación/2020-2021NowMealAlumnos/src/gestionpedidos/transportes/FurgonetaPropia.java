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

public class FurgonetaPropia extends Furgoneta{
	private double velocidadMedia = 30;
	private static final double EUROS_P_HORA = 40;
	
	public FurgonetaPropia(String codigo, Mapa mapa, double tara) {
		super(codigo, mapa, tara);
	}
	
	public double getVelocidadmedia() {
		return velocidadMedia;
	}
	
	public void setVelocidadMedia(double velocidadMedia){
		this.velocidadMedia = velocidadMedia;
	}
	
	public double coste(String codPosOrigen, String codPosDestino) {
		
		if(getTara()<500) {
			return super.getMapa().distancia(codPosOrigen, codPosDestino)*EUROS_P_HORA/velocidadMedia;
		} else {
			return super.getMapa().distancia(codPosOrigen, codPosDestino)*EUROS_P_HORA/velocidadMedia*1.10;
		}
		
	}

}
