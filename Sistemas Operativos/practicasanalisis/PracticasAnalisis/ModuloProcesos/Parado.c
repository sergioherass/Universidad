/* Parado.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Francisco Rosales
 * Versi�n: 1.0		Fecha: 20 Sep 2004
 * Descripci�n:
 *	Programa que se para.
 *	La �nica manera de hacerlo terminar es enviarle una se�al que lo mate.
 * Uso:
 *	Parado
 */
#define MYNAME	"Parado"

#include <unistd.h>

int main(void)
{
	pause();
	return 0;
}
