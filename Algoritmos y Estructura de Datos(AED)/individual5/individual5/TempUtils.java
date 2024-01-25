package aed.individual5;

import java.util.Iterator;

import es.upm.aedlib.Entry;
import es.upm.aedlib.Pair;
import es.upm.aedlib.map.*;


public class TempUtils {
  public static Map<String,Integer> maxTemperatures(long startTime,
                                                    long endTime,
                                                    TempData[] tempData) {
	  
	    long tiempo=0;
		int temp=0;
	  	TempData elemento;
		String localizacion;
		
	  	Map<String,Integer> res= new HashTableMap<String,Integer>();
	  
		for(int i=0; i<tempData.length; i++)  {
			
			 elemento= tempData[i];
			 tiempo= elemento.getTime();
			
			if( tiempo<=endTime && tiempo>=startTime) { // la condicion es que el tiempo se encuentre dentro del intervalo introducido como parametro
				
				 localizacion= elemento.getLocation();
				 temp= elemento.getTemperature();
				
				if(res.containsKey(localizacion)) {  // evalua si tiene la localizacion pedida
					
					if(res.get(localizacion)<temp) { //vemos quien tiene mayor temperatura y actualizamos
						
						 res.put(localizacion,temp);
					}
				} else {                             // en el caso de que no contenga la localizacion entonces se añade directamente
					 res.put(localizacion,temp);
				}
			}
		}

		return res;
	}
    
 


  public static Pair<String,Integer> maxTemperatureInComunidad(long startTime,
                                                               long endTime,
                                                               String region,
                                                               TempData[] tempData,
                                                               Map<String,String> comunidadMap) {
	  
	  	Pair<String,Integer> res= null;
	    Map<String,Integer> max_temp = maxTemperatures(startTime, endTime, tempData);
	    Entry<String,Integer> e = null;
		Iterator<Entry<String,Integer>> it= max_temp.entries().iterator();
		
		
		int temp=0;
		String localizacion;
		
		
		while(it.hasNext()) {
			
			e= it.next();
			localizacion= e.getKey();
			temp= e.getValue();
			
			 if(comunidadMap.get(localizacion).equals(region)) {  // la condicion es que la region este asociada a la localizacion  
				 
				if (res==null) { // en el caso de que el par sea nulo entonces lo aniado 
					res = new Pair<String,Integer>(localizacion,temp);
				} else { // en el caso contrario entonces voy comparando las temperaturas 
					
					if(res.getRight() <temp ) {
						
						res= new Pair<String,Integer>(localizacion,temp);
					}
				}
			}
		}
		
		return res;
	}
   
  }


