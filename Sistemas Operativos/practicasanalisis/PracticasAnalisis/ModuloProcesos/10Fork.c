/* 10Fork.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Francisco Rosales
 * Modificado por: Luis Miguel Mazaira
 * Versi�n: 12.		Fecha: 17 Sept 2009
 * Descripci�n:
 * Uso:
 *	10Fork
 */
#define MYNAME	"10Fork"

#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>

int main(void)
{
	pid_t pid;
	int i;
	int n = 10;

	for (i = 0; i < n; i++) {
		pid = fork();
		if (pid == 0)	
			break;
	}
	printf(MYNAME": [%d] El padre del proceso %d es %d\n", i, getpid(), getppid());
	sleep(1);
	return 0;
}
