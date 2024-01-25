package aed.loops;

public class Utils {
  public static int maxNumRepeated(Integer[] a, Integer elem)  {
      int maxElems = 0, contador = 0, i, j;
      boolean coincide;
      
      for(i=0; i<a.length; i++) { 	// La i la puedo definir en este momento 
    	  if(a[i].equals(elem)) { 	// La j la puedo definir en este momento pero NUNCA dentro del 
    		  coincide = true;		// 2o for anidado porque si no, no podriamos hacer la comprobacion
    		  contador = 1;
    		  for(j = i; j<a.length-1 && coincide; j++) {
    			  if(a[j].equals(a[j+1]))
    				  contador++;
    			  else 
    				  coincide = false;
    		  }
    		  // Esto es util para que cuando salgamos no veamos otra vez elementos repetidos, 
    		  // es decir, saltarme todas las posiciones que ya he estudiado y que estan seguidas
    		  i = j;
    		  // Si he encontrado un numero mayor de elementos seguidos de los que teniamos previamente,
    		  // actualizo este valor
    		  if(contador > maxElems)
    			  maxElems = contador;
    	  }
    	  
      }
      
      return maxElems;
  }
}
