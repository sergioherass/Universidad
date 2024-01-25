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

my_append([], List, List).
my_append([Head | Tail1], List, [Head | Tail2]) :-
    my_append(Tail1, List, Tail2).


evolve([A, B, C | Rest], Rules, [NewColor | NewState]) :-
    rule(A, B, C, Rules, NewColor),
    evolve([B, C | Rest], Rules, NewState).
evolve([A, B], Rules, [NewColor]) :-
    rule(A, B, o, Rules, NewColor).

valid_state([o | Rest]) :-
    my_append(_, [o], Rest).
    
cells(State, Rules, NewState) :-
    valid_state(State),
    my_append([o, o | State], [o], ExtendedState),
    evolve(ExtendedState, Rules, NewState).

evol(0, _, [o, x, o]).
evol(s(N), RuleSet, Cells) :-
    evol(N, RuleSet, PrevCells),
    cells(PrevCells, RuleSet, Cells).

steps([o,x,o],0).
steps(Cells,s(N)):-
    evol(N,_,PrevCells),
    cells(PrevCells,_,Cells).

ruleset(_, [o, x, o]).
ruleset(RuleSet, Cells) :-
    steps(Cells,s(N)),
    evol(N, RuleSet, PrevCells),
    cells(PrevCells, RuleSet, Cells).
