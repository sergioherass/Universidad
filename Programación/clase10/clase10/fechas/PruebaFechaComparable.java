package clase10.fechas;

import java.util.Arrays;

public class PruebaFechaComparable {
	
	public static void main(String args[]) {
		try {
		
			FechaComparable f1 = new FechaComparable(31,10,1999);
			FechaComparable f2 = new FechaComparable(31,10,2003);
			FechaComparable f3 = new FechaComparable(30,10,2003);
			FechaComparable f4 = new FechaComparable(31,1,2003);
			FechaComparable f5 = new FechaComparable(31,10,1999);
			
			FechaComparable[] fecha = {f1,f2,f3,f4,f5};
			Arrays.sort(fecha);
			
			for(int i=0; i<fecha.length; i++) {
				System.out.println(fecha[i]);
			}
			
			FechaComparable fBisiesta = new FechaComparable(29,2,2020);
			System.out.println("\n\n" + fBisiesta);
			
			// FechaComparable fExcepcionMes = new FechaComparable(29,22,2020);
			// System.out.println(fExcepcionMes);
			
			// FechaComparable fExcepcionDia = new FechaComparable(30,2,2020);
			// System.out.println(fExcepcionDia);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
