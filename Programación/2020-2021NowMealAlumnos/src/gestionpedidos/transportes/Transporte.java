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
public abstract class Transporte {
	
private String codigo;
private Mapa mapa;

protected Transporte (String codigo, Mapa mapa) {
	
	this.codigo = codigo;
	this.mapa = mapa;

}
protected Transporte() {
	
}


public String getCodigo() {
	return codigo;
}

public double coste(String codPosDestino) {
		return coste (this.codigo,codPosDestino);
}
public abstract double coste(String codPosOrigen, String codPosDestino);

protected Mapa getMapa() {
	return mapa;
}

}
