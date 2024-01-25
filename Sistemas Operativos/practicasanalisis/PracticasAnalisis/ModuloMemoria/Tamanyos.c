/* Tamanyos.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Fernando P�rez Costoya
 * Versi�n: 1.0		Fecha: 4 Apr 2006
 * Modificado por: Luis Miguel Mazaira
 * Versi�n: 2.0		Fecha: 9 Sep 2009
 * Descripci�n:
 *	Muestra el tama�o de diversos tipo de datos
 *
 * Uso:
 *	Tamanyos
 */
#define MYNAME	"Tamanyos"

#include <stdio.h>
#include <limits.h>

int main(int argc, char *argv[]) 
{

	printf("\n\n                           Size %22s %22s\n", "Min", "Max");
	printf("----------------------------------------------------------------------------------\n");
	printf("sizeof(char):         %4d bytes %22d %22d \n", (int) sizeof(char),CHAR_MIN,CHAR_MAX);
	printf("sizeof(short):        %4d bytes %22d %22d \n", (int) sizeof(short),SHRT_MIN,SHRT_MAX);
	printf("sizeof(int):          %4d bytes %22d %22d \n", (int) sizeof(int),INT_MIN,INT_MAX);
	printf("sizeof(long):         %4d bytes %22ld %22ld\n", (int) sizeof(long),LONG_MIN,LONG_MAX);
	printf("------------------------------\n");
	printf("sizeof(long long):    %4d bytes\n", (int) sizeof(long long));
	printf("sizeof(float):        %4d bytes\n", (int) sizeof(float));
	printf("sizeof(double):       %4d bytes\n", (int) sizeof(double));
	printf("sizeof(long double):  %4d bytes\n", (int) sizeof(long double));
	printf("------------------------------\n");
	printf("sizeof(*int_ptr):     %4d bytes\n", (int) sizeof(int *));

	return 0;
}
