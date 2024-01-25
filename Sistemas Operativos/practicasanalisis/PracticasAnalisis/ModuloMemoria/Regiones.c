/* Regiones.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Fernando P�rez Costoya
 * Versi�n: 1.0		Fecha: 4 Apr 2006
 * Modificado por: Luis Miguel Mazaira 
 * Versi�n: 1.1		Fecha: 9 Sep 2009
 * Descripci�n:
 *	Muestra a qu� regi�n pertenecen distintas variables de un programa
 *
 * Uso:
 *	Regiones
 */
#define MYNAME	"Regiones"

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <errno.h>

/* Funci�n que imprime el mapa de memoria del proceso en ese instante */
static void mostrar_mapa(){
	char mandato[256];
	int mipid;

	printf("\n-------------------------------------------------------------------\n");
	mipid= getpid();
	sprintf(mandato, "pmap %d ", mipid);
	system(mandato);
	printf("-------------------------------------------------------------------\n");
}

int A;		
int B=666;	
int C[4000];
const int D=1000;
int E[]={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

int main(int argc, char **argv) { 
	int F;		
	int G[2500];

	/* Se muestra el mapa */
	mostrar_mapa();

	/* Se imprime direcci�n de variables especificadas en enunciado */
	printf("\n%p\tmain\n%p\tA\n%p\tB\n%p\tC\n%p\tD\n%p\tE\n%p\tF\n%p\tG\n%p\targc\n", main, &A, &B, C,&D, E, &F, G, &argc);

	return 0;
}

	

