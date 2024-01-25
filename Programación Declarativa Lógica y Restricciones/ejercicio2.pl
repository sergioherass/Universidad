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