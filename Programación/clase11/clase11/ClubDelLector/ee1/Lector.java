package clase11.ClubDelLector.ee1;

import java.util.ArrayList;

/**
 * @author asr
 */


public class Lector {
	
	private int nSocio;
	private String nombre;
	private ArrayList<Libro> historicoLectura;
	
	public Lector(int nSocio,String nombre) {
		this.nSocio=nSocio;
		this.nombre=nombre;
		historicoLectura = new ArrayList<Libro>() ;
	}
	
	public int getNSocio(){
		return nSocio;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public Libro getLibroLeido(int pos) {
		if(pos<1||pos>historicoLectura.size())
			return null ;
		else 
			return historicoLectura.get(pos-1) ;
	}
	
	public void leerLibro(Libro leer) {
		leer.prestado();
		if(!historicoLectura.add(leer)) {
			for(int i=0; i<historicoLectura.size()-1; i++) {
				historicoLectura.set(i,historicoLectura.get(i+1)) ;
			}
			historicoLectura.set(historicoLectura.size()-1,leer) ;
		}
	}
	
	public boolean equals(Lector l) {
		if(l.historicoLectura.size()==historicoLectura.size()) {
			for(int i=0; i<historicoLectura.size();i++) {
				if(!l.historicoLectura.get(i).equals(historicoLectura.get(i)))
					return false;
			}
		} else 
			return false ;
		
		return nSocio==l.nSocio ;
	}

	
}
