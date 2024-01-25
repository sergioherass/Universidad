% Definición de variables de decisión
var 7..12: Inf;

% Restricciones
constraint Inf >= 7 /\ Inf >= 5 /\ Inf >= 2 /\ Inf >= 1;
constraint Inf <= 12;
constraint Inf != 7 /\ Inf != 5 /\ Inf != 2 /\ Inf != 1;


% Solución
solve satisfy;

% Impresión de la solución
output ["La puntuación del equipo de Informaticos es: \(Inf)\n"];
