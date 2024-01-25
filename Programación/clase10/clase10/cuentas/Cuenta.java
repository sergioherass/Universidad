package clase10.cuentas;

public class Cuenta {
	
	private String cliente;
	private double saldo;
	private static double gastosApertura = 10;
	
	public Cuenta(String cliente, double saldoInicial) throws SaldoInicialInsuficiente {
		if(saldoInicial<10)
			throw new SaldoInicialInsuficiente("Saldo inicial insuficiente, debe ingresar un mínimo de 10€");
		
		this.cliente = cliente;
		saldo = saldoInicial-gastosApertura;
	}
	
	public void sacarDinero(double cantidad) throws SaldoInsuficiente {
		if(saldo<cantidad)
			throw new SaldoInsuficiente("No tiene suficiente dinero en la cuenta");
		
		saldo -= cantidad;
	}
	
	public void ingresarDinero(double cantidad) {
		saldo += cantidad;
	}
	
	public String toString() {
		return cliente + " tiene " + saldo + " € en la cuenta";
	}

}
