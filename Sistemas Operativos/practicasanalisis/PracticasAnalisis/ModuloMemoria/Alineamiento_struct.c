/* Alineamiento_struct.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Fernando P�rez Costoya
 * Versi�n: 1.0		Fecha: 4 Apr 2006
 * Modificado por: Luis Miguel Mazaira
 * Versi�n: 1.1		Fecha: 9 Sep 2009
 * Descripci�n:
 *	Muestra c�mo cada campo de una estructura se almacena en una direcci�n
 *	que respeta las restricciones de alineamiento. N�tese que esto puede
 *	provocar "huecos" (zonas no usadas en la memoria)
 *
 * Uso:
 *	Alineamiento_struct
 */
#define MYNAME	"Alineamiento_struct"

#include <stdio.h>

 #include <stddef.h>
struct tipo {
	short s;
	int i;
	char c;
};


int main(int argc, char *argv[])
{
	struct tipo var;

	printf("Tama�o        %d\n", (int) sizeof(struct tipo));
	printf("campo short s:  dir %p\n", &var.s);
	printf("campo int i:    dir %p\n", &var.i);
	printf("campo char c:   dir %p\n", &var.c);


	printf("sizeof(struct var)=%ld\n", (long) sizeof(struct tipo));
	printf("Memory Address var:   dir %p\n", &var);
	printf("Memory Address field s: %p -> %ld\n", &var.s, (long) offsetof(struct tipo, s));
	printf("Memory Address field i: %p -> %ld\n", &var.i, (long) offsetof(struct tipo, i));
	printf("Memory Address field c: %p -> %ld\n", &var.c, (long) offsetof(struct tipo, c));

	return 0;
}
