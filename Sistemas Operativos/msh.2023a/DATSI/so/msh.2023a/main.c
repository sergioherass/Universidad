/*-
 * main.c
 * Minishell C source
 * Shows how to use "obtain_order" input interface function.
 *
 * Copyright (c) 1993-2002-2019, Francisco Rosales <frosal@fi.upm.es>
 * Todos los derechos reservados.
 *
 * Publicado bajo Licencia de Proyecto Educativo Práctico
 * <http://laurel.datsi.fi.upm.es/~ssoo/LICENCIA/LPEP>
 *
 * Queda prohibida la difusión total o parcial por cualquier
 * medio del material entregado al alumno para la realización
 * de este proyecto o de cualquier material derivado de este,
 * incluyendo la solución particular que desarrolle el alumno.
 *
 * DO NOT MODIFY ANYTHING OVER THIS LINE
 * THIS FILE IS TO BE MODIFIED
 */
#include <stddef.h>                     /* NULL */
#include <stdio.h>                      /* setbuf, printf */
#include <stdlib.h>
#include <unistd.h>
#include <limits.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/times.h>
#include <sys/wait.h>
#include <time.h>
#include <fcntl.h>

extern int obtain_order();              /* See parser.y for description */

int main(void)
{
        char ***argvv = NULL;
        int argvc;
        char **argv = NULL;
        //int argc;
        char *filev[3] = { NULL, NULL, NULL };
        int bg;
        int ret;
        //int fd;
        int tub[2];
        int salida;

        setbuf(stdout, NULL);                   /* Unbuffered */
        setbuf(stdin, NULL);

        while (1) {
                fprintf(stderr, "%s", "msh> "); /* Prompt */
                ret = obtain_order(&argvv, filev, &bg);
                if (ret == 0) break;            /* EOF */
                if (ret == -1) continue;        /* Syntax error */
                argvc = ret - 1;                /* Line */
                if (argvc == 0) continue;       /* Empty line */
#if 1
/*
 * LAS LINEAS QUE A CONTINUACION SE PRESENTAN SON SOLO
 * PARA DAR UNA IDEA DE COMO UTILIZAR LAS ESTRUCTURAS
 * argvv Y filev. ESTAS LINEAS DEBERAN SER ELIMINADAS.
 */

                for (argvc = 0; (argv = argvv[argvc]); argvc++) {
                        //for (argc = 0; argv[argc]; argc++)

                         if (argvv[argvc+1]!=NULL){
                                if(pipe(tub)<0){
                                        perror("error al crear tuberia");
                                        exit(1);
                                }
                         }



                         if(strcmp(argvv[0][0],"cd")==0){
                         char *new_dir=argvv[0][1];
                                char actual_dir[PATH_MAX];
                                if(new_dir==NULL){
                                        new_dir=getenv("HOME");
                                        if(new_dir==NULL){
                                                fprintf(stderr,"Error:No se pudo obtener el directorio HOME.\n");
                                                continue;
                                        }
                                }

                                if(chdir(new_dir)!=0){
                                        perror("Error al cambiar de directorio");
                                        break;
                                }
                                if(getcwd(actual_dir,sizeof(actual_dir))==NULL){
                                        perror("Error al obtener el directorio actual");
                                }else{
                                        printf("%s\n",actual_dir);
                                        break;
                                }
                         } if(strcmp(argvv[0][0],"umask")==0){
                   mode_t old_mask=umask(0);
                   umask(old_mask);
                                   if (argvv[0][1]==NULL){
                                        printf("%o\n",old_mask);
                                        }
                   if(argvv[0][1]!=NULL){
                      char *endptr;
                      long new_mask=strtol(argv[1],&endptr,8);
                      if(*endptr=='\0'){
                         umask((mode_t)new_mask);
                      }else{
                        fprintf(stderr,"umask:valor octal invalido: %s\n",argv[1]);
                      }
                   }
                                   if(argvv[0][2]!=NULL){
                        fprintf(stderr,"umask: mayor numero de argumentos\n");
                   }
                   //printf("%o\n",old_mask);
                   continue;
                }
                                /*if (strcmp(argv[0], "time") == 0) {
                        if (argc == 1) {
                            struct tms tmsbuf;
                            clock_t start, end;

                            start = times(&tmsbuf);
                            end = times(&tmsbuf);

                            long ticks_per_second = sysconf(_SC_CLK_TCK);
                            double user_time = (double)tmsbuf.tms_utime / ticks_per_second;
                            double system_time = (double)tmsbuf.tms_stime / ticks_per_second;
                            double real_time = (double)(end - start) / ticks_per_second;

                            printf("%d.%03du %d.%03ds %d.%03dr\n",
                                   (int)user_time, (int)(user_time * 1000) % 1000,
                                   (int)system_time, (int)(system_time * 1000) % 1000,
                                   (int)real_time, (int)(real_time * 1000) % 1000);

                        } else {
                  pid_t pid;
                          struct tms tmsbuf;
                  clock_t start, end;

                  start = times(&tmsbuf);
                  pid = fork();
                  if (pid == 0) {
                        if (execvp(argv[1], &argv[1]) == -1) {
                                perror("Error: No se pudo ejecutar el comando");
                                exit(1);
                        }
                          } else if (pid > 0) {
                    int status;
                        waitpid(pid, &status, 0);
                        end = times(&tmsbuf);

                        long ticks_per_second = sysconf(_SC_CLK_TCK);
                        double user_time = (double)tmsbuf.tms_cutime / ticks_per_second;
                        double system_time = (double)tmsbuf.tms_cstime / ticks_per_second;
                        double real_time = (double)(end - start) / ticks_per_second;

                        printf("%d.%03du %d.%03ds %d.%03dr\n",
                           (int)user_time, (int)(user_time * 1000) % 1000,
                           (int)system_time, (int)(system_time * 1000) % 1000,
                           (int)real_time, (int)(real_time * 1000) % 1000);
                      } else {
                        perror("Error: No se pudo crear el proceso hijo");
                      }
                        }
                         }*/ if((strcmp(argv[0], "cd") != 0) && (strcmp(argv[0], "umask") != 0) && (strcmp(argv[0], "time") != 0) && (strcmp(argv[0], "read") != 0)){

                                pid_t pid=fork();
                                if (pid<0){
                                        perror("fork");
                                        exit (1);
                                }
                                else if (pid == 0){
                                        if (filev[0]) {
                                                int fdin=open(filev[0],O_RDONLY);
                                                if(fdin==-1){
                                                        perror("error al abrir el archivo de entrada");
                                                        exit(1);
                                                }
                                                dup2(fdin,0);
                                                close(fdin);
                                        }
                                        if (filev[1]) {
                                                int fdout=creat(filev[1],0666);
                                                if(fdout==-1){
                                                        perror("error al abrir el archivo de salida");
                                                        exit(1);
                                                }
                                                dup2(fdout,1);
                                                close(fdout);
                                        }
                                        if (filev[2]){
                                                int fderr=creat(filev[2],0666);
                                                if(fderr==-1){
                                                        perror("error al abrir el archivo de error");
                                                        exit(1);
                                                }
                                                dup2(fderr,2);
                                                close(fderr);
                                        }
                                        if (bg) printf("&\n");
                                        if(argvc>0){

                                                dup2(salida,0);
                                                close(salida);//si pongo salida igual
                                                //close(tub[1]);
                                        }

                                        if (argvv[argvc+1]!=NULL){
                                                //close(tub[0]);
                                                dup2(tub[1],1);
                                                close(tub[1]);
                                        }

                                        execvp(argv[0],argv);
                                        perror("exec");
                                        exit (1);

                                }else{
                                        if(argvc>0){
                                                close(salida);
                                        }
                                        if (argvv[argvc+1]!=NULL){
                                                salida=tub[0];
                                                close(tub[1]);
                                               // salida=tub[0];

                                        }
                                        if(!bg){
                                                int status;
                                                if (argvv[argvc+1]==NULL){
                                                   waitpid(pid,&status,0);
                                                }
                                        }
                                }

                          }


                }

/*
 * FIN DE LA PARTE A ELIMINAR
 */
#endif
        }
        exit(0);
        return 0;
}
