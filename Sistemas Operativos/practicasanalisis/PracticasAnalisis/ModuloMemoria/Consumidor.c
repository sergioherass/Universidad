/* Consumidor.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Fernando P�rez Costoya
 * Versi�n: 1.0		Fecha: 4 Apr 2006
 * Descripci�n:
 *	Este programa est� pensado para que see ejecute en combinaci�n
 *	con el programa "Productor" conectados por una tuber�a.
 *
 * Uso:
 *	Productor | Consumidor
 */
#define MYNAME	"Consumidor"


#include <stdio.h>
#include <unistd.h>


struct datos {
	char * nombre;
	int puntuacion;
};
	
int main(void)
{
	struct datos undato;

	read(0, &undato, sizeof(undato));

	fprintf(stderr, "CONSUMIDOR: \n");
	printf("\tPuntuaci�n %d\n", undato.puntuacion);
	printf("\tNombre %s\n", undato.nombre);

	return 0;
}
