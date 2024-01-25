/* Argumentos.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Luis Miguel Mazaira 
 * Versi�n: 1.0		Fecha: 27 Sep 20049
 * Descripci�n:
 *	Muestra los argumentos de invocaci�n
 * Uso:
 *	Argumentos [argumentos...]
 */
#define MYNAME	"Argumentos"

#include <stdio.h>

int main(int argc, char * argv[])
{
	int  num;

	printf(MYNAME": argc	=%d\n", argc);

	for (num=0; num < argc; num++)
		printf(MYNAME": argv[%d]	=%s\n", num, argv[num]);

	return 0;
}
