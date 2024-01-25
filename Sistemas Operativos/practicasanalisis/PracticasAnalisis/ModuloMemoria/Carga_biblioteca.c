/* Carga_biblioteca.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Fernando P�rez Costoya
 * Versi�n: 1.0		Fecha: 4 Apr 2006
 * Descripci�n:
 *	Muestra el mecanismo de carga expl�cita de bibliotecas
 *	din�micas.
 *
 * Uso:
 *	Carga_biblioteca
 *
 * Compilaci�n:
 *	cc -m32 -Wall -o Carga_biblioteca Carga_biblioteca.c -ldl
 *
 */
#define MYNAME	"Carga_biblioteca"

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <dlfcn.h>

/* Funci�n que imprime el mapa de memoria del proceso en ese instante */
static void mostrar_mapa(char *titulo)
{
	char mandato[256];
	int mipid;

	printf("\n----------- %s ------------\n", titulo);
	mipid= getpid();
	sprintf(mandato, "pmap %d", mipid);
	system(mandato);
	printf("-------------------------------------------------------------------\n");
}


int main(int argc, char **argv)
{
	double valor;
	void *descriptor_bib;
	double (*fcos) (double);

	if (argc!=2) {
		fprintf(stderr, "Uso: %s argumento_coseno\n", MYNAME);
		exit(1);
	}

	/* Se muestra el mapa antes de cargar la biblioteca din�mica */
	mostrar_mapa("Antes de llamar a dlopen");
	
	/* Se carga la biblioteca matem�tica */
	if (!(descriptor_bib=dlopen("/usr/lib/libm.so", RTLD_LAZY))) {
		fprintf(stderr, MYNAME": %s\n", dlerror());
		return(1);
	}

	/* Se muestra el mapa antes de cargar la biblioteca din�mica */
	mostrar_mapa("Despu�s de llamar a dlopen");
	
	/* Busca el s�mbolo correspondiente al coseno */
	if (!(fcos=dlsym(descriptor_bib, "cos"))) {
		fprintf(stderr, MYNAME": %s\n", dlerror());
		return(1);
	}

	valor=atof(argv[1]);
	printf("Coseno(%f)=%f\n", valor, fcos(valor));

	/* Se descarga la biblioteca din�mica */
	dlclose(descriptor_bib);

	/* Se muestra el mapa despu�s de descargar la biblioteca din�mica */
	mostrar_mapa("Despu�s de llamar a dlclose");
	
	return 0;
}

