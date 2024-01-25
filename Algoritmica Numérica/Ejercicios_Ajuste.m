clear all, clc 

% Problema 1:

H=[1 -1; 1 1; 1 0; 1 2];
b=[-2.05 0.05 -1.15 1.05]';

c=((H'*H)^-1)*H'*b;
c1=H\b;

% Podemos observar que en ambos casos los valores coinciden, tal y como era
% de esperar. 

r=H*c-b;

% Tal y como nos indica el vector residuo difieren 0.05 de la aproximación

%-------------------------------------------------------------------------%

% Problema 2

xi=[-1 0 1 2]';
yi=[-2 -1 0 3]';
xx=[-1:0.1:2]; % Donde lo dibujo

% Apartado a)

H1=[1 -1; 1 0; 1 1; 1 2];
b1=yi;
c1=H1\b1;
r1=H1*c1-b1;
E1=sum(r1.^2);
yy1=c1(1)+c1(2)*xx;

% Apartado b)

H2=[1 (-1)^2; 1 0; 1 1; 1 2^2];
b2=yi;
c2=H2\b2;
r2=H2*c2-b2;
E2=sum(r2.^2);
yy2=c2(1)+c2(2)*xx.^2;

H3=[1 -1 (-1)^2; 1 0 0; 1 1 1; 1 2 2^2];
b3=yi;
c3=H3\b3;
r3=H3*c3-b3;
E3=sum(r3.^2);
yy3=c3(1)+c3(2)*xx+c3(3)*xx.^2;

H4=[1 -1 (-1)^2 (-1)^3; 1 0 0 0; 1 1 1 1; 1 2 2^2 2^3];
b4=yi;
c4=H4\b4;
r4=H4*c4-b4;
E4=sum(r4.^2);
yy4=c4(1)+c4(2)*xx+c4(3)*xx.^2+c4(4)*xx.^3;

plot(xi,yi,'rs',xx,yy1,'g.',xx,yy2,'b:',xx,yy3,'cx',xx,yy4,'r'), hold on; plot(xi, r1,'blacko',-1:0.01:2,0*[-1:0.01:2],'k'), hold off;
legend({'Datos','Recta','Polinomio A+Bx^2','Polinomio grado 2','Polinomio grado 3'}); 

% Podemos observar que los errores obtenidos (de mayor a menor) son E2, E1,
% E3 y E4. Esto es así ya que cuanto mayor grado tenga el polinomio (sin ningún grado intermedio nulo),
% mejor se ajustará a los puntos por los que debe pasar. También se puede
% observar que en el caso de aparecer algún grado nulo el error aumenta considerablemente.

% Es por todo ello que la que mejor se ajusta, es decir, la que comete un
% menor error es la función y4.

% Finalmente si observamos el vector residuo r1 superpuesto sobre la
% gráfica obtenida anteriormente, se puede apreciar que difiere bastante el
% resultado dado (la recta de puntos en color verde) con el realmente se
% esperaba (los puntos rojos). Si la recta se aproximara a la solución
% entonces los puntos del vector residuo (puntos negros) estarían cercanos
% todos al 0 (la diferencia entre la aproximación y los puntos debe ser lo
% más cercana a 0 posible). También podemos observar que a pesar de todo el
% punto más cercano es el segundo mientras que el más lejano es el tercero.

%-------------------------------------------------------------------------%

% Problema 3

xi=[-0.44 0.09 0.92 0.23 -0.68 0.81 -0.85 0.55]';
yi=[0.12 0.09 0.48 -0.01 0.26 0.30 0.38 0.15]';
xx=[-1:0.01:1]; % Donde lo dibujo
% Para el polinomio p
H1=[xi.^0 xi xi.^2];
c1=H1\yi; % Coeficientes A, B y C
pp=c1(1)+c1(2)*xx+c1(3)*xx.^2;
res1=H1*c1-yi;
error1=sum(res1.^2); % Error 
% Para el polinomio u
H2=[xi.^0 exp(xi) exp(-xi)];
c2=H2\yi; % Coeficientes A, B y C
uu=c2(1)+c2(2)*exp(xx)+c2(3)*exp(-xx);
res2=H2*c2-yi;
error2=sum(res2.^2); % Error
plot(xi,yi,'rs',xx,pp,'b',xx,uu,'r')

% Dado que al resolver y calcular los errores en ambos casos el segundo de
% ellos es un poco menor que el primero, podemos asegurar que el que mejor
% se ajusta es la segunda función, es decir, uu (aunque la diferencia entre
% ambos errores es mínima).

%-------------------------------------------------------------------------%

% Problema 4

ti=[0.69 1.46 1.82 2.67 2.85]';
hi=[8.19 11.34 11.50 5.05 2.97]';
xx=[0:0.01:3.1]; % Donde lo dibujo

% Apartado a)

H1=[ti (-1/2)*ti.^2];
c1=H1\hi;
v0=c1(1); g=c1(2);
res1=H1*c1-hi;
Error1=sum(res1.^2);
y1=c1(1)*xx-(1/2)*c1(2)*xx.^2;

% El valor de v0 en este caso es 15.2339 m/s, mientras que el de g es igual
% a 9.9681 m/s^2

% El error es mayor cuando h=11.50 ya que difiere con respecto al punto
% 0.2835, mientras que es menor cuando h=2.97 ya que difiere con respecto
% al punto 0.0364.

% Apartado b) 

% Ahora sabemos que g=9.8, resolver como un problema de ajuste con una sola
% incógnita. Para ello despejamos y dejamos en el otro término 1/2*g*t^2
% sumando.

H2=[ti];
b=hi+((1/2)*9.8*ti.^2);
c2=H2\b;
res2=H2*c2-b;
Error2=sum(res2.^2);
y2=c2(1)*xx-(1/2)*9.8*xx.^2;

% Me fío más del primer caso ya que el valor de g es calculado con exactitud 
% y no se trata de una aproximación como en el segundo de los casos. 

plot(ti,hi,'rs',xx,y1,'b',xx,y2,'r')

% La piedra tarda en caer 3.05 s desde que es lanzada y alcanza su altura máxima 
% a los 1.5 s con una altura de 11.64 m.

%-------------------------------------------------------------------------%

% Problema 5

ci=[0.5 0.8 1.5 2.5 4]';
ki=[1.1 2.4 5.3 7.6 8.9]';

% Primero debemos resolver el problema que viene dado por el tipo de
% ecuación que nos dan (k=(Ac^2/(B+c^2))). De aquí debemos sacar el valor
% de A y de B, por lo que debemos despejar y obtenemos así una nueva
% ecuación con la que podemos formar el sistema sobredeterminado a resolver
% y que nos proporcionará los valores de A y de B tal y como queremos. Esta
% nueva ecuación es: c^2A-kB = kc^2

H=[ci.^2 -ki];
b=ki.*ci.^2;
c=H\b;
A=c(1); B=c(2);

% Luego los valores de A y B son 10.0102 y 1.9932 respectivamente. 

tasa_crec = (A*2^2)/(B+2^2);

% Si la concentración de oxígeno es igual a 2 entonces la tasa de
% crecimiento es 6.6810

%-------------------------------------------------------------------------%

% Problema 6

T=[25 100 150 300]';
V=[1.4 0.075 0.010 0.00075]';
xx=[25:0.01:300]; % Donde lo pinto

% Como tenemos una función del tipo V=Aexp(-BT) aplicamos logaritmos en
% ambos lados de la ecuación y obtenemos log(V)=log(A)-BT*ln(exp(1)), o lo
% que es lo mismo, dado que ln(exp(1))=1, log(V)=log(A)-BT. Si tomamos
% y=log(V), a=log(A) y b=B resulta y=a-bT y el problema se convierte en un
% ajuste por una recta. Solo debemos recordar al hallar a y b deshacer el
% cambio A=exp(a) y B=b.

% Con esto ya podemos resolver la ecuación y hallar los valores de A y B:

H=[T.^0 -T];
c=H\log(V); % De esta forma hallamos los valores de a=c(1) y b=c(2), 
            % realizamos los cambios descritos anteriormente y obtenemos
A=exp(c(1)); B=c(2);

% Luego los valores de A y B son 1.3794 y 0.0267, respectivamente. Y la
% curva queda de la siguiente forma

yy=A*exp(-B*xx);
plot(T,V,'ro',xx,yy,'b')

% Si T=200 grados, la viscosidad pasa a valer 0.0066

viscosidad=A*exp(-B*200);

%-------------------------------------------------------------------------%

% Problema 7

xi=[-0.44 0.09 0.92 0.23 -0.68 0.81 -0.85 0.55]';
yi=[0.12 0.09 0.48 -0.01 0.26 0.30 0.38 0.15]';
wi=[0.5 0.1 1.0 0.1 0.7 1.0 1.0 0.5]';
xx=[-1:0.01:1]; % Donde lo dibujo

% Forma 1 (sin utilizar la función)

% Lo primero que se debe hacer es crear una matriz para los pesos donde el
% peso será igual a ceros exceptuando en la diagonal.

ww=zeros(8,8);
for k=1:8
    for n=1:8
        if k==n
            ww(k,n)=wi(k);
        end
    end
end

% Otra forma es hacer 
%ww=zeros(length(wi),length(wi));
%for k=1:length(wi)
%   ww(k,k)=wi(k);
%end

%Función con pesos
H=[xi.^0, xi, xi.^2];
c1=((H'*ww*H)^-1)*H'*ww*yi;
yy1=c1(1)+c1(2)*xx+c1(3)*xx.^2;
res1=H*c1-yi;
Error1=sum(res1.^2);
% Función sin pesos
c2=H\yi;
yy2=c2(1)+c2(2)*xx+c2(3)*xx.^2;
res2=H*c2-yi;
Error2=sum(res2.^2);
plot(xi,yi,'ro',xx,yy1,'b',xx,yy2,'r')

% Observamos que en el caso de no utilizar pesos, la función se aproxima
% mejor

% Forma 2 (utilizando la función)

coef1=mc(xi,yi,2,wi);
y1=coef1(1)+coef1(2)*xx+coef1(3)*xx.^2;
coef2=mc(xi,yi,2);
y2=coef2(1)+coef2(2)*xx+coef2(3)*xx.^2;
plot(xi,yi,'rs',xx,y1,'b',xx,y2,'r')


function a = mc(x,f,n,w)
% a = mc(x,f,n,w) Calcula los coeficientes de un polinomio de orden n por minimos
%cuadrados partiendo de los datos que se introducen en x y f, con peso w.

% Variables de entrada:
% x,f,w: datos y pesos
% n: orden del polinomio que se debe ajustar

% Variables de salida:
% a: coeficientes del polinomio

if (nargin<4)
    w = x-x+1;
end
m = size(x,1)+size(x,2)-1;
if (m>=n+1) %Si hay suficientes datos
    for j=1:2*n+1, % crear el vector s
        s(j) = 0;
        for i=1:m
            s(j) = s(j)+w(i)*x(i)^(j-1);
        end
    end
    for j=1:n+1, % crear el vector c
        c(j) = 0;
        for i=1:m
            c(j) = c(j)+w(i)*f(i)*x(i)^(j-1);
        end
    end
    for i=1:n+1, %crear la matriz del sistema
        for j=1:n+1
            M(i,j) = s((i-1)+j);
        end
    end
    size(M); a = M\c'; %resolver el sistema
end
end