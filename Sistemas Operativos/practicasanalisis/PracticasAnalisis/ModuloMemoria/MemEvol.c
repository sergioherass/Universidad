/* Heap.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Fernando P�rez Costoya
 * Versi�n: 1.0		Fecha: 4 Apr 2006
 * Descripci�n:
 *	Heap
 *
 * Uso:
 *	Muestra la evoluci�n del heap seg�n ejecuta el programa.
 */
#define MYNAME	"Heap"

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

/* Funci�n que imprime el mapa de memoria del proceso en ese instante */
static void mostrar_mapa(char *titulo){
	char mandato[256];
	int mipid;

	printf("\n----------- %s ------------\n", titulo);
	mipid= getpid();
	sprintf(mandato, "pmap %d", mipid);
	system(mandato);
	printf("-------------------------------------------------------------------\n");
}

/* Funci�n de prueba */
static void funcion(void) {
	long vector[100000];

	vector[0]=0;
	vector[0]=vector[0];

	/* Se muestra el mapa dentro de la funci�n */
	mostrar_mapa("Desde dentro de la funci�n");
}


int main(int argc, char **argv) 
{

	
	long *vector;

	/* Se muestra el mapa antes de llamar a la funci�n */
	mostrar_mapa("Antes de llamar a funci�n");

	funcion();

	/* Se muestra el mapa despu�s de llamar a la funci�n */
	mostrar_mapa("Despu�s de llamar a funci�n");

	/* Se muestra el mapa antes de llamar a malloc */
	mostrar_mapa("Antes de llamar a malloc");
	

	vector=malloc(40000*sizeof(long));

	/* Se muestra el mapa despu�s de llamar a malloc */
	mostrar_mapa("Despu�s de llamar a malloc");

	free(vector);
	
	/* Se muestra el mapa despu�s de llamar a free */
	mostrar_mapa("Despu�s de llamar a free");

	vector=malloc(100000*sizeof(long));
	

	/* Se muestra el mapa despu�s de llamar a malloc por segunda vez */
	mostrar_mapa("Despu�s de llamar a malloc por segunda vez");

	return 0;
}

	

