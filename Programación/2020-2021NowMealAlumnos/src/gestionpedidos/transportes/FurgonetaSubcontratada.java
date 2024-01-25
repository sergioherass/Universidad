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

public class FurgonetaSubcontratada extends Furgoneta{
	private double eurosPKm = 1;
	
	public FurgonetaSubcontratada(String codigo, Mapa mapa, double tara) {
		
		super(codigo,mapa,tara);
	}
	
	public void setEurosPKm(double eurosPKm) {
		this.eurosPKm = eurosPKm;
	}

	public double coste(String codPosOrigen, String codPosDestino) {
		
		if(getTara()<1000) {
			return super.getMapa().distancia(codPosOrigen, codPosDestino)*eurosPKm;
		} else {
			return super.getMapa().distancia(codPosOrigen, codPosDestino)*eurosPKm*1.10;
		}
		

	}

}
