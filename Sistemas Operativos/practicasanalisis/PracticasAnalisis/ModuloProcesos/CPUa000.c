/* CPUa100.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Luis Miguel Mazaira 
 * Versi�n: 1.0		Fecha: 27 Sept  2009
 * Descripci�n:
 	El proceso pasa a estado dormido. 
 *	No termina nunca. Habr� que matarlo.
 * Uso:
 * 	CPUa00
 */
#define MYNAME	"CPUa000"

#include <unistd.h>

int main(void)
{
	pause();
	return 0;
}
