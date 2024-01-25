/* PunteroFichero.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Francisco Rosales
 * Versi�n: 1.0		Fecha: 10 Nov 2005
 * Descripci�n:
 * 	Dos procesos, padre e hijo, escriben sobre un mismo fichero
 * 	10 l�neas de 30 caracteres, cediendo la CPU a cada caracter.
 * 	El padre escribe 'P's y el hijo escribe 'H's.
 * 	El contenido final del fichero ser� muy distinto seg�n
 * 	compartan o no el puntero sobre el fichero.
 * Uso:
 *	PunteroFichero fichero [C]
 */
#define MYNAME "PunteroFichero"

#include <sys/wait.h>
#include <fcntl.h>
#include <sched.h>	/* sched_yield() */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char * argv[])
{
	int pid, fd, i;
	char * letra;

	if (argc != 2 && (argc != 3 || *argv[2] != 'C')) {
		printf("Uso: "MYNAME" fichero [C]\n");
		exit(0);
	}

	if (!argv[2])		/* Sin compartir posici�n. */
		if ((pid = fork()) == -1) {
			perror(MYNAME" fork()");
			exit(1);
		}

	fd = creat(argv[1], 0666);
	if (fd < 0) {
		perror(MYNAME": creat()");
		exit(1);
	}

	if (argv[2])		/* Compartiendo posici�n. */
		if ((pid = fork()) == -1) {
			perror(MYNAME" fork()");
			exit(1);
		}

	letra = (pid == 0 ? "A" : "B");
	i = 0;
	while (i < 10*30) {
		write(fd, letra, 1);
		sched_yield();
		i++;
		if (i%30 == 0)
			write(fd, "\n", 1);
	}
	wait(NULL);
	return 0;
}
