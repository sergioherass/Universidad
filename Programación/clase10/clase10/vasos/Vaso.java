package clase10.vasos;

public class Vaso {
	
	private double contenido;
	private double capacidadMaxima;
	
	public Vaso(double capacidad) {
		this.contenido=0;					// el this.variable es NECESARIO solo cuando hay conflicto,
		this.capacidadMaxima=capacidad; 	// es decir, el parametro de entrada y el nombre de la variable 
	}										// son el mismo
	
	/**
	 * Llena el vaso con una cierta cantidad de líquido
	 * 
	 * PRE: contenido+cantidad <= capacidadMax
	 * 
	 * @param cantidad a introducir en el vaso
	 */
	public void llenar(double cantidad) throws ExcepcionVasoNoCabe {
		if(contenido+cantidad<= capacidadMaxima)
			contenido += cantidad;
		else 
			throw new ExcepcionVasoNoCabe("La cantidad introducida excede la capacidad máxima del vaso");
		/*
		 * MALA SOLUCIÓN --> Mejor trabajamos con excepciones
		 * 
		 * else
		 *	System.err.println("No cabe cantidad");
		*/
		
	}
	
	/**
	 * Vacía cierta cantidad de líquido del vaso
	 * 
	 * PRE: cantidad <= contenido
	 * 
	 * @param cantidad a vaciar del vaso
	 */
	public void vaciar(double cantidad) throws ExcepcionVasoNoCabe {
		if(cantidad<=contenido)
			contenido -= cantidad;
		else
			throw new ExcepcionVasoNoCabe("No hay tanta cantidad de líquido en el vaso");
	}
	
	public double getCantidad() {
		return contenido;
	}
	
	public String toString() {
		return "El vaso con capacidad para " + capacidadMaxima + "ml contiene " + contenido + "ml";
	}
	
	public void prepararVasoDesayuno(double leche, double cacao, double azucar) throws ExcepcionVasoNoCabe {
		this.llenar(leche);
		this.llenar(cacao);
		this.llenar(azucar);
	}

}
