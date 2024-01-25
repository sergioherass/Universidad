:- module(_,_,[classic,assertions,regtypes]).
:- use_module(library(lists)).

author_data('Heras', 'Alvarez', 'Sergio', 'C20M025').


board1([cell(pos(1 ,1),op(*,-3)),
        cell(pos(1 ,2),op(-,1)),
        cell(pos(1 ,3),op(-,4)),
        cell(pos(1 ,4),op(- ,555)),
        cell(pos(2 ,1),op(-,3)),
        cell(pos(2 ,2),op(+ ,2000)),
        cell(pos(2 ,3),op(* ,133)),
        cell(pos(2 ,4),op(- ,444)),
        cell(pos(3 ,1),op(*,0)),
        cell(pos(3 ,2),op(* ,155)),
        cell(pos(3 ,3),op(// ,2)),
        cell(pos(3 ,4),op(+ ,20)),
        cell(pos(4 ,1),op(-,2)),
        cell(pos(4 ,2),op(- ,1000)),
        cell(pos(4 ,3),op(-,9)),
        cell(pos(4 ,4),op(*,4))]).


% Direcciones permitidas
direccion(n).
direccion(s).
direccion(e).
direccion(o).
direccion(no).
direccion(ne).
direccion(so).
direccion(se).

% Predicado para obtener la posición resultante de moverse en una dirección desde una posición dada
efectuar_movimiento(pos(Row, Col), Dir, pos(Row2, Col2)) :-
    direccion(Dir),
    mover_posicion(Dir, Row, Col, Row2, Col2).

% Definición de las reglas de movimiento para cada dirección
mover_posicion(n, Row, Col, Row2, Col) :- Row2 is Row - 1.
mover_posicion(s, Row, Col, Row2, Col) :- Row2 is Row + 1.
mover_posicion(e, Row, Col, Row, Col2) :- Col2 is Col + 1.
mover_posicion(o, Row, Col, Row, Col2) :- Col2 is Col - 1.
mover_posicion(no, Row, Col, Row2, Col2) :- Row2 is Row - 1, Col2 is Col - 1.
mover_posicion(ne, Row, Col, Row2, Col2) :- Row2 is Row - 1, Col2 is Col + 1.
mover_posicion(so, Row, Col, Row2, Col2) :- Row2 is Row + 1, Col2 is Col - 1.
mover_posicion(se, Row, Col, Row2, Col2) :- Row2 is Row + 1, Col2 is Col + 1.

movimiento_valido(N, pos(Row,Col), Dir) :-
    efectuar_movimiento(pos(Row,Col), Dir, pos(Row2,Col2)),
    Row2 >= 1, Row2 =< N,
    Col2 >= 1, Col2 =< N.

select_cell(IPos, Op, Board, NewBoard) :-
    select(cell(IPos, Op), Board, NewBoard).
select_cell2(IPos, Op, Board) :-
    select(cell(IPos, Op), Board,NewBoard).


% Caso base, donde dir(Dir, 1) es la cabeza de la lista. 
% En este caso, simplemente retornamos la cola de la lista
select_dir(Dir, [dir(Dir, 1) | RestoDirs], RestoDirs).

% Caso donde dir(Dir, Num) es la cabeza de la lista, y Num > 1.
% En este caso, restamos uno de Num y agregamos de nuevo a la lista
select_dir(Dir, [dir(Dir, Num) | RestoDirs], [dir(Dir, NewNum) | RestoDirs]) :-
    Num > 1,
    NewNum is Num - 1.

% Caso donde la cabeza de la lista es diferente de dir(Dir, _).
% En este caso, dejamos la cabeza de la lista intacta y operamos sobre el resto de la lista
select_dir(Dir, [dir(Dir2, Num) | RestoDirs], [dir(Dir2, Num) | NewDirs]) :-
    Dir \= Dir2,
    select_dir(Dir, RestoDirs, NewDirs).



aplicar_op(op(+, Operando), Valor, Valor2) :-
    Valor2 is Valor + Operando.
aplicar_op(op(-, Operando), Valor, Valor2) :-
    Valor2 is Valor - Operando.
aplicar_op(op(*, Operando), Valor, Valor2) :-
    Valor2 is Valor * Operando.
aplicar_op(op(//, Operando), Valor, Valor2) :-
    Valor2 is Valor // Operando.

generar_recorrido(Ipos, N, Board, DireccionesPermitidas, RecorridoFinal, ValorFinal) :-
    select_cell(Ipos, op(Operador,Ivalor), Board,NewBoard),
    aplicar_op(op(Operador,Ivalor),0,Valor2),
    generar_recorrido_aux(Ipos, N, NewBoard, DireccionesPermitidas,Valor2, [(Ipos, Valor2)], RecorridoFinal, ValorFinal,[Ipos]).

generar_recorrido_aux(_, _, _, [],Ivalor, Recorrido, Recorrido,Ivalor,_).
generar_recorrido_aux(_, _, [], _,Ivalor, Recorrido, Recorrido,Ivalor,_).
generar_recorrido_aux(_, N, _, _,Ivalor, Recorrido, Recorrido, Ivalor,Visited):-
    length(Visited,N*N).
generar_recorrido_aux(Ipos, N, Board, Dirs,Ivalor,RecorridoParcial, RecorridoFinal, ValorFinal,Visited) :-
 
    movimiento_valido(N,Ipos,Dir),
    select_dir(Dir, Dirs,NewDirs),
    efectuar_movimiento(Ipos, Dir, NextPos),
    \+ member(NextPos,Visited),
    select_cell(NextPos, Op, Board, NewBoard),
    aplicar_op(Op, Ivalor , ValorParcial),
    append(RecorridoParcial, [(NextPos, ValorParcial)], RecorridoParcial2),
 
    generar_recorrido_aux(NextPos, N, NewBoard,NewDirs,ValorParcial, RecorridoParcial2, RecorridoFinal, ValorFinal,[NextPos|Visited]).
generar_recorrido_aux(Ipos, N, Board, [dir(_, 0) | RestoDirs],Ivalor, RecorridoParcial, Recorrido, Valor,Visited) :-
    generar_recorrido_aux(Ipos, N, Board, RestoDirs,Ivalor, RecorridoParcial, Recorrido, Valor,Visited).

%generar_recorridos(N, Board, DireccionesPermitidas, Recorrido, Valor).
%tablero(N, Tablero , DireccionesPermitidas , ValorMinimo , NumeroDeRutasConValorMinimo ).

generar_todos_recorridos(N, Board, Dirs, Recorridos) :-
    findall((Recorrido, Valor),
            (member(cell(Pos, _), Board),
             generar_recorrido(Pos, N, Board, Dirs, Recorrido, Valor)),
            Recorridos).
generar_recorridos(N, Board, Dirs, Recorrido, Valor) :-
    generar_todos_recorridos(N, Board, Dirs, Recorridos),
    member((Recorrido, Valor), Recorridos).

% Predicado para encontrar el mínimo de una lista
min_list([X], X).
min_list([H|T], Min) :-
    min_list(T, MinRest),
    H =< MinRest,
    Min = H.
min_list([H|T], Min) :-
    min_list(T, MinRest),
    H > MinRest,
    Min = MinRest.

% Predicado para contar el número de veces que aparece un valor en una lista de tuplas
count_valores_minimos([], _, 0).
count_valores_minimos([(_, Valor)|T], ValorMinimo, Numero) :-
    Valor =:= ValorMinimo,
    count_valores_minimos(T, ValorMinimo, NumeroResto),
    Numero is NumeroResto + 1.
count_valores_minimos([(_, Valor)|T], ValorMinimo, Numero) :-
    Valor =\= ValorMinimo,
    count_valores_minimos(T, ValorMinimo, NumeroResto),
    Numero is NumeroResto.

% Predicado principal
tablero(N, Board, Dirs, ValorMinimo, NumeroDeRutasConValorMinimo) :-
    generar_recorridos(N, Board, Dirs, Recorridos,ValorFinal),
    findall(Valor, member((_, Valor), Recorridos), Valores),
    min_list(Valores, ValorMinimo),
    count_valores_minimos(Recorridos, ValorMinimo, NumeroDeRutasConValorMinimo).