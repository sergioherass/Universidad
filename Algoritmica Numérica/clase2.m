% ------------------------------------------------------------ %
%                   ERRORES. COMA FLOTANTE
% ------------------------------------------------------------ %

%Problema 1
clc,clear
n=0:1000;
v = (n.*sin(n))./(n.^2+cos(n));
sumav = sum(v);
fprintf("El valor de la suma de v es %.9f\n",sumav);
[max, posMax1] = max(v); % [valor_obtenido, posici�n_donde_se_encuentra]
[min, posMin] = min(v); % si no ponemos nada se obtiene valor_obtenido

% Otra forma
posMax2 = find(v == max); % En este caso encuentra todas las posibles 
posMin2 = find(v == min); % posiciones cuyo valor sea max o min

numPositivos = length(find(v>0));

x = 0:0.01:0.5;
suma1 = zeros(1,length(x));
for n=1:100
    suma1 = suma1 + x.^n./n;
end
vreal = -log(1-x);
erel = abs(vreal-suma1)./abs(vreal);
semilogy(x,erel,':ob')

% Problema 2:
clc,clear

h = 10.^-(1:10);
vreal = cos(2);
vaprox = (sin(2+h)-sin(2-h))./(2.*h);
erel = abs(vreal-vaprox)./abs(vreal);
fprintf("Error relativo %e\n",erel)
loglog(h,erel,':bo')
ncif = round(-log10(erel));
semilogx(h,ncif,':ro')
% Para h = 10^-6 se obtienen 12 cifras significativas de precisi�n
% correctas (el m�ximo alcanzable en este ejercicio). Por lo que es para
% este valor donde se comete un menor error y donde el resultado ser� m�s
% pr�ximo al valor real (cos(2))

% ------------------------------------------------------------ %

% Ejercicio 3
clc,clear

n = 0:4;
x = pi/4;
vreal = cos(x);
vaprox = sum((-1).^n.*x.^(2.*n)./factorial(2.*n));
fprintf("Valor real %.18f Valor aproximado %.18f\n",vreal,vaprox)
% Se obtienen 7 cifras significativas correctas de precisi�n

termino = 1;
iter = 0;
vaprox2 = 0;
while abs(termino)>1e-15
    termino = (-1)^iter*x^(2*iter)/factorial(2*iter);
    iter = iter + 1;
    vaprox2 = vaprox2 + termino;
    fprintf("N�mero de iteraciones %d Valor real %.18f Valor aproximado %.18f\n",iter,vreal,vaprox2)
end
fprintf("\n\n")
iter = 0; vaprox2 = 0;
valores = zeros(1,15);
while abs(termino)>1e-30
    termino = (-1)^iter*x^(2*iter)/factorial(2*iter);
    iter = iter + 1;
    vaprox2 = vaprox2 + termino;
    valores(iter) = vaprox2; % me guardo los valores en un vector para el apartado c) 
    fprintf("N�mero de iteraciones %d Valor real %.18f Valor aproximado %.18f\n",iter,vreal,vaprox2)
end
% En este segundo caso el n�mero de iteraciones aumenta de 10 a 15 dado que
% quiero parar de sumar t�rminos cuando su valor sea m�s peque�o (1e-30 en
% lugar de 1e-15). Pero el cifras significativas correctas no var�a ya que
% se ha alcanzado la precisi�n de la m�quina con un total de 16 cifras
% significativas correctas.

erel = abs(vreal-valores)./abs(vreal);
ncif = floor(-log10(erel)); % floor trunca (fix tambi�n) y round redondea
plot(1:15,ncif,'r*')

x=-2*pi:0.1:2*pi;
n=0:4;
vreal = cos(x);
vaprox = zeros(1,length(x));
for i = 1:length(x)
    vaprox(i) = sum((-1).^n.*x(i).^(2.*n)./factorial(2.*n));
end

erel = abs(vreal-vaprox)./abs(vreal);
semilogy(x,erel,':ob')
% Esta aproximaci�n es �til para valores cercanos a 0, cuanto m�s nos
% alejamos de 0, peor es el resultado.
% De hecho en el apartado anterior en el que x = ?/4 funciona "bastante"
% bien, por lo que tiene sentido que para valores cercano a 0 se cometa un
% error bastante peque�o

% ------------------------------------------------------------ %

% Ejercicio 4
clc,clear
x = pi;
n=0:12;
vreal = x*cos(x);
vaprox = sum((-1).^n.*x.^(2.*n+1)./factorial(2.*n));
erel = abs(vreal-vaprox)./abs(vreal);
ncif = floor(-log10(erel));
fprintf("Valor exacto %.15f aproximado %.15f error relativo %.15f cifras significativas %d\n",vreal,vaprox,erel,ncif)

fprintf("\n\n")

iter = 0;
ncif = 0;
while ncif<10
    n = 0:iter;
    vaprox = sum((-1).^n.*x.^(2.*n+1)./factorial(2.*n));
    erel = abs(vreal-vaprox)./abs(vreal);
    ncif = floor(-log10(erel));
    iter = iter + 1;
    fprintf("N�mero de t�rminos de la serie %d N�mero de Cifras obtenidas %d\n", iter, ncif)
end
% Si queremos obtener la mejor aproximaci�n de la m�quina y en cada
% iteraci�n aumenta aproximadamente en 1, realizar�a un total de 16
% iteraciones para alcanzar el m�ximo n�mero de cifras significativas de
% precisi�n correctas que Matlab puede proporcionar, es decir, 16

clear
x=pi:0.1:2*pi;
n=0:10;
vreal = x.*cos(x);
vaprox = zeros(1,length(x));
for i=1:length(x)
    vaprox(i) = sum((-1).^n.*x(i).^(2.*n+1)./factorial(2.*n));
end
erel = abs(vreal-vaprox)./abs(vreal);
ncif = floor(-log10(erel));
plot(x,ncif,'g*')
% Observamos que cuanto m�s nos alejamos de ?, peor se comporta, es decir,
% se obtienen menos cifras significativas correctas

% Ejercicio 5
clc,clear
n=0:15;
x=10.^n;
ff = f(x);
gg = g(x);
semilogy(n,ff,'b',n,gg,'g')
% Las dos gr�ficas son pr�cticamente iguales, ya que una se superpone sobre
% la otra en casi todo momento.
% Para evaluar 10^5 me dar�a igual usar una que otra, aunque parece que la
% funci�n g(x) obtiene valores m�s precisos, por lo que ser�a �sta la que
% utilizar�a para 10^20
gg20 = g(10^20);
% En este caso el resultado es 0 ya que el t�rmino es tan peque�o que la
% operaci�n que llega a efectuar es log(1) que es igual a 0

% ------------------------------------------------------------ %

% Ejercicio 6
clc,clear
k=1:10;
x=10.^k;
vex = 4./(x+sqrt(x.^2-4));
vap = x-sqrt(x.^2-4);
erel = abs(vex-vap)./abs(vex);
ncif = floor(-log10(erel));
[max, posMax] = max(ncif);
[min, posMin] = min(ncif);

subplot(121), loglog(x,erel,'*r')
subplot(122), semilogx(x,ncif,'*b')

A = [k; erel; ncif]; % a la hora de imprimir, coger� las columnas en cada iter
fprintf("k=%2d erel=%0.2e ncif=%2d\n",A)


k=[1:10];
x=10.^k;

vex=4./(x+sqrt((x.^2)-4))
vap=x-sqrt((x.^2)-4)

erel=abs(vex-vap)./abs(vex)
ncif=-log(erel)
max(ncif)
min(ncif)
subplot(211);loglog(x,erel,'*r');
subplot(212);semilogx(x,ncif,'*b');
A=[k;erel;ncif]
fprintf('\nValor de k=%2d Valor erel=%0.2e ncif=%2d',A)

% Podemos observar que cuanto mayores sean los valores de k (es decir,
% tengamos una potencia de 10 m�s grande) mejores resultados se obtienen
% Esto se debe a que esta aproximaci�n funciona con valores muy grandes y
% cuanto menores son, peores resultados se obtiene.
clear
k=1:10;
x=single(10.^k);
vex = 4./(x+sqrt(x.^2-4));
vap = x-sqrt(x.^2-4);
erel = abs(vex-vap)./abs(vex);
ncif = floor(-log10(erel));
[max, posMax] = max(ncif);
[min, posMin] = min(ncif);

subplot(121), loglog(x,erel,'*r')
subplot(122), semilogx(x,ncif,'*b')

A = [k; erel; ncif]; % a la hora de imprimir, coger� las columnas en cada iter
fprintf("k=%2d erel=%0.2e ncif=%2d\n",A)

% En este caso como la precisi�n se reduce a "la mitad" ya que pasamos de
% tener precisi�n doble a precisi�n simple, entonces obtiene un menor
% n�mero de cifras significativas correctas (una tercera parte) y en muy
% pocas iteraciones (en 4) deja de haber cifras significativas de presici�n
% correctas. 

