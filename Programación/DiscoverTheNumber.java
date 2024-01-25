/**
 * 
 */
package game;

/**
 * Esta clase implementa la lógica del juego que consiste en
 * descubrir un número 'oculto'. Este número se genera de forma aleatoria. El número estará
 * comprnedido entre 1 y un máximo dado, ambos números incluidos. El juego tendrá un número de rondas máximas para encontrar 
 * la respuesta correcta. En el participan un número de jugadores
 * @author agonzalez
 *
 */
//Debe poner el email del autor despues del igual recuerde dejar un espacio en blanco entre el igual y el email 
//emailUPMAutor1 =  
public class DiscoverTheNumber {
	/**
	 * Contiene el número 'oculto'
	 * Este número debe estar entre 1 y n
	 */
	private int lookingFor;
	//TODO: Defina todos los atributos que precise
	private int maxRange;
	private int rounds;
	private int nPlayers;
	private int move[];

	/**
	 * Recibe el número máximo del rango, el número de rondas del juego y el número de jugadores
	 * <br>PRE: maxRange &gt; 0, rounds &gt; 0 y nPlayer &gt; 0 
	 * @param maxRange limite superior en el que debe estar el número que se va a buscar
	 * @param rounds número de rodnas que tendrá la partida
	 * @param nPlayers número de jugadores que tendrá la partida
	 */
	public DiscoverTheNumber (int maxRange, int rounds, int nPlayers) {
		//Se debería verificar las precondiciones aquí
		if(maxRange>0 && rounds>0 && nPlayers>0) {
			this.lookingFor = (int) (Math.random() * maxRange)+1;
			//TODO realice todo el proceso de incialización que requiera
			this.maxRange = maxRange;
			this.rounds = rounds;
			this.nPlayers = nPlayers;
			this.move = new int[nPlayers];
		}
	}

	/**
	 * Retorna el número de jugadores que hay en este juego
	 * @return Se retorna el número de jugadores que participan en el juego 
	 */
	public int getNPlayers() {
		//TODO
		return nPlayers; //DUMMY
	}
	
	/** 
	 * Método auxiliar creado por mi para que cuando NO ganemos podamos saber cual era el numero que
	 * habiamos estado buscando en las rondas de juego
	 * 
	 * Para ello en el else // No hay ganadores de la clase MainApp he añadido tras el primer print
	 * System.out.println("El número que buscabamos era... " + discover.getLookingFor());
	 * 
	 * @return Se retorna el numero aleatorio que buscamos
	 */
	public int getLookingFor() {
		return lookingFor;
	}
	
	/**
	 * Retorna el resultado de la jugada del jugador indicado.
	 * <br>PRE: player &gt; 0 y player &lt;= nPlayers y se ha realizado una jugada;
	 * @param player jugador al que corresponde la jugada. El primer jugador es el 1 y el último es nPlayers
	 * @return retorna el resultado para el jugador indicicado tras la última ronda jugada
	 */
	public Result giveResult(int player) {
		//Verificar las precondiciones
		//Datos válidos
		//TODO: Retornar el resultado para el jugador indicado
		Result resultado = null;
		if(player>0 && player<=nPlayers && setMovePlayer(player,move[player-1]) ) {
			if(move[player-1]==lookingFor){
				resultado = Result.EQUALS;
			}else if(move[player-1]<lookingFor){
				resultado = Result.DOWN;
			}else{
				resultado = Result.UP;
			}
		}
		return resultado; //DUMMY

	}

	/**
	 * Este método indica si se ha terminado la partida. Una partida termina cuando se acaban las rondas o alguien
	 * acierta el número
	 * @return retorna cierto si el juego ha terminado
	 */
	public boolean isGameFinished() {
		//TODO
		return (rounds==0 || winners().length()>0); //DUMMY
	}

	/**
	 * Este método se encarga de procesar los movimientos de una ronda y generar el valor para cada jugador
	 * <br>PRE: Rondas que quedan por disputar &gt; 0  y nadie ha ganado el juego y todos los jugadores han marcado sus movimientos
	 */
	public void play() {
		//Verificar precondiciones, si la precondición no se cumple habría que lanzar una excepción (Por ahora no se hará)
		//Los datos son correctos
		//TODO procesar una ronda
		boolean rondaJugadaCorrectamente = true;
		if(rounds>0 && !isGameFinished()) {
			for(int i=0; i<nPlayers; i++) {
				if(!setMovePlayer(i+1,move[i])) {
					rondaJugadaCorrectamente = false;
				}
			}
			if(rondaJugadaCorrectamente)
				rounds--;
		}
	}


	/**
	 * Este método establece la jugada de un jugador que aun no haya jugado en esta ronda
	 * <br>PRE: Rondas que quedan por disputar &gt; 0 y move &gt; 0 y move &lt;= manxRange y player &gt; 0 y player &lt;= nPlayers;
	 * @param player jugador al que corresponde la jugada. El primer jugador es el 1 y el último es nPlayers
	 * @param move movimiento que se realiza. El movimiento debe estar entre 1 y maxRange (ambos incluidos)
	 * @return retorna cierto si el jugador no ha establecio aun el movimiento en esta jugada y aun quedan rondas
	 */
	public boolean setMovePlayer(int player, int move) {//setMovePlayer
		//Se debe verificar la precondición si no se cumple se lanza una excepción (Por ahora no se hará)
		//Los datos cumplen la precondición
		//TODO
		boolean haJugado = false;
		if(rounds>=0 && move>0 && move<=maxRange && player>0 && player<=nPlayers) {
			this.move[player-1] = move;
			haJugado = true;
		}
		return haJugado; //DUMMY
	}//setMovePlayer

	/**
	 * Este método retorna un String con los ganadores. Los ids de los jugadores iran sperados por coma. Si no
	 * hay ganador se retornará la cadena vacía
	 * @return Retorna la lista de los IDs de los jugadores ganadores seperadas por coma. Si no hay ganadores la longitud 
	 * del String será 0
	 */

	public String winners() {
		//TODO
		String ganadores = "";
		for(int i=0; i<nPlayers; i++) {
			if(move[i]==lookingFor) {
				if(ganadores.length()>0)
					ganadores += ",";
				ganadores += (i+1);
			}
		}
		return ganadores;//DUMMY
	}



	//Enum auxiliar
	/**
	 * Este enumerado define los posibles valores para un resultado.
	 * @author agonzalez
	 *
	 */
	public enum Result {
		/**
		 * El valor está por encima del buscado
		 */
		UP,
		/**
		 * El valor está por debajo del buscado
		 */
		DOWN,
		/**
		 * El valor es igual al buscado
		 */
		EQUALS}
}
