package clase10.cuentas;

public class SaldoInicialInsuficiente extends Exception {

	private static final long serialVersionUID = 1L;

	public SaldoInicialInsuficiente() {}
	
	public SaldoInicialInsuficiente(String msg) {
		super(msg);
	}

}
