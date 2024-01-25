/* Signal_C.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Francisco Rosales
 * Versi�n: 1.0		Fecha: 20 Sep 2004
 * Descripci�n:
 *	Programa que utiliza signal para ignorar la se�al INT,
 *	capturar QUIT y poner TERM a "por defecto".
 *	El manejador re-captura la se�al. Luego se para con un pause.
 *	Se puede ver su comportamiento frente a estas se�ales.
 * Uso:
 *	Signal_C
 */
#define MYNAME	"Signal_C"

#define _XOPEN_SOURCE
#include <stdio.h>
#include <signal.h>
#include <unistd.h>

void Handler(int signo)
{
	char * strsignal(int signo);

	printf(MYNAME": [%d] Recibida la se�al \"%s\"(%d)\n",
		getpid(), strsignal(signo), signo);
	signal(signo, Handler);		/* <<=== Re-manejar se�al */
}

int main(void)
{
	signal(SIGINT,  SIG_IGN);	/* <<=== Ignorar se�al */
	signal(SIGQUIT, Handler);	/* <<=== Manejar se�al */
	signal(SIGTERM, SIG_DFL);	/* <<=== Acci�n por defecto */

	while (1)
		pause();
	return 0;
}
