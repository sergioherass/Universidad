% EXAMEN ADE 
% Ejercicio 1
% Apartado 1
n = 0;
y = 2;

while y~=4
    n = n+1;
    h = 2^-n;
    y = 4+h;
end

% La n=51 representa el número de bits dedicados a la mantisa, es decir, la
% precisión que soporta la máquina

n = single(0);
y = single(2);
while y~=4
    n = n+1;
    h = 2^-n;
    y = 4+h;
end

% En este caso los bits dedicados a la mantisa se reducen aproximadamente a
% la mitad, quedando un total de 22 bits dedicados a la mantisa y siendo
% esta la precisión que soporta la máquina

for n = 43:53
    h = 2^-n;
    y = 4+h;
    fprintf("h:2^-%2d, y:%0.20f\n",n,y)
end

% Apartado 2
n = 36:53;
h = 2.^-n;
vex = h./(sqrt(4+h)+2);
vap = sqrt(4+h)-2;
e = abs((vex-vap)./vex);
cif = floor(-log10(e));
[maximo,pos] = max(cif);
fprintf("\nPara h = %.4e se obtiene el máximo número de cifras significativas\n",h(pos));

subplot(121); semilogy(n, e, 'bo')
subplot(122); plot(n, cif, 'r*')

% Apartado 3
% Cada aproxidamente 3/4 iteraciones alcanzamos una nueva cifra de
% precisión correcta
% A partir de h = 2^-46 se garantizan las 15 cifras de precisión
% significativas correctas 
% Podemos observar que a partir de 2^-50 en adelante la gráfica de los
% errores aumenta y la de las cifras significativas decrece hasta 0, esto
% se debe a que se alcanza la exactitud y no hay diferencia entre el vex y
% vap. Como la expresión de vap es más sencilla que la de vex, y al no
% tener diferencia entre ambos resultados, entonces será mejjor utilizar
% vap para valores de h muy pequeños


% Ejercicio 2
clc, clear
% Apartado 1
x1 = pi*(1:0.5:2.5)';
% El grado del polinomio debe ser 3 ya que tenemos un total de 4 puntos de
% interpolación
y1=2+sin(x1-pi);
H1 = [x1.^0 (x1-pi) (x1-pi).^2 (x1-pi).^3]; % matriz de coeficientes
b1 = y1; % vector de términos independientes
c1 = H1\b1
H1(end,:) % ultima fila de la matriz de coeficientes

% Apartado 2
x2 = pi*(1:0.5:2)';
y2 = 2+sin(x2-pi);
% Calculamos la derivada de la función
% p'(x) = 0*c0 + 1*c1 + 2*c2*(x-pi) + 3*c3*(x-pi)^2
% p'(pi) = 0*c0 + 1*c1 + 0*c2 + 0*c3 = 1 --> c1 = 1;
% p(x) = c0 + (x-pi) + c2*(x-pi)^2 + c3*(x-pi)^3
H2 = [x2.^0 (x2-pi).^2 (x2-pi).^3];
b2 = y2 - x2 + pi
c2 = H2\b2

% Apartado 3
xx = pi*(1:0.001:2.5); % donde lo dibujo
ff = 2+sin(xx-pi);
p1 = c1(1) + c1(2).*(xx-pi) + c1(3).*(xx-pi).^2 + c1(4).*(xx-pi).^3;
p2 = c2(1) + (xx-pi) + c2(2).*(xx-pi).^2 + c2(3).*(xx-pi).^3;
e1 = abs(ff-p1)./abs(ff);
e2 = abs(ff-p2)./abs(ff);

% subplot(rows,cols,cuadrante)
subplot(121); plot(xx,ff,'g',xx,p1,'r',xx,p2,'b',x1,y1,'r*') 
subplot(122); semilogy(xx,e1,'r.',xx,e2,'b.')

% Apartado 4
% El error de interpolación será nulo cuando x sea igual a los puntos de
% interpolación, ya que ambos polinomios deben pasar por ellos.

% El segundo de los polinomios calculamos es más preciso en el intervalo
% [pi,4], por lo que será éste el que proporcione una mayor precisión para
% el punto x = 3.2, alcanzando un total de 4 cifras significativas de
% precisión correctas.

% ----------------------------------------------------------------------- %

% Examen Octubre21
clc,clear

x = 10.^(0:10);
a = 1./(sqrt(x.^2+1)+x);
b = sqrt(x.^2+1)-x;
erel = abs((a-b)./a);
subplot(111), loglog(x,b,'bo:')
% En este caso pasa de tener 7 cifras significativas de precisión a tener 8
% cifras significativas de precisión correctas 

fprintf("Con x=10^5 obtenemos a = %.8e y b = %.8e\n",a(5), b(5))
% Asumiendo que a es el valor exacto de la función, entonces b no tiene
% ninguna cifra significativa de precisión correcta.

% A partir de x = 10^8 no aparece ningún resultado en el error relativo,
% por lo que es a partir de este valor cuando no obtenemos ninguna cifra de
% precisión correcta

x = single(10.^(0:10));
a_single = 1./(sqrt(x.^2+1)+x);
erel = abs((a-a_single)./a);
hold on, loglog(x,erel, 'ro:'), hold off
% En este caso la precisión simple funciona mejor a partir de x = 10^8 

% Ejercicio 2
clc,clear
x = 1:2^-26:1+10^-6;
xs = single(x);
semilogx(x,(x-xs),'bo:')
% En este caso tenemos un máximo de 9 bits dedicados a la mantisa, ya que
% es el máximo número de elementos que tenemos entre un salto y otro

% Tenemos un total de 2^9 (mantisa) x 2^9 (exponente) aproximadamente =
% 2^18 números máquina en total

% Esto se debe a que estamos pasando de un exponente a otro en cada salto 

% ----------------------------------------------------------------------- %
% Simulacro 16Nov
% Ejercicio 1
k = 0:18;
x = 10.^k;
f = x.*(sqrt(x+1)+sqrt(x)).^(-1); % vex
g = x.*(sqrt(x+1)-sqrt(x)); % vap
Erel = abs((f-g)./g);
Ncifras = floor(-log10(Erel));

% La expresión de la izquierda (f) es más compleja que la de la derecha (g)
% ya que tenemos que elevar a menos uno la parte de las raíces.

subplot(131), semilogx(x,f,'b',x,g,'r')
subplot(132), loglog(x,Erel,'bo:')
subplot(133), semilogx(x,Ncifras,'r*:')

% Para x = 1, la gráfica obtiene el mayor número de cifras significativas,
% siendo este valor 15
% Para x = 10^18 no se obtiene ninguna cifra de precisión significativa
% correcta 
% Al aumentar el valor de x la precisión numérica disminuye hasta ser nula
% ya que esta aproximación solo funciona bien para valores de x muy
% pequeños nada más 
% Para valores de x muy grandes solamente funciona la expresión de la
% izquierda, es decir, f ya que es la función que calcula los valores
% exactos y la otra es una aproximación de ella.

% Ejercicio3
xk = [0.5 0.8 1.1 1.8 4]';
yk = [7.1 4.4 3.2 1.9 0.9]';
xx = 0.4:0.001:4.1; % donde lo pinto
% Como tenemos un total de 5 puntos, entonces el polinomio debe ser de
% grado 4 -> p(x) = a + bx + cx^2 + dx^3 + ex^4
H = [xk.^0 xk xk.^2 xk.^3 xk.^4];
c = H\yk;
pp = c(1) + c(2).*xx + c(3).*xx.^2 + c(4).*xx.^3 + c(5).*xx.^4;
pp1 = c(1) + c(2) + c(3) + c(4) + c(5);
pp2 = c(1) + c(2)*2 + c(3)*2^2 + c(4)*2^3 + c(5)*2^4;
pp3 = c(1) + c(2)*3 + c(3)*3^2 + c(4)*3^3 + c(5)*3^4;
subplot(111), plot(xx,pp,'g',xk,yk,'rs',1,pp1,'bo',2,pp2,'bo',3,pp3,'bo')

% Apartado 2
% Como tenemos 5 puntos, entonces debemos llegar hasta x^-4
H = [xk.^0 xk.^-1 xk.^-2 xk.^-3 xk.^-4];
c = H\yk % imprimimos los valores de los coeficientes del polinomio
pp = c(1) + c(2).*xx.^-1 + c(3).*xx.^-2 + c(4).*xx.^-3 + c(5).*xx.^-4;
hold on, plot(xx,pp,'go',xk,yk,'rs'), hold off
