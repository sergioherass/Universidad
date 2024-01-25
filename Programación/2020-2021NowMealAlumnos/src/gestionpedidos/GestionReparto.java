package gestionpedidos;

import gestionpedidos.excepciones.PedidoSinTransporteAsignado;
import gestionpedidos.mapa.Mapa;
import gestionpedidos.mapa.PosicionXY;
import gestionpedidos.pedido.Pedido;
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

public class GestionReparto {
	// CÓDIGO DE APOYO
	private GestionRepartoLocal[] gestoresLocales;
	private Mapa mapa;

	//CÓDIGO DE APOYO
	public Mapa getMapa() {
		return mapa;
	}

	// CÓDIGO DE APOYO
	public String getEstadoGestorLocal(int i){
		return this.gestoresLocales[i].getDisponibles() + this.gestoresLocales[i].getEsperando();
	}

	// CÓDIGO DE APOYO
	public String getEstadoGestorLocalNum(int i){
		return this.gestoresLocales[i].getCodMotosDisponibles().size() + ";" +
				this.gestoresLocales[i].getCodFurgoDisponibles().size() + ";" +
				this.gestoresLocales[i].getCodEsperandoMoto().size() + ";" +
				this.gestoresLocales[i].getCodEsperandoFurgo().size() ;
	}

	public GestionReparto(Mapa mapa){
		
		gestoresLocales = new GestionRepartoLocal[4];
		gestoresLocales[0]= new GestionRepartoLocal();
		gestoresLocales[1]= new GestionRepartoLocal();
		gestoresLocales[2]= new GestionRepartoLocal();
		gestoresLocales[3]= new GestionRepartoLocal();
		this.mapa = mapa;
	}
	
	public void addTransporteLocalidad(Transporte transporte) {
		PosicionXY posicionTransporte = getMapa().getPosicion(transporte.getCodigo());
		int localidad = seleccionarLocalidad(posicionTransporte);
		gestoresLocales[localidad].add(transporte);
	}

	private int seleccionarLocalidad(PosicionXY pos){
		int x = pos.getX();
		int y = pos.getY();
		int localidad ;
		
		if ( x<=getMapa().getMaxCoordX()/2) {
			if (y<=getMapa().getMaxCoordY()/2) {
				localidad=0;
				
			}else {
				localidad=2;
			}
		}else {
			if(y<=getMapa().getMaxCoordY()/2) {
				localidad=1;
			}else {
				localidad = 3;
			}
		}
		return localidad;
	}

	public void asignarPedido(Pedido pedido) {
		PosicionXY posicionCliente = getMapa().getPosicion(pedido.getCliente().getCodigo());
		int localidadCliente= seleccionarLocalidad(posicionCliente);
		PosicionXY posicionRestaurante = getMapa().getPosicion(pedido.getRestaurante().getCodigo());
		int localidadRestaurante=seleccionarLocalidad(posicionRestaurante);
		if ( localidadCliente == localidadRestaurante) {
			gestoresLocales[localidadCliente].asignarPedido(pedido);
		}
	}
	

	public void notificarEntregaPedido(Pedido pedido) throws PedidoSinTransporteAsignado{
		PosicionXY posicionTransporte = getMapa().getPosicion(pedido.getTransporte().getCodigo());
		int localidad = seleccionarLocalidad(posicionTransporte);
		gestoresLocales[localidad].notificarEntregaPedido(pedido);
	}
	

}
