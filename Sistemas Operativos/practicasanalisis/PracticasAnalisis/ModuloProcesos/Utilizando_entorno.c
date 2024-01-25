/* Utilizando_Entorno.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�ica de Madrid, Facultad de Inform�ca
 *
 * Autor: Luis Miguel Mazaira
 * Versi�1.0		Fecha: 19 9 9 9 9 9 9 9 9 Sept 20009
 * Descripci� * 	E
 * Uso:
 *	Utilizando_Entorno
 */
#define MYNAME	"Utilizando_Entorno"

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
	char *var;
	if ((var=getenv("SEMILLA")))
		srand(atoi(var));
 
	printf("\n\t[%d] - [%d]\n\n",rand()%6+1, rand()%6+1);

	return 0;
}
