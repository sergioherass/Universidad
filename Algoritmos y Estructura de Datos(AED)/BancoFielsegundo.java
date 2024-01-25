package aed.bancofiel;

import java.util.Comparator;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.indexedlist.ArrayIndexedList;


/**
 * Implements the code for the bank application.
 * Implements the client and the "gestor" interfaces.
 */
public class BancoFiel implements ClienteBanco, GestorBanco {

	// NOTAD. No se deberia cambiar esta declaracion.
	public IndexedList<Cuenta> cuentas;

	// NOTAD. No se deberia cambiar esta constructor.
	public BancoFiel() {
		this.cuentas = new ArrayIndexedList<Cuenta>();
	}

	// ----------------------------------------------------------------------
	// Anadir metodos aqui ...

	@Override
	public String crearCuenta(String dni, int saldoIncial) {
		Cuenta cuenta = new Cuenta(dni, saldoIncial);
		String idCuentaNueva = cuenta.getId();
		boolean anyadido = false;
		int posicion = 0;
		
		for(; posicion<cuentas.size() && !anyadido; posicion++) {
			if(idCuentaNueva.compareTo(cuentas.get(posicion).getId())<0) {
				anyadido=true;
				cuentas.add(posicion,cuenta);
			}
		}
		
		if(!anyadido)
			cuentas.add(posicion,cuenta);
		
		return idCuentaNueva;
	}

	private int buscarCuenta(String id) throws CuentaNoExisteExc {
		int pos = -1;
		if(id!=null) {
			for(int i=0; i<cuentas.size() && pos==-1; i++) {
				if(id.equals(cuentas.get(i).getId()))
					pos = i;
			}
		}
		if(pos == -1) 
			throw new CuentaNoExisteExc();

		return pos;
	}

	@Override
	public void borrarCuenta(String id) throws CuentaNoExisteExc, CuentaNoVaciaExc {
		int pos = buscarCuenta(id);

		Cuenta cuenta = cuentas.get(pos);
		if(cuenta.getSaldo() != 0)
			throw new CuentaNoVaciaExc();

		cuentas.remove(cuenta);

	}

	public int ingresarDinero(String id, int cantidad) throws CuentaNoExisteExc {
		int pos = buscarCuenta(id);

		return cuentas.get(pos).ingresar(cantidad);
	}

	public int retirarDinero(String id, int cantidad) throws CuentaNoExisteExc, InsuficienteSaldoExc {
		int pos = buscarCuenta(id);

		return cuentas.get(pos).retirar(cantidad);
	}

	@Override
	public int consultarSaldo(String id) throws CuentaNoExisteExc {
		int pos = buscarCuenta(id);

		return cuentas.get(pos).getSaldo();
	}

	@Override
	public void hacerTransferencia(String idFrom, String idTo, int cantidad)
			throws CuentaNoExisteExc, InsuficienteSaldoExc {
		// compruebo si existen las dos cuentas bancarias
		int posOrigen = buscarCuenta(idFrom);
		int posDestino = buscarCuenta(idTo);

		// compruebo que puedo retirar el dinero de la cuenta origen
		// y si es el caso, lo retiro e ingreso en la segunda
		cuentas.get(posOrigen).retirar(cantidad);
		cuentas.get(posDestino).ingresar(cantidad);
	}

	// YO NO LO HARÍA ASÍ
	public void hacerTransferencia2(String idFrom, String idTo, int cantidad)
			throws CuentaNoExisteExc, InsuficienteSaldoExc {
		//ingresarDinero(idTo, 0);
		buscarCuenta(idTo);
		retirarDinero(idFrom,cantidad);
		ingresarDinero(idTo,cantidad);
	}

	@Override
	public IndexedList<String> getIdCuentas(String dni) {
		IndexedList<String> ids = new ArrayIndexedList<String>();
		int tam = 0;

		if(dni != null) {
			for(Cuenta cuenta: cuentas) {
				if(dni.equals(cuenta.getDNI())) {
					ids.add(tam,cuenta.getId());
					tam++;
				}
			}
		}
		return ids;
	}

	@Override
	public int getSaldoCuentas(String dni) {
		int saldoTotal = 0;

		if(dni != null) {
			for(Cuenta cuenta: cuentas) {
				if(dni.equals(cuenta.getDNI())) {
					saldoTotal += cuenta.getSaldo();
					// Esto es equivalente a hacer:
					// saldoTotal = saldoTotal + cuenta.getSaldo();
				}
			}
		}

		return saldoTotal;
	}

	@Override
	public IndexedList<Cuenta> getCuentasOrdenadas(Comparator<Cuenta> cmp) {
		IndexedList<Cuenta> cuentasOrdenadas = new ArrayIndexedList<Cuenta>(); 
		int tam = 0;
		Cuenta cuenta;
		boolean anyadido;

		if(cuentas.size()>0) {
			cuentasOrdenadas.add(tam, cuentas.get(0));
			tam++;
			for(int i = 1; i<cuentas.size(); i++) {
				anyadido = false;
				cuenta = cuentas.get(i);
				for(int j=0; j<tam && !anyadido; j++) {
					if(cmp.compare(cuenta,cuentasOrdenadas.get(j))<0) {
						cuentasOrdenadas.add(j,cuenta);
						anyadido = true;
					}
				}
				if(!anyadido)
					cuentasOrdenadas.add(tam,cuenta);
				
				tam++;
			}
		}


		return cuentasOrdenadas;
	}


	// ----------------------------------------------------------------------
	// NOTAD. No se deberia cambiar este metodo.
	public String toString() {
		return "banco";
	}


}



