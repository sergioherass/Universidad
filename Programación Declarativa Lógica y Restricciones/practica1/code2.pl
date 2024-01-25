1)
:- module(_,_,[assertions,regtypes]).
author_data('Heras', 'Alvarez', 'Sergio', 'C20M025').

color(o).
color(x).

rule(o,o,o,_,o). % regla nula
rule(x,o,o,r(A,_,_,_,_,_,_),A) :- color(A).
rule(o,x,o,r(_,B,_,_,_,_,_),B) :- color(B).
rule(o,o,x,r(_,_,C,_,_,_,_),C) :- color(C).
rule(x,o,x,r(_,_,_,D,_,_,_),D) :- color(D).
rule(x,x,o,r(_,_,_,_,E,_,_),E) :- color(E).
rule(o,x,x,r(_,_,_,_,_,F,_),F) :- color(F).
rule(x,x,x,r(_,_,_,_,_,_,G),G) :- color(G).

evolve([A, B, C | Rest], Rules, [NewColor | NewState]) :-
    rule(A, B, C, Rules, NewColor),
    evolve([B, C | Rest], Rules, NewState).
evolve([A, B], Rules, [NewColor]) :-
    rule(A, B, o, Rules, NewColor).

evolve_cinta(Cinta, Rules, NewCinta) :-
    append([o, o | Cinta], [o, o], ExtendedCinta),
    evolve(ExtendedCinta, Rules, NewCinta).

?- Rules = r(x,o,x,x,x,x,o), Cinta = [o, x, x, x, o, o, o, x, o], evolve_cinta(Cinta, Rules, NewCinta).

2)
color(o).
color(x).

rule(o,o,o,_,o).
rule(x,o,o,r(A,_,_,_,_,_,_),A) :- color(A).
rule(o,x,o,r(_,B,_,_,_,_,_),B) :- color(B).
rule(o,o,x,r(_,_,C,_,_,_,_),C) :- color(C).
rule(x,o,x,r(_,_,_,D,_,_,_),D) :- color(D).
rule(x,x,o,r(_,_,_,_,E,_,_),E) :- color(E).
rule(o,x,x,r(_,_,_,_,_,F,_),F) :- color(F).
rule(x,x,x,r(_,_,_,_,_,_,G),G) :- color(G).

evolve([A, B, C | Rest], Rules, [NewColor | NewState]) :-
    rule(A, B, C, Rules, NewColor),
    evolve([B, C | Rest], Rules, NewState).
evolve([A, B], Rules, [NewColor]) :-
    rule(A, B, o, Rules, NewColor).

valid_state([o | Rest]) :-
    append(_, [o], Rest).
    
cells(State, Rules, NewState) :-
    valid_state(State),
    append([o, o | State], [o, o], ExtendedState),
    evolve(ExtendedState, Rules, NewState),
    valid_state(NewState),
    length(State, N),
    length(NewState, N2),
    N2 is N + 2.

opcion casi buena

evolve([A, B, C | Rest], Rules, [NewColor | NewState]) :-
    rule(A, B, C, Rules, NewColor),
    evolve([B, C | Rest], Rules, NewState).
evolve([A, B], Rules, [NewColor]) :-
    rule(A, B, o, Rules, NewColor).

valid_state([o | Rest]) :-
    append(_, [o], Rest).
    
cells(State, Rules, NewState) :-
    valid_state(State),
    append([o, o | State], [o], ExtendedState),
    evolve(ExtendedState, Rules, NewState).


?- cells([o, x, o], r(x, x, x, o, o, x, o), Cells).

otra opcion:

next_cell(A, B, C, RuleSet, NewCell) :-
    rule(A, B, C, RuleSet, NewCell).

evolve([A, B, C | Rest], RuleSet, [NewCell | EvolvedRest]) :-
    next_cell(A, B, C, RuleSet, NewCell),
    evolve([B, C | Rest], RuleSet, EvolvedRest).

evolve([A, B], RuleSet, [NewCell, B, o]) :-
    next_cell(A, B, o, RuleSet, NewCell).

valid_state([o | Rest]) :-
    append(_, [o], Rest).

cells(InitialState, RuleSet, FinalState) :-
    valid_state(InitialState),
    evolve(InitialState, RuleSet, FinalState).


3)

color(o).
color(x).

rule(o,o,o,_,o).
rule(x,o,o,r(A,_,_,_,_,_,_),A) :- color(A).
rule(o,x,o,r(_,B,_,_,_,_,_),B) :- color(B).
rule(o,o,x,r(_,_,C,_,_,_,_),C) :- color(C).
rule(x,o,x,r(_,_,_,D,_,_,_),D) :- color(D).
rule(x,x,o,r(_,_,_,_,E,_,_),E) :- color(E).
rule(o,x,x,r(_,_,_,_,_,F,_),F) :- color(F).
rule(x,x,x,r(_,_,_,_,_,_,G),G) :- color(G).

evolve([A, B, C | Rest], Rules, [NewColor | NewState]) :-
    rule(A, B, C, Rules, NewColor),
    evolve([B, C | Rest], Rules, NewState).
evolve([A, B], Rules, [NewColor]) :-
    rule(A, B, o, Rules, NewColor).

valid_state([o | Rest]) :-
    append(_, [o], Rest).
    
cells(State, Rules, NewState) :-
    valid_state(State),
    append([o, o | State], [o, o], ExtendedState),
    evolve(ExtendedState, Rules, NewState),
    valid_state(NewState),
    length(State, N),
    length(NewState, N2),
    N2 is N + 2.

evol(0, _, [o, x, o]).
evol(N, RuleSet, Cells) :-
    N = s(M),
    evol(M, RuleSet, PrevCells),
    cells(PrevCells, RuleSet, Cells).

evol(N, r(x, x, x, o, o, x, o), Cells).

4)


color(o).
color(x).

rule(o,o,o,_,o).
rule(x,o,o,r(A,_,_,_,_,_,_),A) :- color(A).
rule(o,x,o,r(_,B,_,_,_,_,_),B) :- color(B).
rule(o,o,x,r(_,_,C,_,_,_,_),C) :- color(C).
rule(x,o,x,r(_,_,_,D,_,_,_),D) :- color(D).
rule(x,x,o,r(_,_,_,_,E,_,_),E) :- color(E).
rule(o,x,x,r(_,_,_,_,_,F,_),F) :- color(F).
rule(x,x,x,r(_,_,_,_,_,_,G),G) :- color(G).

evolve([A, B, C | Rest], Rules, [NewColor | NewState]) :-
    rule(A, B, C, Rules, NewColor),
    evolve([B, C | Rest], Rules, NewState).
evolve([A, B], Rules, [NewColor]) :-
    rule(A, B, o, Rules, NewColor).

valid_state([o | Rest]) :-
    append(_, [o], Rest).
    
cells(State, Rules, NewState) :-
    valid_state(State),
    append([o, o | State], [o, o], ExtendedState),
    evolve(ExtendedState, Rules, NewState),
    valid_state(NewState),
    length(State, N),
    length(NewState, N2),
    N2 is N + 2.

evol(0, _, [o, x, o]).
evol(N, RuleSet, Cells) :-
    N = s(M),
    evol(M, RuleSet, PrevCells),
    cells(PrevCells, RuleSet, Cells).

steps(Cells, N) :-
    valid_state(Cells),
    length(Cells, Len),
    N is (Len - 3) // 2.

ruleset(RuleSet, Cells) :-
    steps(Cells, N),
    evol(N, RuleSet, Cells).


