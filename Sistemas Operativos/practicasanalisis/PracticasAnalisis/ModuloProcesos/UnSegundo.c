/* UnSegundo.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Francisco Rosales
 * Versi�n: 1.0		Fecha: 20 Sep 2004
 * Descripci�n:
 *	Mide, de manera promediada, el n�mero de vueltas a un bucle
 *	b�sico que pueden realizarse en un segundo.
 * Uso:
 *	UnSegundo_B
 */
#define MYNAME	"UnSegundo_B"

#include <signal.h>
#include <stdio.h>
#include <unistd.h>

volatile int cnt = 0;
int secs = 0;
long long vueltas = 0;	/* 64 bits */

void Handler(int signo)
{
	vueltas += cnt;
	secs++;
	printf(MYNAME": En %d segundos %Ld vueltas. Son %d vueltas/segundo.\n",
		secs, vueltas, (int)(vueltas/secs));
	signal(SIGALRM, Handler);
	alarm(1);
	cnt = 1;
}

int main(void)
{
	signal(SIGALRM, Handler);
	alarm(1);
	cnt = 1;
	while (cnt > 0)
		cnt++;

	return 0;
}
