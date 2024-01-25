#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "movil.h"


/*
 * Crea dato movil_t y copia los argumentos en la nueva estructura
 * Si no hay memoria devuelve NULL
 * En caso contrario, devuelve la dirección del móvil creado
 */
movil_t *new_movil(const char *imei, const char *modelo, const char *ram, int bat, double pvp, int sspec) {

int i;
 movil_t *aux;
for (i=0;i<17;i++){
  aux->imei[i]=*(imei+i);
}
for (i=0;i<17;i++){
  aux->modelo[i]=*(modelo+i);
}
for(i=0;i<17;i++){
  aux->ram[i]=*(ram+i);
}
 aux->bat=bat;
 aux->pvp=pvp;
 aux->sspec=sspec;
 return aux;





}

/*
 * Elimina el móvil en la dirección pm
 * Libera toda la memoria ocupada el móvil
 */
void del_movil(movil_t *pm) {


free(pm);


}

/*
 * Esta función convierte el terminal en pmov a un string
 * Requiere como argumentos la dirección del terminal
 * y un array de cadenas de formateo del string resultante
 * https://www.cprogramming.com/tutorial/printf-format-strings.html
 * La función devuelve la dirección de un string acorde
 * con los códigos de formato en el segundo argumento
 * Para hacer la conversión invoca la llamada
 * sprintf() sobre el buffer en la dirección mstr
 * Si la cadena de formateo es NULL el campo se ignora
 * Al terminar la conversión invoca realloc() para reasignar
 * mstr a fin de que ocupe la memoria estrictamente necesaria
 * Devuelve NULL si no hay memoria
 * En caso contrario, devuelve la dirección del string
 */
char *toString(movil_t *pmov, const char *sfmt[], char *mstr)
{





}
