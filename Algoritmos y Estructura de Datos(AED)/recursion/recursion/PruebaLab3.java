package aed.recursion;

import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

public class PruebaLab3 {
	
	public static void main(String args[]) {
		PositionList<Character> key = new NodePositionList<Character>();
		key.addLast('A');
		key.addLast('B');
		key.addLast('D');
		key.addLast('L');
		key.addLast('M');
		key.addLast('O');
		key.addLast('S');
		key.addLast('U');
		
		PositionList<Character> text = new NodePositionList<Character>();
		text.addLast('S');
		text.addLast('A');
		text.addLast('L');
		text.addLast('U');
		text.addLast('D');
		
		PositionList<Integer> encodedText = Crypto.encrypt(key,text);
		
		System.out.println(encodedText); // Syso + crtl + space
		
		System.out.println(Crypto.decrypt(key,encodedText));
		
		Lugar lugarInicial = new Lugar(new Punto[][] { { new Punto(null,false,false,false,true),new Punto(null,false,false,true,true),
			  new Punto(null,true,false,true,true),new Punto(null,false,false,true,false),new Punto(null,true,false,false,false),
			  new Punto(null,true,false,false,false),new Punto(null,false,false,false,false) }, 
			  { new Punto(null,false,false,false,true),
				  new Punto(null,false,false,true,false),new Punto(null,true,true,false,true),new Punto(null,true,false,true,false),
				  new Punto(null,false,true,false,false),new Punto(null,false,true,false,true),new Punto(null,true,false,true,false) }, 
			  { new Punto(null,false,false,false,false),new Punto(null,false,false,false,false),new Punto(null,false,true,false,false),
					  new Punto(null,false,true,false,true),new Punto(null,true,false,true,false),new Punto(null,true,false,false,true),
					  new Punto(null,true,true,true,false) }, { new Punto(null,true,false,false,true),
						  new Punto(null,true,false,true,false),new Punto(null,true,false,false,true),
						  new Punto(null,false,false,true,true),new Punto(null,true,true,true,true),
						  new Punto(null,true,true,true,false),new Punto(null,false,true,false,false) }, 
			  { new Punto(null,true,true,false,false),new Punto(null,true,true,false,true),new Punto(null,true,true,true,true),
							  new Punto(null,true,false,true,false),new Punto(null,false,true,false,true),
							  new Punto(null,false,true,true,true),new Punto(null,false,false,true,false) }, 
			  { new Punto(54,false,true,false,false),new Punto(null,false,true,false,false),new Punto(null,false,true,false,false),
								  new Punto(null,false,true,false,false),new Punto(null,false,false,false,false),
								  new Punto(null,false,false,false,false),new Punto(null,false,false,false,false) } },
				  0,0);
		
		System.out.println(Explorador.explora(lugarInicial));
	}

}
