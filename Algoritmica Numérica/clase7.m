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
    fprintf("Iteraci�n: %2d x = %.15f Eabs = %.2e Ncifras = %.2d\n",iter,x,eabs,ncif);
end

% Necesitamos 19 iteraciones para alcanzar un total de 15 cifras
% significativas ed precisi�n correctas.

% El tipo de convergencia es lineal ya que el n�mero de cifras
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
    fprintf("Iteraci�n: %2d x = %.15f Eabs = %.2e Ncifras = %2d\n",iter,x,eabs,ncif);
end

% En este caso necesitamos solo 5 iteraciones para alcanzar las 15 cifras
% significativas de precisi�n correctas.

% Al tener en el denominador del m�todo iterativo la derivada del
% numerador, entonces estamos trabajando con el m�todo de Newton que
% funciona muy bien para calcular la ra�z de una funci�n en pocas
% iteraciones

% En este caso la velocidad de convergencia es cuadr�tica ya que entre cada
% par de iteraciones se duplican aproximadamente el n�mero de cifras
% significativas de precisi�n.

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
    fprintf("Iteraci�n: %2d x = %.15f Eabs = %.2e Ncifras = %2d\n",iter,x,eabs,ncif);
end

% En este caso necesitamos 5 iteraciones tambi�n para alcanzar la ra�z de 3

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
    fprintf("Iteraci�n: %2d x = %.15f Erel = %.2e\n",iter,x(n+1),erel);
end
% Difiere de la ra�z 3.14e-16. Como se va aproximando m�s a la ra�z en cada
% iteraci�n, entonces podemos afirmar que el m�todo converge a la ra�z de 2

semilogy(1:20,Erel,'bo:')
% Necesitamos 19 iteraciones para alcanzar la precisi�n de la m�quina. 
% Entre cada par de iteraciones se alcanza, aproximadamente, una nueva
% cifra significativa de precisi�n correcta.

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

% Para ver visualmente las ra�ces
x = -2.5:0.01:4;
y = f(-2.5:0.01:4);
plot(x, y, 'b', x, y*0, 'k')
% Observamos que tal y como era de esperar hay una ra�z doble cercana al
% punto -2.5, por lo que tarda mucho en aproximarse a ella; mientras que la
% ra�z cercana a 4 es simple, por lo que su aproximaci�n por el m�todo de
% Newton es muy r�pida.

% Luego en el primer caso la convergencia es lineal ya que necesito varias
% iteraciones para alcanzar una nueva cifra significativa de precisi�n
% correcta; mientras que en el segundo caso la convergencia es cuadr�tica,
% ya que entre cada par de iteraciones se duplican aproximadamente el
% n�mero de cifras significativas de precisi�n correctas.

% ----------------------------------------------------------------------- %

% JULIO13
clc,clear

aprox_x = zeros(1,11);
aprox_x(1) = -2.5;
for n=1:10
    aprox_x(n+1) = aprox_x(n) - (aprox_x(n)^3-3*aprox_x(n)+2)/(3*aprox_x(n)^2-3);
end
s1 = aprox_x(end);

% Al tener una funci�n en el numerador y su derivada en el denominador,
% este m�todo es el de Newton. El m�todo converge al valor -2

% Vemos gr�ficamente que el m�todo de Newton converge hacia la ra�z de la
% funci�n igual a -2.
plot(1:10, aprox_x(2:11),'bo:')

% Estamos calculando una de las ra�ces de la funci�n f(x) = x^3-3x+2 = 0

Erel = abs((aprox_x-s1)./s1);
semilogy(1:10, Erel(2:11), 'bo:')

% Necesitamos 5 iteraciones para alcanzar 15 cifras significativas de
% precisi�n correctas, alcanzando de esta forma el valor exacto de la ra�z,
% ya que con 4 iteraciones tan solo llegamos a tener 10 cifras
% significativas de precisi�n correctas.

% Como entre cada par de iteraciones se duplican aproximadamente el n�mero
% de cifras significativas de precisi�n correctas, entonces la velocidad de
% convergencia es cuadr�tica.

aprox_x2 = zeros(1,51);
aprox_x2(1) = 1.5;
for n=1:50
    aprox_x2(n+1) = aprox_x2(n) - (aprox_x2(n)^3-3*aprox_x2(n)+2)/(3*aprox_x2(n)^2-3);
end
s2 = aprox_x2(end);

% Al tener una funci�n en el numerador y su derivada en el denominador,
% este m�todo es el de Newton. El m�todo converge al valor 1

% Vemos gr�ficamente que el m�todo de Newton converge hacia la ra�z de la
% funci�n igual a 1.
plot(1:50, aprox_x2(2:51),'bo:')

% Estamos calculando una de las ra�ces de la funci�n f(x) = x^3-3x+2 = 0

Erel = abs((aprox_x2-s2)./s2);
semilogy(1:50, Erel(2:51), 'bo:')

% Necesitamos 27 iteraciones para alcanzar 15 cifras significativas de
% precisi�n correctas, alcanzando de esta forma el valor exacto de la ra�z.

% Como en este caso necesitamos m�s de una iteraci�n para alcanzar una
% nueva cifra significativa de precisi�n correcta, entonces en este caso la
% velocidad de convergencia es lineal.

x = -2.5:0.01:1.5;
y = x.^3-3.*x+2;
plot(x,y,'b',x,y*0,'k')

% La diferencia de comportamiento entre un punto inicial y el otro se debe
% a que una de ellas es una ra�z simple (la de -2) y la otra es ra�z doble
% (la de 1).

% ----------------------------------------------------------------------- %

% ENERO12 - a)
clc,clear

% Ejercicio1
x = 0:0.01:4;
y = x.^3 + 4.*x.^2-10;
plot(x,y,'b',x,y*0,'k')
% Un posible intervalo en el que se encuentra una ra�z simple ser�a [1,2]

s = 1.3652300134140969;
x = 1;
for n=1:10
    x = (2*x^3 + 4*x^2+10)/(3*x^2+8*x);
    fx = x.^3 + 4.*x.^2-10;
    erel = abs(x-s)/abs(s);
    ncif = floor(-log10(erel));
    fprintf("Iteraci�n: %2d valor x_k: %.16f valor f(x_k): %.3e Ncifras = %2d\n",n,x,fx,ncif);
end

% El valor de la ra�z s es 1.3652300134140969. Han sido necesarias 5
% iteraciones para alcanzar la precisi�n de la m�quina (y la ra�z exacta
% de la funci�n)

%   Iteraci�n   Ncifras
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

% Luego, la velocidad de convergencia es cuadr�tica, ya que entre cada par
% de iteraciones se duplican aproximadamente el n�mero de cifras
% significativas de precisi�n correctas

% Ejercicio2
clc,clear

x = -5:0.01:5;
y = exp(x)-2.*x.^2;
plot(x,y,'b',x,y*0,'k')
% Un posible intervalo en el que se encuentra una ra�z simple ser�a [1,2]

% El m�todo de Newton sobre esta funci�n ser�a 
% xn+1 = xn - (exp(xn)-2*xn^2)/(exp(xn)-4*xn)
% xn+1 = ((xn-1)*exp(xn) - 2*xn^2) / (exp(xn)-4*xn)

s = -0.5398352769028201; % raiz s1
x = 0;
for n=1:10
    x = ((x-1)*exp(x) - 2*x^2) / (exp(x)-4*x);
    fx = exp(x)-2*x^2;
    erel = abs(x-s)/abs(s);
    ncif = floor(-log10(erel));
    fprintf("Iteraci�n: %2d valor x_k: %.16f valor f(x_k): %.3e Ncifras = %2d\n",n,x,fx,ncif);
    % fprintf("Iteraci�n: %2d valor x_k: %.16f valor f(x_k): %.3e\n",n,x,fx);
end

% El valor de la ra�z s es -0.5398352769028201. Han sido necesarias 6
% iteraciones para alcanzar la precisi�n de la m�quina (y la ra�z exacta
% de la funci�n)

%   Iteraci�n   Ncifras
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

% Luego, la velocidad de convergencia es cuadr�tica, ya que entre cada par
% de iteraciones se duplican aproximadamente el n�mero de cifras
% significativas de precisi�n correctas

fprintf("\n\n")
s = 1.4879620654981773; % raiz s2
x = 1;
for n=1:10
    x = ((x-1)*exp(x) - 2*x^2) / (exp(x)-4*x);
    fx = exp(x)-2*x^2;
    erel = abs(x-s)/abs(s);
    ncif = floor(-log10(erel));
    fprintf("Iteraci�n: %2d valor x_k: %.16f valor f(x_k): %.3e Ncifras = %2d\n",n,x,fx,ncif);
    %fprintf("Iteraci�n: %2d valor x_k: %.16f valor f(x_k): %.3e\n",n,x,fx);
end

% El valor de la ra�z s es 1.4879620654981773. Han sido necesarias 5
% iteraciones para alcanzar la precisi�n de la m�quina (y la ra�z exacta
% de la funci�n)

%   Iteraci�n   Ncifras
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

% Luego, la velocidad de convergencia es cuadr�tica, ya que entre cada par
% de iteraciones se duplican aproximadamente el n�mero de cifras
% significativas de precisi�n correctas

fprintf("\n\n")
s = 2.6178666130668127; % raiz s3
x = 3;
for n=1:10
    x = ((x-1)*exp(x) - 2*x^2) / (exp(x)-4*x);
    fx = exp(x)-2*x^2;
    erel = abs(x-s)/abs(s);
    ncif = floor(-log10(erel));
    fprintf("Iteraci�n: %2d valor x_k: %.16f valor f(x_k): %.3e Ncifras = %2d\n",n,x,fx,ncif);
    %fprintf("Iteraci�n: %2d valor x_k: %.16f valor f(x_k): %.3e\n",n,x,fx);
end

% El valor de la ra�z s es 2.6178666130668127. Han sido necesarias 6
% iteraciones para alcanzar la precisi�n de la m�quina (y la ra�z exacta
% de la funci�n)

%   Iteraci�n   Ncifras
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

% Luego, la velocidad de convergencia es cuadr�tica, ya que entre cada par
% de iteraciones se duplican aproximadamente el n�mero de cifras
% significativas de precisi�n correctas

% Un posible intervalo de convergencia ser�a [0.5, 2] ya que comenzando en
% cualquiera de estos puntos converger�a el m�todo a la ra�z s2 (si
% comienzo en un a m�s peque�o me aproximar�a a s1; y si comienzo en un b
% m�s grande converger�a a s3).

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
    fprintf("Iteraci�n: %2d x = %.16f\n", n, x(n+1));
end
% El m�todo iterativo converge hacia el valor 1.3016657990629881

xx = 0:0.01:3;
yy = r./(1+xx.*K);
plot(x(2:10),R(1:9),'rs',x(11),R(10),'gs',xx,yy,'b')

% Apartado c) 
x = ones(1,71); % ya tengo x0 = 1
for n = 1:70
    x(n+1) = r/(1+x(n)*K);
    R(n) = x(n+1); 
    fprintf("Iteraci�n: %2d x = %.16f\n", n, x(n+1));
end
s = x(end);

erel = abs(x-s)./abs(s);
ncif = floor(-log10(erel));
plot(1:70, ncif(2:71), 'r*')

% La velocidad de convergencia es lineal ya que para alcanzar una nueva
% cifra significativa de precisi�n correcta se necesitan aproximadamente
% unas 4 iteraciones

% Alcanzamos 15 cifras significativas de precisi�n en la iteraci�n 59

% Apartado d) 
fprintf("\n\n")
x = ones(1,71); % ya tengo x0 = 1
for n = 1:70
    x(n+1) = r/(1+(x(n)/K)^2);
    R(n) = x(n+1); 
    fprintf("Iteraci�n: %2d x = %.16f\n", n, x(n+1));
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

% Un posible intervalo para s1 ser�a [4,5] y otro posible intervalo para s2
% ser�a [7,8]

% Apartado d) 
[s1,fs1,iter1] = secante(4,5,0.001,100);
[s2,fs2,iter2] = secante(7,8,0.001,100);
fprintf("Ra�z %.14f Funci�n %.14f Iteraciones %2d\n",s1,fs1,iter1)
fprintf("Ra�z %.14f Funci�n %.14f Iteraciones %2d\n",s2,fs2,iter2)

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

% M�todo de la secante - Apartado c)
function [s,fs,its] = secante(a,b,tol,maxiter)
% Entrada:      extremos del intervalo (a,b) donde se encuentra la ra�z
%               tol tolerancia permitida de la soluci�n
%               maxiter n�mero m�ximo de iteraciones
% Salida:       s valor de la ra�z
%               fs valor de la ra�z en la funci�n
%               its n�mero de iteraciones empleadas
u= g(a);
v= g(b);
if u*v>0
    fprintf('Error, no hay ninguna ra�z en el intervalo [%.2f,%.2f]', a,b)
    
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