#include <stdio.h>

int main(){
  int num_filas1;
  int num_col1;
  int num_filas2;
  int num_col2;
  printf("Dime el numero de filas de la primera matriz:\n");
  scanf("%d",&num_filas1);
  printf("Dime el numero de columnas de la primera matriz:\n");
  scanf("%d",&num_col1);;

  int matrix1[num_filas1][num_col1];

  for(int i=0;i<num_filas1;i++){
      for (int j=0;j<num_col1;j++){
           printf("Ingresar dato en ( fila[%d]columnas[%d]):",i,j);
           scanf("%d",&matrix1[i][j]);
      }
  }


  printf("Dime el numero de filas de la segunda matriz:\n");
  scanf("%d",&num_filas2);
  printf("Dime el numero de columnas de la segunda matriz:\n");
  scanf("%d",&num_col2);


  if(num_col1==num_filas2){
   int matrix2[num_filas2][num_col2];

   for(int i=0;i<num_filas2;i++){
       for (int j=0;j<num_col2;j++){
            printf("Ingresar dato en ( fila[%d] columna[%d]):",i,j);
            scanf("%d",&matrix2[i][j]);
       }
   }
   int res[num_filas1][num_col2];
   for(int i=0;i<num_filas1;i++){
       for(int j=0;j<num_col2;j++){
           res[i][j]=0;
       }
   }
   for(int i=0;i<num_filas1;i++){
       for(int j=0;j<num_col2;j++){
           for(int z=0;z<num_col1;z++){
                res[i][j]=res[i][j]+matrix1[i][z]*matrix2[z][j];
               printf("%d\t",res[i][j]);
               }
//      printf("\n");
       }
 /*  for(int i=0;i<num_filas1;i++){
       for(int j=0;j<num_col2;j++){

           printf("%d\t",res[i][j]);
       }
      // printf("\n");
   }
 */
   }
  }

  return 0;
}
