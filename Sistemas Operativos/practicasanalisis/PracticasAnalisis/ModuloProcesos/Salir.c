/* Salir.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Pedro de Miguel
 * Versi�n: 1.0		Fecha: 23 Nov 2005
 * Descripci�n:
 *	Utiliza la llamada exit para terminar bruscamente el proceso.
 *	El valor de terminaci�n depender� del pid del proceso.
 * Uso:
 *	Salir
 */
#define MYNAME	"Salir"

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(void)
{
	int pid = getpid();

	printf(MYNAME": Antes  \tPID=%d=0x%x\n", pid, pid);
	exit(pid);		/* El proceso termina! */
	/*
	 * == 0 significa terminaci�n   correcta.
	 * != 0 significa terminaci�n incorrecta.
	 *
	 * S�lo sirve el byte menos significativo: 0..255
	 *
	 *	exit(256) equivale a exit(0)
	 *	exit(-1)  equivale a exit(255)
	 */
}
