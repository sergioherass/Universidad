/* Alineamiento.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Fernando P�rez Costoya
 * Versi�n: 1.0		Fecha: 4 Apr 2006
 * Modificado por: Luis Miguel Mazaira
 * Versi�n: 1.1		Fecha: 9 Sep 2009
 * Descripci�n:
 *	Muestra c�mo cada variable se almacena en una direcci�n de manera que
 *	se respeten las restricciones de alineamiento. N�tese que esto puede
 *	provocar "huecos" (zonas no usadas en la memoria)
 *
 * Uso:
 *	Alineamiento
 */
#define MYNAME	"Alineamiento"

#include <stdio.h>

int main(int argc, char *argv[])
{
	int i1;
	short int s1;
	char c2;
	short int s2;
	char c1;
	short int s3;
	int i2;

	printf("int i2:   dir %p\n", &i2);
	printf("short s3: dir %p\n", &s3);
	printf("char c1:  dir %p\n", &c1);
	printf("short s2: dir %p\n", &s2);
	printf("char c2:  dir %p\n", &c2);
	printf("short s1: dir %p\n", &s1);
	printf("int i1:   dir %p\n", &i1);
	printf("Total         %d\n", (int) ((char *)&i1-(char*)(&i2-1)));
	return 0;
}
