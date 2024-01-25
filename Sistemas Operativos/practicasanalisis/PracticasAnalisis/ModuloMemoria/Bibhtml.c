/* Bibhtml.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Fernando P�rez Costoya
 * Versi�n: 1.0		Fecha: 4 Apr 2006
 * Descripci�n:
 *	M�dulo hipot�tico que procesase archivos HTML.
 *	Este fichero se compilar� como una biblioteca y se usar�
 *	desde el programa Navegador.
 *
 * Uso:
 *	Lo usa el programa Navegador
 *
 *	Compilaci�n como biblioteca din�mica:
 *		cc -fPIC -shared -o Bibhtml Bibhtml.c
 *
 */
#define MYNAME	"Bibhtml"

#include <stdio.h>

void procesar(char *archivo)
{
	printf("Soy la biblioteca bibhtml y estoy procesando %s\n",
		archivo);
}
