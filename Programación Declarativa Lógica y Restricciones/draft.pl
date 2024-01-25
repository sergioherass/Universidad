% Write your Prolog code here, e.g.:

% hechos
controla(corleone, manhattan).
controla(corleone, brooklyn).
controla(solozzo, drogas).
controla(corleone, apuestas).
controla(solozzo, bronx).
controla(solozzo, harlem).
apoya(roth, solozzo).
apoya(roth, corleone).
garantiza_impunidad(X) :- apoya(roth, X).
control_pol(mafioso) :- controla(corleone, apuestas).
garantiza_impunidad(X) :- control_pol(X).

% reglas
elimina(A, B) :-
    controla(A, manhattan),
    controla(A, brooklyn),
    B = solozzo.

elimina(solozzo, corleone) :-
    controla(solozzo, drogas),
    apoya(roth, solozzo).

elimina(corleone, solozzo) :-
    apoya(roth, corleone),
    controla(corleone, manhattan),
    controla(corleone, brooklyn).

elimina(A, B) :-
    apoya(roth, A),
    garantiza_impunidad(B).


