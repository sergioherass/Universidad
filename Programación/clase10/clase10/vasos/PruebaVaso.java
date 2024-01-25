package clase10.vasos;

import java.util.Scanner;

public class PruebaVaso {

	public static void main(String[] args) { // throws ExcepcionVasoNoCabe { <- Es mejor usar try-catch
		Scanner sc = new Scanner(System.in);
		Vaso vaso2 = new Vaso(200);
		try {
			
			Vaso vaso1 = new Vaso(500);
			System.out.println(vaso1);
			vaso1.llenar(200);
			System.out.println(vaso1);
			vaso1.vaciar(100);
			System.out.println(vaso1);
			vaso1.llenar(100);
			System.out.println(vaso1);
			vaso1.llenar(200);
			System.out.println(vaso1);
			vaso1.vaciar(30);
			System.out.println(vaso1 + "\n\n");
			
			
			// vaso2.prepararVasoDesayuno(250,30,10);
			double cantidad = 0;
			do {
				System.out.println("Introduzca una cantidad: ");
				 cantidad = sc.nextDouble();
			} while(cantidad<0||cantidad>200);
			vaso2.llenar(cantidad);
			System.out.println(vaso2);
			
		} catch(ExcepcionVasoNoCabe excepcion) {
			excepcion.printStackTrace();
		} finally {
			sc.close();
		}

	}

}
