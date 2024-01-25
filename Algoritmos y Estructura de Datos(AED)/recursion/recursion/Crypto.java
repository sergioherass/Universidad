package aed.recursion;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;


public class Crypto {

	/*
	 * Devuelve una lista nueva de enteros que resulta
	 * de encifrar el texto (text) usando el alfabeto (key)
	 * 
	 */
	public static PositionList<Integer> encrypt(PositionList<Character> key, 
			PositionList<Character> text) {
		PositionList<Integer> res = new NodePositionList<Integer>();

		if(text!=null && !text.isEmpty()) 
			encryptRec(key,key.first(),text,text.first(),res,0);


		return res;
	}

	public static void encryptRec(PositionList<Character> key,
			Position<Character> cursorKey, PositionList<Character> text,
			Position<Character> cursorText, PositionList<Integer> res, int contador) {

		if(cursorText!=null) {
			if(cursorText.element()>cursorKey.element()) {
				encryptRec(key,key.next(cursorKey),text, cursorText, res, contador+1);
			} else if(cursorText.element()<cursorKey.element()) {
				encryptRec(key,key.prev(cursorKey),text, cursorText, res, contador-1);
			} else {
				res.addLast(contador);
				encryptRec(key,cursorKey,text, text.next(cursorText), res, 0);
			}
		}
	}

	/*
	 * Devuelve una lista nueva de caracteres que resulta de descifrar
	 * el texto codificado (encodedText) usando el alfabeto (key)
	 * 
	 */
	public static PositionList<Character> decrypt(PositionList<Character> key, 
			PositionList<Integer> encodedText) {
		PositionList<Character> res = new NodePositionList<Character>();

		if(encodedText!=null) 
			decryptRec(key,key.first(),encodedText,encodedText.first(),res,0);

		return res;
	}

	public static void decryptRec(PositionList<Character> key,
			Position<Character> cursorKey, PositionList<Integer> encodedText, 
			Position<Integer> cursorText, PositionList<Character> res, int contador) {
		if(cursorText!=null) {
			if(contador>cursorText.element()) { // retrocedo
				decryptRec(key,key.prev(cursorKey),encodedText,cursorText,res,contador-1);
			} else if(contador<cursorText.element()) { // avanzo
				decryptRec(key,key.next(cursorKey),encodedText,cursorText,res,contador+1);
			} else {
				res.addLast(cursorKey.element());
				decryptRec(key,cursorKey,encodedText,encodedText.next(cursorText),
						res, 0);
			}
		}
	}


}
