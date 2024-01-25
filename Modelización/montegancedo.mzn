% Declaración de variables

var int: v_inf; %las tres primeras variables hacen referencia a las incognitas que queremos averiguar
var int: e_inf;
var int : d_inf;
int : v_imd =2; %a partir de aqui ,son las variables que ya sabemos del enunciado que hemos podido deducir
int : e_imd=1;
int : d_imd=1;
int : v_cai=1;
int : e_cai=2;
int : d_cai=1;
int : v_bio=0;
int : e_bio=2;
int : d_bio=2;
int : v_uso=0;
int : e_uso=1;
int : d_uso=3;

% Restricciones
constraint v_inf>=0 /\ e_inf>=0 /\ d_inf>=0; %que sean mayores o iguales que 0
constraint 3*v_inf+e_inf<=12; %el maximo de puntos que pueden conseguir es 12 ,es decir, si ganan todos los partidos
constraint v_inf+e_inf+d_inf=4; %tienen que jugar 4 partidos en total
constraint 3*sum([v_inf, v_imd, v_cai, v_bio, v_uso])+sum([e_inf, e_imd, e_cai, e_bio, e_uso])<=30; %el maximo de puntos que puede haber jugando 10 partidos, con 5 equipos son 30. Luego el sumatorio de puntos debe ser menor a 30.


solve satisfy;

%el resultado hace referencia a que el equipo de informaticos hace 4 victorias , que son 12 puntos.