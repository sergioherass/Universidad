function [F,J]=fun(X)

x=X(1); 
y=X(2); % Extraemos componentes x,y del vector X

% Evaluacion de F (vector COLUMNA)

f1=sqrt((x+4)^2+(y+2)^2)-4;
f2=sqrt((x-3)^2+(y-1)^2)-6;
f3=sqrt((x+4)^2+(y-4)^2)-2.5;
F=[f1 f2 f3]';


if nargout==1, return; end

% Evaluar aproximación matriz J 
h=0.1;
hx=[h 0]';
hy=[0 h]';
Fx=(fun(X+hx)-fun(X))/h;
Fy=(fun(X+hy)-fun(X))/h;

J = [Fx Fy];
end