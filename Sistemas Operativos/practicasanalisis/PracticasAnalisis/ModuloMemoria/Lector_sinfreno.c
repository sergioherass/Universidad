/* Lector_sinfreno.c */
/*
 * Copyright 2021 datsi.fi.upm.es.
 * Universidad Politécnica de Madrid, Facultad de Informática
 *
 * Autor: Fernando Pérez Costoya
 * Versión: 2.0		Fecha: 8 May 2021
 * Descripción:
 *	Lector_sinfreno: Lee un fichero proyectado saliéndose de la zona
 *	proyectada para ilustrar qué ocurre en esta situación.
 *
 * Uso:
 *	Lector_sinfreno fichero
 *
 */
#define MYNAME	"Lector_sinfreno"

#include <sys/types.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>
#include <ctype.h>
#include <stdlib.h>


int main(int  argc, char **argv) {
	int fd, i;
	struct stat bstat;
	char *org;

	if (argc!=2) {
		fprintf (stderr, "Uso: %s archivo\n", MYNAME);
		return(1);
	}

	/* Abre el archivo para lectura */
	if ((fd=open(argv[1], O_RDONLY))<0) {
		perror(MYNAME": No puede abrirse el archivo");
		return(1);
	}

	/* Averigua el numero de caracteres del archivo */
	if (fstat(fd, &bstat)<0) {
		perror(MYNAME": Error en fstat del archivo");
		close(fd);
		return(1);
	}

	/* Asegura que no quede pegado a otra región */
	if ((org=mmap((caddr_t) 0, bstat.st_size + 2*getpagesize(), PROT_NONE,
				MAP_ANON|MAP_PRIVATE, -1, 0)) == MAP_FAILED) {
		perror(MYNAME": Error en la proyeccion");
		close(fd);
		return(1);
	}

	/* Se proyecta el archivo */
	if ((org=mmap((caddr_t) org+getpagesize(), bstat.st_size, PROT_READ,
				MAP_FIXED|MAP_PRIVATE, fd, 0)) == MAP_FAILED) {
		perror(MYNAME": Error en la proyeccion del archivo");
		close(fd);
		return(1);
	}


	/* Se cierra el archivo */
	close(fd);

	for (i=0; ;i++)
		if (i<bstat.st_size)
			printf("byte[%d](dentro del fichero): %hhu\n", i, org[i]);
		else
			printf("byte[%d](fuera del fichero): %hhu\n", i, org[i]);

	return(0);
}
