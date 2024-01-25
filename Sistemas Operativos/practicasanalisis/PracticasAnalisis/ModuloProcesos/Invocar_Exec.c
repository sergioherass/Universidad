/* Invocar_Exec.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Francisco Rosales
 * Modificado por: Luis Miguel Mazaira
 * Versi�n: 1.0		Fecha: 29 Sep 20009
 * Descripci�n:
 *	Realiza la llamada exec para ejecutar el programa identificadores.
 * Uso:
 *	Invocar_Exec	
 */
#define MYNAME	"Invocar_Exec"

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(void)
{

	printf(MYNAME": Antes  \tPID=%d\tPPID=%d\n", getpid(), getppid());
	execl("./Identificadores", "./Identificadores", NULL);
	printf(MYNAME": Antes  \tPID=%d\tPPID=%d\n", getpid(), getppid());
	exit(1);

	return 0;
}
