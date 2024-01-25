package clase8.herenciaAnimales;

public class Oveja extends Animal {
	
	private String pastor;
	
	public Oveja(String nombre, int edad, String pastor) {
		super(nombre,edad); // primero el padre
		this.pastor = pastor; // luego los de la propia clase
	}
	
	public void pastar() {
		System.out.println("pastando hierba");
	}
	
	public String toString() {
		return super.toString() + ", Pastor: " + pastor;
	}

}
