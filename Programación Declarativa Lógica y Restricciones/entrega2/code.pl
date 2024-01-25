:-module(_,_,[]).
author_data('Heras', 'Alvarez', 'Sergio', 'C20M025').


natural(0).
natural(s(X)) :-
natural(X).

% Ejer1
% a)
suma(0, Y, Y).
suma(s(X), Y, s(Z)) :-
    suma(X, Y, Z).

% b) 
par(0).
par(s(s(X))) :- par(X).


% c)
impar(s(0)).
impar(s(s(X))) :- impar(X).

%Ejer2
% a)
suma_a_lista([], _, []).
suma_a_lista([X|Xs], N, [Y|Ys]) :-
    natural(N), suma(X, N, Y),
    suma_a_lista(Xs, N, Ys).



% b)
pares_lista([], []).
pares_lista([X|Xs], [X|Ps]) :-
    par(X),
    pares_lista(Xs, Ps).
pares_lista([X|Xs], Ps) :-
    impar(X),
    pares_lista(Xs, Ps).

%Ejer3

extrae_elemento(0, [E|NL], E, NL).
extrae_elemento(s(I), [X|L], E, [X|NL]) :- extrae_elemento(I, L, E, NL).


