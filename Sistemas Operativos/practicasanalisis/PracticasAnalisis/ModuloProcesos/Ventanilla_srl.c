/* Ventanilla_srl.c */
/*
 * Copyright 2005 datsi.fi.upm.es.
 * Universidad Polit�cnica de Madrid, Facultad de Inform�tica
 *
 * Autor: Francisco Rosales
 * Versi�n: 1.0		Fecha: 28 Nov 2005
 * Descripci�n:
 * 	Este programa modela un servidor muy sencillo.
 * 	Para cada caracter introducido, se invoca a una funci�n de servicio
 * 	que hace eco del caracter tras cierta cantidad de tiempo
 * 	dependiente de dicho caracter y con unos atributos dependientes del
 * 	n�mero total de caracteres introducidos hasta el momento.
 *
 *	Esta versi�n de servidor usa un �nico proceso para todo.
 *	No existir� concurrencia pero si compartici�n.
 *	Los caracteres se procesan uno a uno secuencialmente.
 *	La variable est�tica cnt se comparte, luego los atributos de
 *	presentaci�n van cambiando.
 * Uso:
 * 	Ventanilla_srl
 */
#define MYNAME	"Ventanilla_srl"

#include <stdio.h>
#include <unistd.h>

void ServiceFunction(unsigned char ch)
{
	unsigned char buff[] = "[__;4m_[0m";
	static unsigned cnt;
	/* La duraci�n del servicio depende del par�metro */
	usleep((256-ch)*20000);
	/* Los atributos dependen del n�mero de orden */
	buff[3] = '0'+ (cnt%8);
	buff[2] = "349"[cnt/8%3];
	cnt++;
	/* Finalmente muestra un resultado */
	buff[7] = ch;
	write(1,buff,12);
}




int main(void)
{
	unsigned char ch;

	while(read(0,&ch,1) == 1)
	{
		ServiceFunction(ch);
	}
	write(1,"\n",1);
	return 0;
}
