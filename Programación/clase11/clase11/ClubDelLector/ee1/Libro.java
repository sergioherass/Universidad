package clase11.ClubDelLector.ee1;
import anotacion.Programacion2;
import fecha.*;


@Programacion2 (
        nombreAutor1 = "Sergio",
		apellidoAutor1 = "Heras",
		emailUPMAutor1 = "sergio.heras.alvarez@alumnos.upm.es"
)


public class Libro {
	
	private String titulo ;
	private String autor ;
	private Fecha fechaPublicacion ;
	private int nVecesPrestado ;
	
	public Libro(String titulo, String autor, Fecha fecha) {
		this.titulo = titulo ;
		this.autor = autor ;
		this.fechaPublicacion = fecha ;
	}
	
	public Libro(Libro l) {
		titulo = new String(l.titulo) ;
		autor = new String(l.autor) ;
		fechaPublicacion = new Fecha(l.fechaPublicacion) ;
	}
	
	public String getTitulo() {
		return titulo ;
	}
	
	public String getAutor() {
		return autor ;
	}
	
	public Fecha getFechaPublicacion() {
		return fechaPublicacion ;
	}
	
	public int getNVecesPrestado() {
		return nVecesPrestado ;
	}
	
	public void prestado() {
		nVecesPrestado++ ;
	}
	
	public boolean equals(Libro l) {
		return l.getTitulo().equals(titulo) && l.getAutor().equals(autor) && l.getFechaPublicacion().equals(fechaPublicacion) ;
	}
	
}
