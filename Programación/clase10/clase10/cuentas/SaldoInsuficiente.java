package clase10.cuentas;

public class SaldoInsuficiente extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SaldoInsuficiente() {}
	
	public SaldoInsuficiente(String msg) {
		super(msg);
	}

}
