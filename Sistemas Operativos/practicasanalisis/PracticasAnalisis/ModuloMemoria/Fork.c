/* Fork.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Fernando P�rez Costoya
 * Versi�n: 1.0		Fecha: 4 Apr 2006
 * Descripci�n:
 *	Fork: Muestra la evoluci�n del mapa de memoria de un proceso
 *	y el de su proceso hijo
 *
 * Uso:
 *	Fork
 *
 */
#define MYNAME	"Fork"


#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>


/* Funci�n que imprime el mapa de memoria del proceso en ese instante */
static void mostrar_mapa(char *titulo){
	char mandato[256];
	int mipid;

	mipid= getpid();
	printf("\n----------- Proceso %d: %s ------------\n", mipid, titulo);
	sprintf(mandato, "pmap %d", mipid);
	system(mandato);
	printf("-------------------------------------------------------------------\n");
}

int main(int argc, char **argv) {
	int id_pr;
	char *p;

	/* Se muestra el mapa antes del fork */
	mostrar_mapa("Antes del fork");
	
	id_pr=fork();

	if (!id_pr) {
		p=malloc(10000);
		sleep(1);
	}

	/* Ambos procesos muestran el mapa despu�s del fork */
	mostrar_mapa("Despu�s del fork");

	if (id_pr)
		wait(NULL);

	return 0;
}
