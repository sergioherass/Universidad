/* CambiaDir.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Francisco Rosales
 * Versi�n: 1.0		Fecha: 10 Nov 2005
 * Descripci�n:
 *	Hace la correspondiente llamada al servicio chdir,
 *	mostrando el directorio actual de trabajo antes y despu�s.
 * Uso:
 *	Chdir directorio
 */
#define MYNAME "CambiaDir"

#include <limits.h>
#include <stdio.h>
#include <unistd.h>

int main(int argc, char * argv[])
{
	char path[PATH_MAX+1];

	if (argc != 2) {
		printf("Uso: %s directorio\n", MYNAME);
	} else {
		printf("%s:  Antes  cwd=%s\n", MYNAME, getcwd(path, PATH_MAX));
		if (chdir(argv[1]) < 0) {
			fprintf(stderr, "%s: ", MYNAME); perror(argv[1]);
		}
		printf("%s: Despues cwd=%s\n", MYNAME, getcwd(path, PATH_MAX));
	}
	return 0;
}
