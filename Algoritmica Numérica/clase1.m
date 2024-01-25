% ---------------------------------------------------------------------- %
%                   TEST COMPUTACIONAL SOBRE MATLAB
% ---------------------------------------------------------------------- %

% Ejercicio 1
x = pi; % Con ; tras una sentencia, oculta el resultado en la consola
v_ex = x*cos(x); % Con nada o , tras una sentencia, entonces muestra el resultado por consola
n = 0:12; % Se puede crear a:s:b, donde a y b son los extremos y s es el salto (por defecto s=1)
v_aprox = sum(((-1).^n./factorial(2.*n)).*x.^(2.*n+1));
fprintf("Valor exacto %15.12f Valor aproximado %15.12f\n",v_ex,v_aprox)

% Para el tipo de datos que podemos mostrar por pantalla (consola) tenemos
% d --> números enteros
% f --> números reales
% e --> números exponenciales 
% a.b --> donde a es el número de enteros que muestra y b es el número de
%       decimales que se muestran

% Ejercicio 2
clc, clear  % Con el comando clc limpiamos la consola de comandos
            % Con el comando clear limpiamos las variables (workspace)
n = 0:15;
x = 10.^n;
ff = f(x);
gg = g(x);
semilogy(n,ff,'b', n,gg,'g')


% Otra opción sería superponer una gráfica a otra previa (hold on; hold off)
% semilogy(n,ff,'b'), hold on, semilogy(n,gg,'g'), ...(todas las gráficas que queramos), 
% hold off

% Para las gráficas tenemos varios estilos y colores:
% Colores: g(verde), b(azul - por defecto), c(cyan), r(rojo), y(amarillo),
% k(negro), m(magenta)
% Estilos: : linea discontinua, - linea continua (por defecto), s square
% (cuadrados), o círculos, . puntos gordos

% ---------------------------------------------------------------------- %
%           EJERCICIO COMPUTACIONALES SOBRE MATLAB
% ---------------------------------------------------------------------- %

% Ejercicio 1
clc, clear
x = rand(1,10); % rand(a,b) crea valores aleatorios entre 0 y 1 
                % donde a = num rows y b = num cols

% Usando bucles for y sentencias if 
for n=1:length(x) % El primer índice de un vector es 1
    if x(n)>=0.5
        x(n) = 0.8;
    else
        x(n) = 0;
    end
end

% Repetimos usando indexado lógico
x2 = rand(1,10);
x2(x>=0.5) = 0.8;
x2(x<0.5) = 0;
% abs(x) calcula el valor absoluto de x -> no necesito usarlo

% Ejercicio 2

x=-2:2; % Donde lo quiero pintar
n=1:4;

h  = 1./(1+abs(x).^1)
h2 = 1./(1+abs(x).^2)
h3 = 1./(1+abs(x).^3)
h4 = 1./(1+abs(x).^4)
plot(x,h,x,h2,x,h3,x,h4)
legend('n=1','n=2','n=3','n=4')
% Lo hacemos utilizando subplots
% subplot(a,b,n) sirve para dividir el cuadrante en a filas y b columnas
% siendo n el cuadrante en el que nos situamos
subplot(221), plot(x,h), legend("n=1")
subplot(222), plot(x,h2), legend("n=2")
subplot(223), plot(x,h3), legend("n=3")
subplot(224), plot(x,h4), legend("n=4")

% Ejercicio 3
clc,clear
x=0.5;
vexact = sin(x);
n=0:29;
% Apartado a): punto a punto
vaprox1 = sum(((-1).^n.*x.^(2.*n+1))./(factorial(2.*n+1)));
% Apartado b): bucle for
vaprox2 = zeros(1,30); % Me creo un vector a 0's con una fila y 30 columnas
% Otra posibilidad sería crear un vector a 1's con una fila y 30 columnas
% (ones(1,30))
for i=0:29
    vaprox2(i+1) = ((-1)^i*x^(2*i+1))/(factorial(2*i+1));
    suma = sum(vaprox2(1:30));
    fprintf("Iteración %d Término %e Suma %5.3f\n", i+1,vaprox2(i+1),suma)
end
% Apartado c): bucle while
iter = 0;
vaprox3 = ((-1)^iter*x^(2*iter+1))/(factorial(2*iter+1));
suma = 0;
while abs(vaprox3)>1e-18 % 1e-18 es equivalente 1x10^-18
    suma = suma + vaprox3;
    iter = iter+1;
    vaprox3 = ((-1)^iter*x^(2*iter+1))/(factorial(2*iter+1));
end
% En este caso necesitamos un total de 8 iteraciones 


% ---------------------------------------------------------------------- %
%   Introducción a Matlab: crear/indexar/operar con matrices. Gráficos
% ---------------------------------------------------------------------- %

% APARTADO A

% Ejercicio 1
clear, clc
A1 = [3:3:27; 5:5:45; 7:7:63];
x = 1:9;
A2 = [x*3; x*5; x*7]
fila1 = A2(1,:); % Con : en un vector extraemos todos los valores de la fila/columna deseada
colFinal = A2(:,end)
B = A2(1:2:end, 3:3:end)
whos A2, whos B
% La matriz a es de 3x9 y ocupa 216 bytes
% La matriz B es de 2x3 y ocupa 48 bytes -> cada número ocupa 8 bytes (1 bit)
C = A2(end:-1:1, 4:2:8)

% ---------------------------------------------------------------------- %

% APARTADO B. OPERACIONES LÓGICAS

% Ejercicio 1
clc,clear
x = [-1 0 1 -5 4 7 -5 9 3.5];
x(x>0);
x(x<=0)=0;
x(x>=5)=x(x>=5)+2;
y=x;
for n=1:length(x)
    if x(n)>=mean(x)
        y(n) = 1;
    else
        y(n) = 0;
    end
end
z=x(x>=3);

% Ejercicio 2
clc,clear
nota = 5.5+1.2*randn(1,70)
subplot(111), plot(nota,'.') % Como previamente tenía un subplot de 2 filas y columnas, 
% entonces tengo que volver a tener una única fila y una única columna
notaMedia = mean(nota)
notaMaxima = max(nota)
notaMediaAprobados = mean(nota(nota>=5))
length(nota(nota>=5))
length(nota(nota>=9))
nota(nota>=4.5 & nota<5) = 5

% ---------------------------------------------------------------------- %

% APARTADO C. OPERACIONES PUNTO A PUNTO

% Ejercicio 1
clc,clear
x = 0:0.01:2*pi;
ff = sin(x);
gg = x.*cos(x);
% Lo pintamos superponiéndolos
plot(x,ff,'r',x,gg,'b')
% Lo pintamos con un subplot (dividiendo la gráfica)
subplot(211), plot(x,ff,'b')
subplot(212), plot(x,gg,'b')

% Ejercicio 2
clc,clear
x = -1:0.01:1;
f1 = sin(pi.*x);
f2 = (1./(1+x.^2)) + (abs(x)./(2+abs(x)));
f3 = exp(-abs(x.^3));

subplot(111), plot(x,f1,'b',x,f2,'g',x,f3,'r')

plot(x,f1,'b')
hold on, plot(x,f2,'g')
plot(x,f3,'r'), hold off

% Ejercicio 3
clc,clear
x = 0:0.01:200;
ff = exp(-x);
semilogy(x,ff,'b')

% Ejercicio 4
clc,clear
x = linspace(0,1,100); % linspace(a,b,n) crea n puntos equidistantes entre sí
                      % en el intervalo [a,b]
f1 = x;
f2 = x.^2;
f3 = sin(pi.*x);

% Lineal en ambos ejes
subplot(221), plot(x,f1,'b')
subplot(222), plot(x,f2,'b')
subplot(223), plot(x,f3,'b')

% semilogy
subplot(221), semilogy(x,f1,'b')
subplot(222), semilogy(x,f2,'b')
subplot(223), semilogy(x,f3,'b')

% semilogx
subplot(221), semilogx(x,f1,'b')
subplot(222), semilogx(x,f2,'b')
subplot(223), semilogx(x,f3,'b')

% loglog
subplot(221), loglog(x,f1,'b')
subplot(222), loglog(x,f2,'b')
subplot(223), loglog(x,f3,'b')

% Ejercicio 5
clc,clear
x = 0:0.01:1;
ff = sinh(x);
gg = (exp(x)-exp(-x))/2;
subplot(211), plot(x,ff,'b.',x,gg,'r.'), legend('sinh(x)','(exp(x)-exp(-x))/2')

y = 10.^-(0:16);
ff = sinh(y);
gg = (exp(y)-exp(-y))/2;
error = abs(ff-gg)./abs(ff);
subplot(212), loglog(y,error,'bo'), legend('Error relativo')

% La primera gráfica no se ve ya que la segunda se superpone por completo, 
% por lo que ambas funciones deben tomar valores prácticamente iguales

% ---------------------------------------------------------------------- %
%                       FUNCIONES AUXILIARES
% ---------------------------------------------------------------------- %

% Las funciones auxiliares, si quereis tenerlas en el script SIEMPRE deben
% estar al final de todo el script
% Si las ponéis en un script aparte, necesitamos que esté en la misma
% carpeta de trabajo

% ---------------------------------------------------------------------- %

% Funciones auxliares Test Computacional, ejercicio 2
function y = f(x)
y = log(x+1)-log(x);
end

function y = g(x)
y = log(1+1./x);
end

% ---------------------------------------------------------------------- %

% Función auxliar Ejercicios Computacional, ejercicio 2
function y = h(x,n)
y = 1./(1+abs(x).^n);
end


