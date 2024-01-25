package cc.controlReciclado;
import org.jcsp.lang.*;

public class ControlRecicladoCSP implements ControlReciclado, CSProcess {
	private enum Estado { LISTO, SUSTITUIBLE, SUSTITUYENDO }

	private final int MAX_P_CONTENEDOR;
	private final int MAX_P_GRUA;

	// Canales 
	private Any2OneChannel chNotificarPeso;
	private Any2OneChannel chIncrementarPeso;
	private Any2OneChannel chNotificarSoltar;
	private Any2OneChannel chPrepararSustitucion;
	private Any2OneChannel chNotificarSustitucion;

	public ControlRecicladoCSP (int max_p_contenedor, int max_p_grua) {
		MAX_P_CONTENEDOR = max_p_contenedor;
		MAX_P_GRUA = max_p_grua;
		new ProcessManager(this).start();

		// Inicializamos los canales:
		chNotificarPeso = Channel.any2one();
		chIncrementarPeso = Channel.any2one();
		chNotificarSoltar = Channel.any2one();
		chPrepararSustitucion = Channel.any2one();
		chNotificarSustitucion = Channel.any2one();
	}

	public void notificarPeso(int p) {
		chNotificarPeso.out().write(p);
	}

	public void incrementarPeso(int p) {
		chIncrementarPeso.out().write(p);
	}

	public void notificarSoltar() {
		chNotificarSoltar.out().write(null);
	}

	public void prepararSustitucion() {
		chPrepararSustitucion.out().write(null);
	}

	public void notificarSustitucion() {
		chNotificarSustitucion.out().write(null);
	}

	public void run() {
		// Inicializamos los atributos del recurso
		int peso = 0;
		Estado estado = Estado.LISTO;
		int accediendo = 0;

		// Inicializamos los atributos auxiliares
		int p = 0;

		// soporte para recepcion alternativa condicional
		// Nombres simbolicos para los indices de servicios
		final int NOTIFICAR_PESO = 0;
		final int INCREMENTAR_PESO = 1;
		final int NOTIFICAR_SOLTAR = 2;
		final int PREPARAR_SUSTITUCION = 3;
		final int NOTIFICAR_SUSTITUCION = 4;

		// Entradas de la select
		final AltingChannelInput[] entradas = {chNotificarPeso.in(), chIncrementarPeso.in(), chNotificarSoltar.in(),
				chPrepararSustitucion.in(), chNotificarSustitucion.in()};

		// Recepcion alternativa
		final Alternative servicios = new Alternative(entradas);

		// Sincronizacion condicional en la select
		boolean[] sincCond = new boolean[5];

		sincCond[NOTIFICAR_SOLTAR] = true;
		sincCond[NOTIFICAR_SUSTITUCION] = true;

		// el servidor ejecuta un bucle de servicio infinito
		while(true) {

			// Estudio de las CPREs
			sincCond[NOTIFICAR_PESO] = estado != Estado.SUSTITUYENDO;
			sincCond[INCREMENTAR_PESO] = estado != Estado.SUSTITUYENDO;
			sincCond[PREPARAR_SUSTITUCION] = estado == Estado.SUSTITUIBLE && accediendo == 0;

			switch(servicios.fairSelect(sincCond)) {
			case NOTIFICAR_PESO:
				p = (int)chNotificarPeso.in().read();

				// Estudio de la PRE:
				if(p <= 0 || p > MAX_P_GRUA)
					throw new IllegalArgumentException("El peso debe estar entre (0, MAX_P_GRUA]");

				// POST
				if(peso+p > MAX_P_CONTENEDOR)
					estado = Estado.SUSTITUIBLE;
				else
					estado = Estado.LISTO;
				break;

			case INCREMENTAR_PESO:
				p = (int)chIncrementarPeso.in().read();

				// Estudio de la PRE:
				if(p <= 0 || p > MAX_P_GRUA)
					throw new IllegalArgumentException("El peso debe estar entre (0, MAX_P_GRUA]");

				// Estudio de la CPRE
				if(peso + p <= MAX_P_CONTENEDOR) {
					// POST
					peso += p;
					accediendo++;
				}
				break;

			case NOTIFICAR_SOLTAR:
				chNotificarSoltar.in().read();

				// POST
				accediendo--;
				break;

			case PREPARAR_SUSTITUCION: 
				chPrepararSustitucion.in().read();

				// POST
				estado = Estado.SUSTITUYENDO;
				accediendo = 0;
				break;

			case NOTIFICAR_SUSTITUCION:
				chNotificarSustitucion.in().read();

				// POST
				peso = 0;
				estado = Estado.LISTO;
				accediendo = 0;
				
			} // switch
		} // while
	}
}
