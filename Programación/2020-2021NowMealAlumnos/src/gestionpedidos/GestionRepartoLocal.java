package gestionpedidos;

import gestionpedidos.excepciones.PedidoSinTransporteAsignado;
import gestionpedidos.pedido.Pedido;
import gestionpedidos.transportes.Furgoneta;
import gestionpedidos.transportes.Moto;
import gestionpedidos.transportes.Transporte;
import list.ArrayList;
import list.IList;
import queues.IQueue;
import queues.exceptions.EmptyQueueException;
import queues.CircularQueue;


import anotacion.Programacion2;
@Programacion2 (
		nombreAutor1 = "Sergio",
		apellidoAutor1 = "Heras Alvarez",
		emailUPMAutor1 = "sergio.heras.alvarez@alumnos.upm.es",
		nombreAutor2 = "",
		apellidoAutor2 = "", 
		emailUPMAutor2 = ""
	)

	public class GestionRepartoLocal {
		// CÓDIGO DE APOYO
		private IList<Moto> motosDisponibles;
		private IList<Furgoneta> furgonetasDisponibles;

		private IQueue<Pedido> pedidosEsperandoMoto;
		private IQueue<Pedido> pedidosEsperandoFurgoneta;

		// CÓDIGO DE APOYO
		public String getDisponibles() {
			return "Motos Disponibles:"
					+ GestionRepartoLocalFuncionesAuxiliares
							.myArrayListToString(GestionRepartoLocalFuncionesAuxiliares.getCodList(motosDisponibles))
					+ System.lineSeparator() + "Furgonetas Disponibles:"
					+ GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(GestionRepartoLocalFuncionesAuxiliares.getCodList(furgonetasDisponibles))
					+ System.lineSeparator();

		}

		// CÓDIGO DE APOYO
		public String getEsperando() {
			return "Pedidos esperando moto:"
					+ GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
							GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoMoto))
					+ System.lineSeparator() + "Pedidos esperando furgoneta:"
					+ GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
							GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoFurgoneta))
					+ System.lineSeparator();
		}

		// CÓDIGO DE APOYO
		public IList<String> getCodMotosDisponibles() {
			return GestionRepartoLocalFuncionesAuxiliares.getCodList(motosDisponibles);
		}

		// CÓDIGO DE APOYO
		public IList<String> getCodFurgoDisponibles() {
			return GestionRepartoLocalFuncionesAuxiliares.getCodList(furgonetasDisponibles);

		}

		// CÓDIGO DE APOYO
		public IList<String[]> getCodEsperandoMoto() {
			return GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoMoto);
		}

		public IList<String[]> getCodEsperandoFurgo() {
			return GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoFurgoneta);
		}

		private static final double PESO_MAX_MOTO = 20;

		// CÓDIGO DE APOYO
		public GestionRepartoLocal() {

			this.motosDisponibles = new ArrayList<>();
			this.furgonetasDisponibles = new ArrayList<>();

			this.pedidosEsperandoFurgoneta = new CircularQueue<>();
			this.pedidosEsperandoMoto = new CircularQueue<>();
		}

		public void add(Transporte transporte) {

			if (transporte instanceof Moto) {
				motosDisponibles.add(motosDisponibles.size(), (Moto) transporte);
			} else {
				furgonetasDisponibles.add(furgonetasDisponibles.size(), (Furgoneta) transporte);
			}
		}

		public void asignarPedido(Pedido pedido) {

			if (pedido.getPeso() <= PESO_MAX_MOTO) {
				if (motosDisponibles.size() == 0) {
					pedidosEsperandoMoto.add(pedido);
				} else {
					Moto motoaux = motosDisponibles.get(0);
					for (int i = 1; i < motosDisponibles.size(); i++) {
						if (pedido.coste(motoaux) > pedido.coste(motosDisponibles.get(i))) {
							motoaux = motosDisponibles.get(i);
						}
					}
					pedido.setTransporte(motoaux);
					motosDisponibles.remove(motoaux);
				}
			} else {

				if (furgonetasDisponibles.size() == 0) {
					pedidosEsperandoFurgoneta.add(pedido);
				} else {
					Furgoneta furgonetaaux = furgonetasDisponibles.get(0);
					for (int i = 1; i < furgonetasDisponibles.size(); i++) {
						if (pedido.coste(furgonetaaux) > pedido.coste(furgonetasDisponibles.get(i))) {
							furgonetaaux = furgonetasDisponibles.get(i);
						}
					}
					pedido.setTransporte(furgonetaaux);
					furgonetasDisponibles.remove(furgonetaaux);
				}
			}
		}
	
		// PRE: el pedido tiene asignado un transporte
		public void notificarEntregaPedido(Pedido pedido) throws PedidoSinTransporteAsignado {

			if (pedido.getTransporte() == null) {
				throw new PedidoSinTransporteAsignado("El pedido no tiene transporte asignado");
			} else {
				try {
					if (pedido.getTransporte() instanceof Moto) {
						if (!pedidosEsperandoMoto.isEmpty()) {
							pedidosEsperandoMoto.poll().setTransporte(pedido.getTransporte());
						} else {
							add(pedido.getTransporte());
						}
					} else {
						if (!pedidosEsperandoFurgoneta.isEmpty()) {
							pedidosEsperandoFurgoneta.poll().setTransporte(pedido.getTransporte());
						} else {
							add(pedido.getTransporte());
						}
					}
				} catch (EmptyQueueException e) {
					System.err.println("Error");
				}
			}
		}
	}
