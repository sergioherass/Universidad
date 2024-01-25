package gestionpedidos.pedido;

import gestionpedidos.transportes.Transporte;
import anotacion.Programacion2;
@Programacion2 (
		nombreAutor1 = "Sergio",
		apellidoAutor1 = "Heras Alvarez",
		emailUPMAutor1 = "sergio.heras.alvarez@alumnos.upm.es",
		nombreAutor2 = "",
		apellidoAutor2 = "", 
		emailUPMAutor2 = ""
	)

public class Pedido {
	// CÓDIGO DE APOYO
	private Cliente cliente;
	private PlatoComida[] comidas;
	private Restaurante restaurante;
	private double importe;	
	private Transporte transporte;
	private double peso;
	
	public Pedido(Cliente cliente, PlatoComida[] comidas, Restaurante restaurante) {		
		//TO-DO
		this.cliente = cliente;
		this.comidas = comidas;
		this.restaurante =restaurante;
		for(int i=0;i<this.comidas.length;i++) {
			importe += this.comidas[i].getPrecio();
			peso += this.comidas[i].getPeso();
		}
	}
	
	public double getPeso(){		
		return peso;
	}
	
	
	
	public double coste(Transporte transporte){
	
		if (getImporte()<100) {
			return importe+ transporte.coste(restaurante.getCodigo())+transporte.coste(restaurante.getCodigo(), cliente.getCodigo()); 
			
		} else {
			return importe+ (transporte.coste(restaurante.getCodigo())+transporte.coste(restaurante.getCodigo(), cliente.getCodigo()))*1.10;
		}
		
	}
	
	// CÓDIGO DE APOYO
	public double getImporte(){
		return importe;
	}	

	// CÓDIGO DE APOYO
	public Transporte getTransporte() {
		return transporte;
	}

	// CÓDIGO DE APOYO
	public void setTransporte(Transporte transporte) {
		this.transporte = transporte;
	}
	
	// CÓDIGO DE APOYO
	public Cliente getCliente(){
		return cliente;
	}
	
	// CÓDIGO DE APOYO
	public Restaurante getRestaurante(){
		return restaurante;
	}

}
