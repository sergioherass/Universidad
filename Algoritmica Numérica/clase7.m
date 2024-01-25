% ECUACIONES NO LINEALES

% JULIO 14

s = sqrt(2); % raiz
x = 1;
iter = 0;
for n = 1:30
    iter = iter + 1;
    x = (2+x)/(1+x);
    eabs = abs(x-s);
    erel = eabs/abs(s);
    ncif = floor(-log10(erel));
    fprintf("Iteración: %2d x = %.15f Eabs = %.2e Ncifras = %.2d\n",iter,x,eabs,ncif);
end

% Necesitamos 19 iteraciones para alcanzar un total de 15 cifras
% significativas ed precisión correctas.

% El tipo de convergencia es lineal ya que el número de cifras
% significativas correctas se incrementa de uno en uno aproximadamente.
fprintf("\n\n");
x = 1;
iter = 0;
for n = 1:30
    iter = iter + 1;
    x = (x^2 + 2) / (2*x);
    eabs = abs(x-s);
    erel = eabs/abs(s);
    ncif = floor(-log10(erel));
    fprintf("Iteración: %2d x = %.15f Eabs = %.2e Ncifras = %2d\n",iter,x,eabs,ncif);
end

% En este caso necesitamos solo 5 iteraciones para alcanzar las 15 cifras
% significativas de precisión correctas.

% Al tener en el denominador del método iterativo la derivada del
% numerador, entonces estamos trabajando con el método de Newton que
% funciona muy bien para calcular la raíz de una función en pocas
% iteraciones

% En este caso la velocidad de convergencia es cuadrática ya que entre cada
% par de iteraciones se duplican aproximadamente el número de cifras
% significativas de precisión.

fprintf("\n\n");
s = sqrt(3);
x = 1;
iter = 0;
% f(x) = x^2 + 3 = 0
for n = 1:30
    iter = iter + 1;
    x = (x^2 + 3) / (2*x);
    eabs = abs(x-s);
    erel = eabs/abs(s);
    ncif = floor(-log10(erel));
    fprintf("Iteración: %2d x = %.15f Eabs = %.2e Ncifras = %2d\n",iter,x,eabs,ncif);
end

% En este caso necesitamos 5 iteraciones también para alcanzar la raíz de 3

% ----------------------------------------------------------------------- %

% SIMULACRO JULIO20
clc,clear
s = sqrt(2); % raiz
x = ones(1,21); % ya tengo x0 = 1
Erel = zeros(1,20); % apartado c)
iter = 0;
for n = 1:20
    iter = iter + 1;
    x(n+1) = (2+x(n))/(1+x(n));
    eabs = abs(x(n+1)-s);
    erel = eabs/abs(s);
    Erel(n) = erel; % apartado c)
    fprintf("Iteración: %2d x = %.15f Erel = %.2e\n",iter,x(n+1),erel);
end
% Difiere de la raíz 3.14e-16. Como se va aproximando más a la raíz en cada
% iteración, entonces podemos afirmar que el método converge a la raíz de 2

semilogy(1:20,Erel,'bo:')
% Necesitamos 19 iteraciones para alcanzar la precisión de la máquina. 
% Entre cada par de iteraciones se alcanza, aproximadamente, una nueva
% cifra significativa de precisión correcta.

% ----------------------------------------------------------------------- %

% DICIEMBRE18
clc,clear

x = -2.5;
for n = 1:5
    x_ant = x;
    x = x_ant-f(x_ant)/fp(x_ant);
    error = abs(x-x_ant);
    fprintf("Iter %d Raiz %.15f Error %.4e\n", n, x, error);
end

fprintf("\n\n");

x = 4;
for n = 1:5
    x_ant = x;
    x = x_ant-f(x_ant)/fp(x_ant);
    error = abs(x-x_ant);
    fprintf("Iter %d Raiz %.15f Error %.4e\n", n, x, error);
end

% Para ver visualmente las raíces
x = -2.5:0.01:4;
y = f(-2.5:0.01:4);
plot(x, y, 'b', x, y*0, 'k')
% Observamos que tal y como era de esperar hay una raíz doble cercana al
% punto -2.5, por lo que tarda mucho en aproximarse a ella; mientras que la
% raíz cercana a 4 es simple, por lo que su aproximación por el método de
% Newton es muy rápida.

% Luego en el primer caso la convergencia es lineal ya que necesito varias
% iteraciones para alcanzar una nueva cifra significativa de precisión
% correcta; mientras que en el segundo caso la convergencia es cuadrática,
% ya que entre cada par de iteraciones se duplican aproximadamente el
% número de cifras significativas de precisión correctas.

% ----------------------------------------------------------------------- %

% JULIO13
clc,clear

aprox_x = zeros(1,11);
aprox_x(1) = -2.5;
for n=1:10
    aprox_x(n+1) = aprox_x(n) - (aprox_x(n)^3-3*aprox_x(n)+2)/(3*aprox_x(n)^2-3);
end
s1 = aprox_x(end);

% Al tener una función en el numerador y su derivada en el denominador,
% este método es el de Newton. El método converge al valor -2

% Vemos gráficamente que el método de Newton converge hacia la raíz de la
% función igual a -2.
plot(1:10, aprox_x(2:11),'bo:')

% Estamos calculando una de las raíces de la función f(x) = x^3-3x+2 = 0

Erel = abs((aprox_x-s1)./s1);
semilogy(1:10, Erel(2:11), 'bo:')

% Necesitamos 5 iteraciones para alcanzar 15 cifras significativas de
% precisión correctas, alcanzando de esta forma el valor exacto de la raíz,
% ya que con 4 iteraciones tan solo llegamos a tener 10 cifras
% significativas de precisión correctas.

% Como entre cada par de iteraciones se duplican aproximadamente el número
% de cifras significativas de precisión correctas, entonces la velocidad de
% convergencia es cuadrática.

aprox_x2 = zeros(1,51);
aprox_x2(1) = 1.5;
for n=1:50
    aprox_x2(n+1) = aprox_x2(n) - (aprox_x2(n)^3-3*aprox_x2(n)+2)/(3*aprox_x2(n)^2-3);
end
s2 = aprox_x2(end);

% Al tener una función en el numerador y su derivada en el denominador,
% este método es el de Newton. El método converge al valor 1

% Vemos gráficamente que el método de Newton converge hacia la raíz de la
% función igual a 1.
plot(1:50, aprox_x2(2:51),'bo:')

% Estamos calculando una de las raíces de la función f(x) = x^3-3x+2 = 0

Erel = abs((aprox_x2-s2)./s2);
semilogy(1:50, Erel(2:51), 'bo:')

% Necesitamos 27 iteraciones para alcanzar 15 cifras significativas de
% precisión correctas, alcanzando de esta forma el valor exacto de la raíz.

% Como en este caso necesitamos más de una iteración para alcanzar una
% nueva cifra significativa de precisión correcta, entonces en este caso la
% velocidad de convergencia es lineal.

x = -2.5:0.01:1.5;
y = x.^3-3.*x+2;
plot(x,y,'b',x,y*0,'k')

% La diferencia de comportamiento entre un punto inicial y el otro se debe
% a que una de ellas es una raíz simple (la de -2) y la otra es raíz doble
% (la de 1).

% ----------------------------------------------------------------------- %

% ENERO12 - a)
clc,clear

% Ejercicio1
x = 0:0.01:4;
y = x.^3 + 4.*x.^2-10;
plot(x,y,'b',x,y*0,'k')
% Un posible intervalo en el que se encuentra una raíz simple sería [1,2]

s = 1.3652300134140969;
x = 1;
for n=1:10
    x = (2*x^3 + 4*x^2+10)/(3*x^2+8*x);
    fx = x.^3 + 4.*x.^2-10;
    erel = abs(x-s)/abs(s);
    ncif = floor(-log10(erel));
    fprintf("Iteración: %2d valor x_k: %.16f valor f(x_k): %.3e Ncifras = %2d\n",n,x,fx,ncif);
end

% El valor de la raíz s es 1.3652300134140969. Han sido necesarias 5
% iteraciones para alcanzar la precisión de la máquina (y la raíz exacta
% de la función)

%   Iteración   Ncifras
%       1          1
%       2          2
%       3          5
%       4         10
%       5         15
%       6         15
%       7         15
%       8         15
%       9         15
%      10         15

% Luego, la velocidad de convergencia es cuadrática, ya que entre cada par
% de iteraciones se duplican aproximadamente el número de cifras
% significativas de precisión correctas

% Ejercicio2
clc,clear

x = -5:0.01:5;
y = exp(x)-2.*x.^2;
plot(x,y,'b',x,y*0,'k')
% Un posible intervalo en el que se encuentra una raíz simple sería [1,2]

% El método de Newton sobre esta función sería 
% xn+1 = xn - (exp(xn)-2*xn^2)/(exp(xn)-4*xn)
% xn+1 = ((xn-1)*exp(xn) - 2*xn^2) / (exp(xn)-4*xn)

s = -0.5398352769028201; % raiz s1
x = 0;
for n=1:10
    x = ((x-1)*exp(x) - 2*x^2) / (exp(x)-4*x);
    fx = exp(x)-2*x^2;
    erel = abs(x-s)/abs(s);
    ncif = floor(-log10(erel));
    fprintf("Iteración: %2d valor x_k: %.16f valor f(x_k): %.3e Ncifras = %2d\n",n,x,fx,ncif);
    % fprintf("Iteración: %2d valor x_k: %.16f valor f(x_k): %.3e\n",n,x,fx);
end

% El valor de la raíz s es -0.5398352769028201. Han sido necesarias 6
% iteraciones para alcanzar la precisión de la máquina (y la raíz exacta
% de la función)

%   Iteración   Ncifras
%       1          0
%       2          0
%       3          2
%       4          4
%       5          9
%       6         15
%       7         15
%       8         15
%       9         15
%      10         15

% Luego, la velocidad de convergencia es cuadrática, ya que entre cada par
% de iteraciones se duplican aproximadamente el número de cifras
% significativas de precisión correctas

fprintf("\n\n")
s = 1.4879620654981773; % raiz s2
x = 1;
for n=1:10
    x = ((x-1)*exp(x) - 2*x^2) / (exp(x)-4*x);
    fx = exp(x)-2*x^2;
    erel = abs(x-s)/abs(s);
    ncif = floor(-log10(erel));
    fprintf("Iteración: %2d valor x_k: %.16f valor f(x_k): %.3e Ncifras = %2d\n",n,x,fx,ncif);
    %fprintf("Iteración: %2d valor x_k: %.16f valor f(x_k): %.3e\n",n,x,fx);
end

% El valor de la raíz s es 1.4879620654981773. Han sido necesarias 5
% iteraciones para alcanzar la precisión de la máquina (y la raíz exacta
% de la función)

%   Iteración   Ncifras
%       1          1
%       2          3
%       3          6
%       4         14
%       5         15
%       6         15
%       7         15
%       8         15
%       9         15
%      10         15

% Luego, la velocidad de convergencia es cuadrática, ya que entre cada par
% de iteraciones se duplican aproximadamente el número de cifras
% significativas de precisión correctas

fprintf("\n\n")
s = 2.6178666130668127; % raiz s3
x = 3;
for n=1:10
    x = ((x-1)*exp(x) - 2*x^2) / (exp(x)-4*x);
    fx = exp(x)-2*x^2;
    erel = abs(x-s)/abs(s);
    ncif = floor(-log10(erel));
    fprintf("Iteración: %2d valor x_k: %.16f valor f(x_k): %.3e Ncifras = %2d\n",n,x,fx,ncif);
    %fprintf("Iteración: %2d valor x_k: %.16f valor f(x_k): %.3e\n",n,x,fx);
end

% El valor de la raíz s es 2.6178666130668127. Han sido necesarias 6
% iteraciones para alcanzar la precisión de la máquina (y la raíz exacta
% de la función)

%   Iteración   Ncifras
%       1          1
%       2          2
%       3          3
%       4          6
%       5         13
%       6         15
%       7         15
%       8         15
%       9         15
%      10         15

% Luego, la velocidad de convergencia es cuadrática, ya que entre cada par
% de iteraciones se duplican aproximadamente el número de cifras
% significativas de precisión correctas

% Un posible intervalo de convergencia sería [0.5, 2] ya que comenzando en
% cualquiera de estos puntos convergería el método a la raíz s2 (si
% comienzo en un a más pequeño me aproximaría a s1; y si comienzo en un b
% más grande convergería a s3).

% ----------------------------------------------------------------------- %

% ENERO12 - b)
clc,clear 

% Apartado a) y b)
r = 3; K = 1;
R = zeros(1,10); 
x = ones(1,11); % ya tengo x0 = 1
for n = 1:10
    x(n+1) = r/(1+x(n)*K);
    R(n) = x(n+1); 
    fprintf("Iteración: %2d x = %.16f\n", n, x(n+1));
end
% El método iterativo converge hacia el valor 1.3016657990629881

xx = 0:0.01:3;
yy = r./(1+xx.*K);
plot(x(2:10),R(1:9),'rs',x(11),R(10),'gs',xx,yy,'b')

% Apartado c) 
x = ones(1,71); % ya tengo x0 = 1
for n = 1:70
    x(n+1) = r/(1+x(n)*K);
    R(n) = x(n+1); 
    fprintf("Iteración: %2d x = %.16f\n", n, x(n+1));
end
s = x(end);

erel = abs(x-s)./abs(s);
ncif = floor(-log10(erel));
plot(1:70, ncif(2:71), 'r*')

% La velocidad de convergencia es lineal ya que para alcanzar una nueva
% cifra significativa de precisión correcta se necesitan aproximadamente
% unas 4 iteraciones

% Alcanzamos 15 cifras significativas de precisión en la iteración 59

% Apartado d) 
fprintf("\n\n")
x = ones(1,71); % ya tengo x0 = 1
for n = 1:70
    x(n+1) = r/(1+(x(n)/K)^2);
    R(n) = x(n+1); 
    fprintf("Iteración: %2d x = %.16f\n", n, x(n+1));
end
s = x(end);

erel = abs(x-s)./abs(s);
ncif = floor(-log10(erel));
plot(1:70, ncif(2:71), 'r*')

% ----------------------------------------------------------------------- %

% JULIO12
clc,clear

% Apartado b)
x = 2:0.01:10;
y = g(x);
plot(x,y,'b',x,y*0,'k')

% Un posible intervalo para s1 sería [4,5] y otro posible intervalo para s2
% sería [7,8]

% Apartado d) 
[s1,fs1,iter1] = secante(4,5,0.001,100);
[s2,fs2,iter2] = secante(7,8,0.001,100);
fprintf("Raíz %.14f Función %.14f Iteraciones %2d\n",s1,fs1,iter1)
fprintf("Raíz %.14f Función %.14f Iteraciones %2d\n",s2,fs2,iter2)

% ----------------------------------------------------------------------- %

% FUNCIONES AUXILIARES 

% DIC18
function y = f(x)
y = x.^3 + x.^2 - 8.*x - 12;
end

function y = fp(x)
y = 3*x^2 + 2*x - 8;
end

% JULIO12 - Apartado a)
function y = g(x)
y=cos(x)./sqrt(x);
end

% Método de la secante - Apartado c)
function [s,fs,its] = secante(a,b,tol,maxiter)
% Entrada:      extremos del intervalo (a,b) donde se encuentra la raíz
%               tol tolerancia permitida de la solución
%               maxiter número máximo de iteraciones
% Salida:       s valor de la raíz
%               fs valor de la raíz en la función
%               its número de iteraciones empleadas
u= g(a);
v= g(b);
if u*v>0
    fprintf('Error, no hay ninguna raíz en el intervalo [%.2f,%.2f]', a,b)
    
else
    s=(a*v-b*u)/(v-u);
    fs=g(s);
    its=1;
    
    while (abs(fs)>tol) && (its<maxiter)
        if u*fs>0
            a=s; u=fs;
        else
            b=s; v=fs;
        end
        
        s=(a*v-b*u)/(v-u);
        fs=g(s);
        its=its+1;
    end
end 

return
end