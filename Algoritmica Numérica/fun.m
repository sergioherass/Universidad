%ap1)
clc,clear
N=15;
xk=linspace(-1,1,N)
yk=fun1(xk)
xx=[-1:0.01:1];
ff=fun1(xx);
pp=newton(xk,yk,xx); 

Eabs= abs(ff-pp);
emax=max(Eabs);
fprintf('\nError máximo=%.2e',emax);


function f=fun1(xx)
 f= atan(pi.*xx), 
return
end
function out=newton(x,y,xx)
% Calcula diferencias divididas a partir de x e y 
% Entrada: x, N abcisas de interpolacion
%          f, N ordenadas de interpolacion
%          xx, opcional. Si se da, evalua polinomio en esos puntos
% Salida:  out, N diferencias divididas
 
N=length(y); y=reshape(y,N,1); 

% Calculo diferencias divididas  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
DD = zeros(N,N);      % Reservo matriz de Diferencias Divididas 
DD(:,1) = y;    % 1era columna = valores de y

for k=2:N, %  Barremos columnas de DD (diferencias ordenadas orden k)         
  for j=k:N, % En cada columna k barremos de la diagonal(k) hacia abajo  
    dif =  (DD(j,k-1)-DD(j-1,k-1));  % resta de dif divididas orden k-1
    dx =  x(j)-x(j-k+1);           % resta de abscisas
    DD(j,k) =  dif/ dx;              % Diferencia DIVIDIDA
  end
end
D = diag(DD)';  % coeficientes = diagonal del cuadro de diferencias div
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

if nargin==2,  % Solo dos argumentos de entrada, devolver dif div
 out=D; return;
end

% Si llegamos aqui hay un tercer arg -> evaluar polinomio en xx
 pp = D(N);  % Regla de Horner con diferencias divididas
 for k=N-1:-1:1,
   pp = D(k) + pp.*(xx-x(k));  
 end
 out=pp; % devuelve valores obtenidos polinomio en out            
  
end
