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


select_dir(Dir, Dirs , NewDirs) :-
    select(dir(Dir,Num), Dirs , TempDirs),
    Num > 1,
    NewNum is Num - 1,
    NewDirs = [dir(Dir, NewNum) | TempDirs].


select_dir(Dir, Dirs, NewDirs) :-
    select(dir(Dir, 1), Dirs, NewDirs).

select_dir(Dir, Dirs, Dirs) :-
    member(dir(Dir, _), Dirs).

aplicar_op(op(+, Operando), Valor, Valor2) :-
    Valor2 is Valor + Operando.
aplicar_op(op(-, Operando), Valor, Valor2) :-
    Valor2 is Valor - Operando.
aplicar_op(op(*, Operando), Valor, Valor2) :-
    Valor2 is Valor * Operando.
aplicar_op(op(//, Operando), Valor, Valor2) :-
    Valor2 is Valor // Operando.


generar_recorrido(Ipos, N, Board, DireccionesPermitidas, Recorrido, Valor) :-
    select_cell(Ipos,op(Op,IValor),Board,NewBoard),
    aplicar_op(op(Op,IValor),0,Valor2),
    generar_recorrido_aux(N, Ipos, Board, DireccionesPermitidas,[(Ipos,Valor2)],Valor2, Recorrido, ValorFinal,[Ipos]).

generar_recorrido_aux(N,_,_,_,_,_,_,_,Visited):-
    length(Visited,N*N).
generar_recorrido_aux(_, _, [], _,Recorrido,Valor, Recorrido, Valor,_).
generar_recorrido_aux(_, _, _, [],Recorrido,Valor, Recorrido, Valor,_).
generar_recorrido_aux(N, Ipos, Board, Dirs,Recorrido,ValorActual,FinalRecorrido,FinalValor,Visited) :-
    movimiento_valido(N,Ipos,Dir),
    select_dir(Dir,Dirs, NewDirs),
    efectuar_movimiento(Ipos, Dir, NewPos),
    \+ member(NewPos,Visited),
    select_cell(NewPos, op(Op,Operando), Board, NewBoard),
    aplicar_op(op(Op,Operando),ValorActual, NewValor),
    generar_recorrido_aux(N, NewPos, NewBoard, NewDirs,[(NewPos,NewValor) | Recorrido],NewValor, FinalRecorrido,FinalValor,[NewPos | Visited]).



generar_recorridos(N, Board, DireccionesPermitidas, Recorrido, Valor).
tablero(N, Tablero , DireccionesPermitidas , ValorMinimo , NumeroDeRutasConValorMinimo ).