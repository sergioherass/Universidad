
:-module(_,_,[]).
author_data('Heras', 'Alvarez', 'Sergio', 'C20M025').

%ejercicio1

% Hechos
controla(corleone, manhattan).
controla(corleone, brooklyn).

controla(solozzo, drogas).
controla(corleone, apuestas).

controla(solozzo,bronx):-
     apoya(roth,solozzo).
controla(solozzo,harlem):-
     apoya(roth,solozzo).

controla(corleone,manhattan):-
     apoya(roth,corleone).
controla(corleone,brooklyn):-
     apoya(roth,corleone).

apoya(roth,corleone).
apoya(roth,X) :-garantiza_impunidad(X).

garantiza_impunidad(X) :- controla(X,policia).



% Reglas
elimina(corleone, solozzo) :- 
  controla(corleone, manhattan),
  controla(corleone, brooklyn).

elimina(solozzo, corleone) :-
  controla(solozzo, drogas),
  apoya(roth, solozzo).


%ejercicio2


hermano(homer,tio_fester).
hermano(homer,tio_cosa).
hermano(wednesday,pugsley).

hijo(homer,abuela_addams).
hijo(tio_fester,abuela_addams).
hijo(tio_cosa,abuela_addams).
hijo(pugsley,homer).
hijo(pugsley,morticia).
hijo(wednesday,homer).
hijo(wednesday,morticia).

abuela(abuela_addams,pugsley).
abuela(abuela_addams,wednesday).

tio(tio_fester,wednesday).
tio(tio_cosa,wednesday).
tio(tio_fester,pugsley).
tio(tio_cosa,pugsley).

sobrino(B,C):-
    tio(C,B).

padre(homer,wednesday).
padre(homer,pugsley).

cunyado(tio_cosa,morticia).
cunyado(tio_fester,morticia).