/* Cada3Segs.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Francisco Rosales
 * Versi�n: 1.0		Fecha: 15 Oct 2005
 * Modificado por: Luis Miguel Mazaira
 * Versi�n: 1.1		Fecha: 19 Sep 2009
 * Descripci�n:
 * 	El proceso captura la se�al de alarma y se lanza una
 * 	cada 3 segundos.
 *	En el tratamiento de la alarma no se realiza ninguna accion.
 *	Para ver que efectivamente se trta una alarma cada 3segs, 
 *	solo es necesario eliminar el comentario del printf  en la 
 *	funcion de tratar_alarma
 * Uso:
 *	Cada3Segs
 */
#define MYNAME	"Cada3Segs"

#define _GNU_SOURCE
#include <string.h>
#include <signal.h>
#include <stdio.h>
#include <unistd.h>

void tratar_alarma(int signo)
{
	//printf(MYNAME": Se�al %s activada\n", strsignal(signo));
}

int main(void)
{
	struct sigaction act;

	/* establece el manejador para SIGALRM */
	act.sa_handler = tratar_alarma;	/* funci�n a ejecutar */
	act.sa_flags = 0;		/* ninguna opci�n especifica */
	sigaction(SIGALRM, &act, NULL);
	act.sa_handler = SIG_IGN;	/* ignora la se�al SIGINT */
	sigaction(SIGINT, &act, NULL);
	for (;;) {			/* recibe SIGALRM cada 3 segundos */
		alarm(3);
		pause();
	}
	return 0;
}
