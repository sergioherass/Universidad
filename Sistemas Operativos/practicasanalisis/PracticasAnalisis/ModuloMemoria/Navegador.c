/* Navegador.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Fernando P�rez Costoya
 * Versi�n: 1.0		Fecha: 4 Apr 2006
 * Descripci�n:
 *	Navegador: Averigua en tiempo de ejecuci�n qu� biblioteca debe usar
 *	para procesar un archivo dependiendo de la extensi�n de su nombre.
 *	Usa la t�cnica de la carga expl�cita de bibliotecas din�micas
 *
 * Uso:
 *	Navegador
 *
 * Compilaci�n:
 *	cc -Wall -o Navegador Navegador.c -ldl
 *
 */
#define MYNAME	"Navegador"

#include <stdio.h>
#include <unistd.h>
#include <dlfcn.h>
#include <string.h>
#include <stdlib.h>

/* Busca en el archivo de configuraci�n qu� biblioteca est� asociada
   a la extensi�n especificada. Devuelve NULL si no la encuentra */

static char *buscar_biblio_en_config(char *extension_buscada) {
	FILE *fconfig;
	char *biblio;
	char extension_config[32];

	biblio=malloc(128*sizeof(char));

	if ((fconfig=fopen("config", "r"))==NULL) {
		perror(MYNAME": No puede abrir archivo de configuraci�n");
		exit(1);
	}
	while (fscanf(fconfig, "%s %s", extension_config, biblio)!=EOF) 
		if (strcmp(extension_config, extension_buscada)==0) {
			fclose(fconfig);
			return biblio;
		}

	fclose(fconfig);
	return NULL;
}

/* Dado un nombre de archivo devuelve su extensi�n. Retorna NULL si el
   nombre no tiene extensi�n o el punto es el �ltimo car�cter. */
static char *obtener_extension(char *archivo) {
		char *ext;

		ext=rindex(archivo, '.');
		/* Si hay un '.', ext != NULL */
		if (ext!=NULL) {
			/* se avanza despu�s del punto */
			ext++;
			/* Si '.' es �ltimo car., no hay realmente extensi�n */
			if (*ext=='\0')
				ext=NULL;
		}
		return ext;
}

/* Procesa el archivo usando la biblioteca din�mica especificada */
static void tratar_archivo(char *archivo, char *biblioteca){
	void *descriptor_bib;
	void (*procesar)(char *);

	/* Se carga la biblioteca din�mica */
	if (!(descriptor_bib=dlopen(biblioteca, RTLD_LAZY))) {
		fprintf(stderr, MYNAME": Error cargando biblioteca: %s\n", dlerror());
		return;
	}

	/* Busca el s�mbolo correspondiente a "procesar" */
	if (!(procesar=dlsym(descriptor_bib, "procesar")))
		/* La funci�n no existe */
		fprintf(stderr, MYNAME": Error: biblioteca no incluye funci�n procesar\n");
	else
		/* Finalmente, procesamos el archivo */
		procesar(archivo);

	/* Se descarga la biblioteca */
	dlclose(descriptor_bib);
}

int main() {
	char archivo[256];
	char *extension;
	char *biblioteca;

	printf("Introduzca el nombre del archivo que quiere procesar: ");
	while (scanf("%s", archivo)!=EOF) {
		/* Obtiene la extensi�n correspondiente al nombre */		
		extension=obtener_extension(archivo);
		if (extension==NULL)
			fprintf(stderr, MYNAME": Error: el nombre de archivo debe tener extensi�n\n");
		else {
			/* Busca la bobliteca asociada */
			biblioteca=buscar_biblio_en_config(extension);
			if (biblioteca==NULL)
				fprintf(stderr, MYNAME": Error: extensi�n no configurada\n");
			else
				/* procesa el archivo usando la bibloteca */
				tratar_archivo(archivo, biblioteca);
		}

		printf("Introduzca el nombre del archivo que quiere procesar: ");
	}
	return 0;
}
