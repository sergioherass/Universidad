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