% Write your Prolog code here, e.g.:

% hechos

controla(solozzo, drogas).
controla(corleone, apuestas).

apoya(roth,X) :- garantiza_impunidad(roth).

controla(X,apuestas) :- controla(X,policia).

garantiza_impunidad(X) :- controla(X,policia).


% reglas


controla(solozzo,bronx),controla(solozzo,harlem) :-
     apoya(roth,solozzo).
controla(corleone,manhattan),controla(corleone,brooklyn) :-
     apoya(roth,corleone).

elimina(corleone, solozzo) :-
    controla(corleone, manhattan),
    controla(corleone, brooklyn).

elimina(solozzo, corleone) :-
    controla(solozzo, drogas),
    apoya(roth, solozzo).







