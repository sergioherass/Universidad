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
	
    public IndexedList<Cuenta> getCuentasOrdenadas(Comparator<Cuenta> cmp) {
		
		IndexedList<Cuenta> cuentas_Ordenadas = new ArrayIndexedList<Cuenta>(); 
		

		Cuenta cuenta;
		
		boolean anadido=true;

		if(cuentas.size()>0) {
			
			cuentas_Ordenadas.add(cuentas_Ordenadas.size(), cuentas.get(0));
			
			for(int i = 1 ; i<cuentas.size(); i++) {
				anadido = false;
				cuenta = cuentas.get(i);
				int j=0;
				while (  j<cuentas_Ordenadas.size() && !anadido) {

					if(cmp.compare(cuenta,cuentas_Ordenadas.get(j))<0) {
						cuentas_Ordenadas.add(j,cuenta);
						anadido = true;
					}
					j++;
				}
				if(!anadido) {
					cuentas_Ordenadas.add(cuentas_Ordenadas.size(),cuenta);
				
				}
			}
		}


		return cuentas_Ordenadas;

	}
	@Override
	
	public String crearCuenta(String dni, int saldoIncial) {
		
		Cuenta cuenta = new Cuenta(dni, saldoIncial); //creamos un objeto de tipo cuenta
		
		cuentas.add(cuentas.size(),cuenta); //añadimos las cuentas creadas a la lista indexada cuentas en la ultima posicion
		
		return cuenta.getId();
		
	}
	private int buscarCuenta(String id) throws CuentaNoExisteExc {
		
		int posicion = -1;
		int i=0;
		if(id!=null) {
			
			while(posicion==-1 && i<cuentas.size()) { // hacemos un bucle while para recorrer la lista indexada cuentas y comparar el id dado como parametro con cada una de las posiciones de la lista
				if(id.equals(cuentas.get(i).getId())) { // si coincide, ya tenemos la posicion buscada
					posicion = i; 
				}
				i++;
			}
		}
		if(posicion == -1) { // si el valor de posicion no ha variado, significa que no ha coincidido el id dado con ningun id de las cuentas por lo que no existe la cuenta
			 
			  throw new CuentaNoExisteExc();
		}
		return posicion;
		
	}


	@Override
	
	public void borrarCuenta(String id) throws CuentaNoExisteExc, CuentaNoVaciaExc {
		
		int posicion = buscarCuenta(id); //llamamos al metodo privado buscarCuenta para saber la posicion de la cuenta asociada al id pasado como parametro

		Cuenta cuenta = cuentas.get(posicion); //creamos un objeto de tipo cuenta que sera la cuenta asociada al id
		
		if(cuenta.getSaldo() != 0) { // si el saldo de la cuenta no es cero, lanza la excepcion
			
			throw new CuentaNoVaciaExc();
			
		}else { // en caso contrario, eliminamos la cuenta

		cuentas.remove(cuenta);
		}

	}

	@Override
	
	public int ingresarDinero(String id, int cantidad) throws CuentaNoExisteExc {
		
		int posicion=buscarCuenta(id);

		return cuentas.get(posicion).ingresar(cantidad); //llamamos al metodo ingresar de la clase Cuenta para ingresar una cantidad dada como parametro en la cuenta asociada al id pasado como parametro

	 }

	@Override
	
	public int retirarDinero(String id, int cantidad) throws CuentaNoExisteExc, InsuficienteSaldoExc {
		
		int posicion=buscarCuenta(id);

		return cuentas.get(posicion).retirar(cantidad); //llamamos al metodo retirar de la clase Cuenta

	}

	@Override
	
	public int consultarSaldo(String id) throws CuentaNoExisteExc {
		
		int posicion=buscarCuenta(id);

		return cuentas.get(posicion).getSaldo(); //llamamos al metodo getSaldo de la clase Cuenta

	}

	@Override
	
	public void hacerTransferencia(String idFrom, String idTo, int cantidad) throws CuentaNoExisteExc, InsuficienteSaldoExc {
		
		int pos_Origen=buscarCuenta(idFrom);
		
		int pos_Destino=buscarCuenta(idTo);
		
		cuentas.get(pos_Origen).retirar(cantidad);
		
		cuentas.get(pos_Destino).ingresar(cantidad);

	 }

	@Override
	
	public IndexedList<String> getIdCuentas(String dni) {
		
		IndexedList<String> lista_Id = new ArrayIndexedList<String>(); 


	
		if(dni != null) {
		for(int i=0;i<cuentas.size();i++) { //recorremos la lista cuentas para añadir todas las cuentas a la lista_Id en el caso de que los dnis coincidan
			
				if(dni.equals(cuentas.get(i).getDNI())) {
					
					lista_Id.add(lista_Id.size(),cuentas.get(i).getId());
				
				}
			}
		}
		return lista_Id;
	}

	@Override
	
	public int getSaldoCuentas(String dni) {
		int saldoTotal = 0;

		if(dni != null) {
			
			for(int i=0; i<cuentas.size();i++) {
				
				if(dni.equals(cuentas.get(i).getDNI())) {
					
					saldoTotal = saldoTotal + cuentas.get(i).getSaldo();

				}
			}
		}

		return saldoTotal;

	}



	// ----------------------------------------------------------------------
	// NOTAD. No se deberia cambiar este metodo.
	
	public String toString() {
		return "banco";
	}
}