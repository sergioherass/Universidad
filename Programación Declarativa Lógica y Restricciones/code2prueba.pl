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


% Efectuar movimiento desde Pos en la direccion Dir.
efectuar_movimiento(pos(Row,Col), n, pos(Row2,Col)) :- Row2 is Row - 1.
efectuar_movimiento(pos(Row,Col), s, pos(Row2,Col)) :- Row2 is Row + 1.
efectuar_movimiento(pos(Row,Col), e, pos(Row,Col2)) :- Col2 is Col + 1.
efectuar_movimiento(pos(Row,Col), o, pos(Row,Col2)) :- Col2 is Col - 1.
efectuar_movimiento(pos(Row,Col), ne, pos(Row2,Col2)) :- Row2 is Row - 1, Col2 is Col + 1.
efectuar_movimiento(pos(Row,Col), no, pos(Row2,Col2)) :- Row2 is Row - 1, Col2 is Col - 1.
efectuar_movimiento(pos(Row,Col), se, pos(Row2,Col2)) :- Row2 is Row + 1, Col2 is Col + 1.
efectuar_movimiento(pos(Row,Col), so, pos(Row2,Col2)) :- Row2 is Row + 1, Col2 is Col - 1.

% Verifica si el movimiento es valido en un tablero NxN.
movimiento_valido(N, pos(Row,Col), Dir) :-
    efectuar_movimiento(pos(Row,Col), Dir, pos(Row2,Col2)),
    between(1, N, Row2),
    between(1, N, Col2).

% Extrae la celda de la posicion IPos del tablero.
select_cell(IPos, Op, [cell(IPos, Op)|Rest], Rest).
select_cell(IPos, Op, [cell(Pos2, Op2)|Rest], [cell(Pos2, Op2)|NewRest]) :-
    IPos \= Pos2,
    select_cell(IPos, Op, Rest, NewRest).

% Extrae una direccion de la lista de direcciones permitidas.
select_dir(Dir, [dir(Dir, Num)|Rest], NewDirs) :-
    ( Num > 1 -> NewDirs = [dir(Dir, Num2)|Rest], Num2 is Num - 1
    ; Num = 1, NewDirs = Rest
    ).
select_dir(Dir, [dir(Dir2, Num2)|Rest], [dir(Dir2, Num2)|NewRest]) :-
    Dir \= Dir2,
    select_dir(Dir, Rest, NewRest).

% Aplica la operacion indicada en Valor para obtener Valor2.
aplicar_op(op(Operador,Operando), Valor, Valor2) :-
    Operacion =.. [Operador, Valor, Operando],
    Valor2 is Operacion.

% Genera todos los recorridos posibles desde la casilla IPos.
generar_recorrido(Ipos, N, Board, DireccionesPermitidas, Recorrido, Valor) :-
    generar_recorrido_aux(Ipos, N, Board, DireccionesPermitidas, [(Ipos, 0)], Recorrido, Valor).

generar_recorrido_aux(_, _, [], _, Recorrido, Recorrido, Valor) :- last(Recorrido, (_, Valor)).
generar_recorrido_aux(Ipos, N, Board, DireccionesPermitidas, Recorrido, FinalRecorrido, ValorFinal) :-
    select_cell(Ipos, Op, Board, NewBoard),
    select_dir(Dir, DireccionesPermitidas, NewDirs),
    movimiento_valido(N, Ipos, Dir),
    efectuar_movimiento(Ipos, Dir, Ipos2),
    last(Recorrido, (_, Valor)),
    aplicar_op(Op, Valor, Valor2),
    append(Recorrido, [(Ipos2, Valor2)], NewRecorrido),
    generar_recorrido_aux(Ipos2, N, NewBoard, NewDirs, NewRecorrido, FinalRecorrido, ValorFinal).