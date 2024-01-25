/* EnlaceF.c */
/*
 * Copyright 2006 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Pedro de Miguel
 * Versi�n: 1.0		Fecha: 21 enero 2006
 * Descripci�n:
 *	Realiza la llamada link con los argumentos suministrados.
 * Uso:
 *	EnlaceF fich_existente nuevo_nombre
 */
#define MYNAME	"EnlaceF"

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char * argv[])
{
	if (argc == 3) {
		link(argv[1], argv[2]);
	} else {
		printf("USO: %s fich_existente nuevo_nombre\n", MYNAME);
	}
	return 0;
}
