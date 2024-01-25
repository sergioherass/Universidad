/* Productor.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Fernando P�rez Costoya
 * Versi�n: 1.0		Fecha: 4 Apr 2006
 * Descripci�n:
 *	Este programa est� pensado para que sse ejecute en combinaci�n
 *	con el programa "Consumidor" conectados por una tuber�a.
 *
 * Uso:
 *	Productor | Consumidor
 */
#define MYNAME	"Productor"


#include <stdio.h>
#include <unistd.h>

#define MAX 256

struct datos {
	char * nombre;
	int puntuacion;
};
	
int main(void)
{
	char buf[MAX];
	int aux;
	struct datos undato;

	fprintf(stderr, MYNAME": Introduzca un nombre (cadena de caracteres) y una puntuacion (un entero) separados por un espacio:\n");
	scanf("%s%d", buf, &aux);

	undato.nombre= buf;
	undato.puntuacion= aux;

	
	fprintf(stderr, "PRODUCTOR: %s - %d \n", undato.nombre, undato.puntuacion);
	fprintf(stderr, "----------------------------------------------------------\n");
	
	write(1, &undato, sizeof(undato));

	return 0;
}
