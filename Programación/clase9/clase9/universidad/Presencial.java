package clase9.universidad;

public class Presencial extends Alumno {
	
	private double matriculaCurso;
	private double plusPorConvocatoria;
	private int noConvocatoria;
	
	public Presencial(String nombre, int edad, int curso, String nivel, double matricula, double plus, int nCon) {
		super(nombre,edad,curso,nivel);
		matriculaCurso=matricula;
		plusPorConvocatoria=plus;
		noConvocatoria=nCon;
	}
	
	public double pagoMensual() {
		return (matriculaCurso + plusPorConvocatoria*noConvocatoria)/12;
		
	} 
	
	public String getAsignaturas() {
		return "Todas las del curso " + getCurso();
	}

}
