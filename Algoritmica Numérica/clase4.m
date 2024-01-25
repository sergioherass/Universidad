% ----------------------------------------------------------------------- %
%                       AJUSTE. MEJOR APROXIMACIÓN
% ----------------------------------------------------------------------- %

% DICIEMBRE 2018   
xk = [-2 -1 0 1 2]';
yk = [0 1 3 1 0]';
xx = -3:0.01:3; % Donde lo pinto

H = [xk.^0 xk.^2];
c = H\yk;
pp = c(1) + c(2).*xx.^2;

plot(xk,yk,'ro', xx,pp,'b')

H = [cos(xk) -yk.*sin(xk)];
c = H\yk;
pp = c(1).*cos(xx)./(1+c(2).*sin(xx));
hold on, plot(xx,pp,'g'), hold off

% ----------------------------------------------------------------------- %

% JULIO 2018
clc,clear

xi = [-0.5 -0.3 0.1 0.5]';
yi = [2.5 2.3 1.9 1.4]';
xx = -0.7:0.01:0.7; % Donde lo pinto

H = [xi.^0 tan(xi)];
c = H\yi;
pp = c(1) + c(2).*tan(xx);
plot(xi,yi,'ro',xx,pp,'b')

res = abs(yi-H*c);
error = sum(res.^2);

% ----------------------------------------------------------------------- %

% JULIO 2017
clc,clear

xi = pi*(-1.5:1:1.5)';
yi = sin(xi);
xx = -5:0.01:5; % Donde lo pinto

% Me construyo un polinomio de grado 3 genérico -> p(x) = a + bx + cx^2 + dx^3
% Obligo a que pase por el punto p'(0) = 1 
% p'(0) = 0a + b + 2c*0 + 3d*0 = 1 -> b=1
% p(x) = a + x + cx^2 + dx^3 -> p(x) - x = a + cx^2 + dx^3
H = [xi.^0 xi.^2 xi.^3];
B = yi-xi;
c = H\B;
pp = c(1) + xx + c(2).*xx.^2 + c(3).*xx.^3;

plot(xi,yi,'ro', xx,pp,'k.');
res = abs(B - H*c);
error = sum(res.^2);

% ----------------------------------------------------------------------- %

% JULIO 2016
clc,clear

xi = [0.24 0.95 1.73 2.23 2.99]';
yi = [0.23 -1.1 0.27 -0.29 1]';

H = [log(xi) cos(xi) exp(xi)];
c = H\yi;
fprintf("En este caso los valores de los coeficientes son:\na = %.6f, b = %.6f y c = %.6f\n",c(1),c(2),c(3))

res = abs(yi - H*c);
error = sum(res.^2);

% ----------------------------------------------------------------------- %

% JULIO 2015
clc,clear
xi=(-1:0.5:1)';

% Apartado a)
yi=(10.*log(xi.^2+xi+1))./(10.*xi.^3-20.*xi.^2+xi-2);
xx=-1:0.01:1;
ff=(10.*log(xx.^2+xx+1))./(10.*xx.^3-20.*xx.^2+xx-2); 
plot(xx,ff,'b.',xi,yi,'ro')

% Apartado b)
% Como tenemos un total de 5 datos, entonces nuestro polinomio debe de ser
% de grado 4. p(x)=a+bx+cx^2+dx^3+ex^4;
H=[xi.^0 xi xi.^2 xi.^3 xi.^4];
c=H\yi;
pp=c(1)+c(2).*xx+c(3).*xx.^2+c(4).*xx.^3+c(5).*xx.^4;
hold on; plot(xx,pp,'g.')

% Apartado c) 
H = [xi.^0 xi xi.^2 log(xi.^2+xi+1)];
c = H\yi;
aa = c(1) + c(2).*xx + c(3).*xx.^2 + c(4).*log(xx.^2+xx+1);
ff = 10*log(xx.^2 + xx + 1)./(10.*xx.^3-20.*xx.^2+xx-2);
plot(xx,aa,'m.'); hold off;

r = abs(yi - H*c);
E = sum(r.^2);

% Apartado d) 
error1 = abs(ff-pp);
e1 = sum(error1)*0.01;

error2 = abs(ff-aa);
e2 = sum(error2)*0.01;

plot(xx,error1,'b.',xx,error2,'g.')

% Se aproxima mejor a(x), es decir, la del apartado c). 
% Esta aproximación es por mínimos cuadrados. 

% ----------------------------------------------------------------------- %

% JULIO 2014
clc,clear

xi = [0.15 0.4 0.6 1.01 1.5 2.2]';
yi = [4.5 5.13 5.7 6.3 7.1 7.6]';

% y = b*x^a; 
% log(y) = log(b) + log(x)^a
% log(y) = log(b) + a*log(x)
% Debemos transformar los parámetros a y b en algo que podamos resolver
% y luego acordarnos de hacer la transformación inversa

% B = log(b) -> b = exp(B)  //  A = a
% log(y) = 1*B + A*log(x)
H = [xi.^0 log(xi)];
c = H\log(yi);
a=c(2); b = exp(c(1));
fprintf("Luego, los coeficientes de la función son:\na = %.6f y b = %.6f\n",a,b);
fprintf("Si x = 0.8, entonces y = %.6f\n",b*0.8^a);

% ----------------------------------------------------------------------- %

% JULIO 2019
clc,clear

xi = [2.6 2.9 3.1 3.5]';
yi = [2.3 4.1 5.3 6.6]';
xx = 2.6:0.01:3.6; % Donde lo pinto

% Apartado a)
% Como tenemos 4 datos, entonces mi polinomio será de grado 3
% p(x) = a + bx + cx^2 + dx^3
H = [xi.^0 xi xi.^2 xi.^3];
c = H\yi;
a = c(1); b=c(2); d=c(4); c=c(3);
pp = a + b.*xx + c.*xx.^2 + d.*xx.^3; % apartado c) 
pp1 = a + b*3.3 + c*3.3^2 + d*3.3^3; % apartado e)
fprintf("Los valores de los coeficientes son:\na=%.6f, b=%.6f, c=%.6f, d=%.6f\n",a,b,c,d)

% Apartado b) 
H = [xi.^0 1./xi log(xi) xi.^2];
c = H\yi;
A = c(1); B = c(2); C = c(3); D = c(4);
fprintf("\nLos valores de los coeficientes son:\na=%.6f, b=%.6f, c=%.6f, d=%.6f\n",A,B,C,D)
uu = A + B./xx + C.*log(xx) + D.*xx.^2; % apartado c) 
uu1 = A + B/3.3 + C*log(3.3) + D*3.3^2; % apartado e)

% Apartado c) 
plot(xi,yi,'ro', xx,pp,'b', xx,uu,'g')

% Apartado d) 
H = [xi.^0 1./xi];
c = H\yi;
a0 = c(1); a1 = c(2);
aa1 = a0 + a1/3.3; % apartado e) 
fprintf("\nLos valores de los coeficientes son:\na0=%.6f, a1=%.6f\n",a0,a1)

res = abs(yi - H*c);
error = sum(res.^2);
fprintf("\nEl error del ajuste es %.6f\n",error)

% Apartado e) 
y = 5.99;
erel_pp = abs(y-pp1)/abs(y);
erel_uu = abs(y-uu1)/abs(y);
erel_aa = abs(y-aa1)/abs(y);
fprintf("\nEl error cometido en la primera interpolación es %.6f\n",erel_pp)
fprintf("El error cometido en la segunda interpolación es %.6f\n",erel_uu)
fprintf("El error cometido en el ajuste es %.6f\n",erel_aa)

% ----------------------------------------------------------------------- %

% JULIO 2020
clc,clear

xi = (0:0.2:0.8)';
yi = [1 5 4 0 -0.5]';
xx = 0:0.01:0.8; % Donde lo pinto

% Apartado 1)
xk = [xi(1) xi(3) xi(5)]';
yk = [yi(1) yi(3) yi(5)]';
H = [xk.^0 sin(pi.*xk) sin(2.*pi.*xk)];
c = H\yk;
pp = c(1) + c(2).*sin(pi.*xx) + c(3).*sin(2.*pi.*xx);
plot(xk,yk,'og', xx,pp,'g')

% Apartado 2) 
H = [xi.^0 sin(pi.*xi) sin(2.*pi.*xi)];
c = H\yi;
uu = c(1) + c(2).*sin(pi.*xx) + c(3).*sin(2.*pi.*xx);
hold on, plot(xi,yi,'r*',xx,uu,'r')

res = abs(yi - H*c);
error = sum(res.^2);
fprintf("La mayor desviación se produce en x = 0.8 y es %.6f\n",res(5))

% Apartado 3) 
% u(0) = c1 + c2*sin(0) + c3*sin(0) = 1 -> c1 = 1
% u(x) = 1 + c2*sin(pi*x) + c3*sin(2pi*x) 
% u(x) - 1 = c2*sin(pi*x) + c3*sin(2pi*x)
H = [sin(pi.*xi) sin(2.*pi.*xi)];
fila = H(end,:);
% Se dispone de dos parámetros (c2 y c3)
c = H\(yi-1);
aa = 1 + c(1).*sin(pi.*xx) + c(2).*sin(2.*pi.*xx);
plot(xx,aa,'b'), hold off

res2 = abs(yi-H*c);
error2 = sum(res.^2);

% Apartado 4)

% La primera de ellas (la del apartado 2) producirá un menor error ya que
% tiene más parámetros libres y permite un mejor ajuste a la función
% original

% Los apartados 1 y 3, pasar por el primer punto en sí, no tienen error
% alguno; mientras que para el dato final el único que no produce error es
% el del apartado 1 (en el que se hace interpolación) 

% ----------------------------------------------------------------------- %

% JULIO 2021
clc,clear

xi = (0:3)';
yi = [27 34 35 38]';
xx = 0:0.01:3;

H = [xi.^0 xi sin(xi) cos(xi).^2];
c = H\yi;
a = c(1); b = c(2); d = c(4); c = c(3);
fprintf("\nLos valores de los coeficientes son:\na0=%.6f, a1=%.6f\n",a,b,c,d)
pp = a + b.*xx + c.*sin(xx) + d.*cos(xx).^2;
plot(xi,yi,'r*', xx,pp,'g')

% Apartado b) y c)
H = [xi.^0 xi xi.^2];
c = H\yi;
uu = c(1) + c(2).*xx + c(3).*xx.^2;
uu1 = c(1) + c(2) + c(3);
plot(xx,uu,'b')
res1 = abs(yi - H*c);
e1 = sum(res1.^2);

H = [xi.^0 sin(xi) cos(xi).^2];
c = H\(yi-3.*xi);
tt = c(1) + 3.*xx + c(2).*sin(xx) + c(3).*cos(xx).^2;
tt1 = c(1) + 3 + c(2)*sin(1) + c(3)*cos(1).^2;
hold on, plot(xi,yi,'r*',xx,tt,'g'), hold off
res2 = abs(yi - H*c);
e2 = sum(res2.^2);

% Se ajusta mejor en el punto (0,27) produciendo un error igual a 0.2193

y = 34;
erel_uu = (abs(y-uu1)/abs(y))*100;
erel_tt = (abs(y-tt1)/abs(y))*100;

% El que producirá un menor error en los puntos de la tabla será u(x), ya
% que al efectuar interpolación el error es nulo en dichos puntos


