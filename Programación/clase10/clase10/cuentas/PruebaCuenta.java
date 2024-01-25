package clase10.cuentas;

public class PruebaCuenta {
	
	public static void main(String args[]) {
		try {
			Cuenta c1 = new Cuenta("Pepe",10.02);
			System.out.println(c1);
			
			c1.ingresarDinero(100);
			System.out.println(c1);
			
			c1.sacarDinero(200);
			System.out.println(c1);
			
			// Cuenta c2 = new Cuenta("Paquito",9.99);
			// System.out.println(c2);
			
		} catch(SaldoInicialInsuficiente e) {
			e.printStackTrace();
		} catch(SaldoInsuficiente e) {
			e.printStackTrace();
		}
		
		
		
	}

}
