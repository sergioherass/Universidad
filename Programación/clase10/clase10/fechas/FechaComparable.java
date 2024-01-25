package clase10.fechas;

public class FechaComparable  implements Comparable<FechaComparable> {

	private int dia;
	private int mes;
	private int anio;

	public FechaComparable(int dia, int mes, int anio) throws Exception {
		this.anio = anio;
		if(mes<1||mes>12)
			throw new Exception("Mes no válido");
		else {
			this.mes = mes;
			switch(mes) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				if(dia<1||dia>31) 
					throw new Exception("Día no válido");
				break;
			case 2:
				if(dia<1||(esBisiesto(anio)&&dia>29)||(!esBisiesto(anio)&&dia>28)) 
					throw new Exception("Día no válido");
				break;
			case 4: case 6: case 9: case 11:
				if(dia<1||dia>30) 
					throw new Exception("Día no válido");
			}
			this.dia = dia;
		}
	}
	
	private boolean esBisiesto(int anio) {
		return (anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0));
	}

	public int compareTo(FechaComparable f) {
		int resultado = 0;
		if(anio<f.anio) {
			resultado = -1;
		} else if(anio>f.anio) {
			resultado = 1;
		} else {
			if(mes<f.mes) {
				resultado = -1;
			} else if(mes>f.mes) {
				resultado=1;
			} else {
				if(dia<f.dia) {
					resultado = -1;
				} else if(dia>f.dia) {
					resultado = 1;
				}
			}
		}
		return resultado;
	}
	
	public String toString() {
		return dia + "/" + mes + "/" + anio;
	}

}
