% ECUACIONES NO LINEALES

% JULIO20
% Ecuación x-log(x)=2 en [3,4]
% Función f(x) = x-log(x)-2 -> comprobar que tiene una raíz en [3,4]
% Aplicamos con el Teorema de Bolzano para ver si hay al menos una raíz en
% este intervalo
f(3), f(4), existeRaiz = f(3)*f(4)<0
% Como f(3)*f(4)<0, entonces por el teorema de Bolzano existe, al menos, una
% raíz en el intervalo [3,4]

% Método de Newton --> x(n+1) = x(n) - f(x(n))/fp(x(n))
x=zeros(8,1);
x(1) = 3;
for n=1:7
    x(n+1) = x(n) - f(x(n))/fp(x(n));
end
s = x(end);
% Comprobamos que s es raíz de la función, es decir, que f(s) = 0
esRaiz = f(s) == 0

Erel = abs((x-s)./s);
Ncifras = floor(-log10(Erel));

% Debemos seleccionar los valores de los vectores x, Erel y Ncifras del 2:8
% ya que x(1) es el valor inicial x0 desde el que parto, por lo que las
% iteraciones de la 1 a la 7 están desplazadas una posición en cada uno de
% los vectores

subplot(131), plot(1:7, x(2:8), 'bs:')
subplot(132), semilogy(1:7, Erel(2:8),'bo:')
subplot(133), plot(1:7, Ncifras(2:8),'r*:')

% Necesitamos 4 iteraciones para alcanzar la precisión de la máquina, ya
% que al no mostrar más valores en las gráficas correspondientes al error y
% el número de cifras significativas de precisión, entonces ésta no mejora

% Como entre cada par de iteraciones el número de cifras significativas se
% duplica aproximadamente, entonces el orden de convergencia es cuadrático

% Necesitamos 2 iteraciones para alcanzar un mínimo de 5 cifras
% significativas de precisión, ya que con una sola iteración tan solo
% conseguimos 3 cifras significativas de precisión correctas

% s y x(4) contienen un total de 15 cifras significativas de precisión
% correctas coincidentes tal y como podemos observar en la gráfica 

fprintf("s = \t%.18f\nx(4) = \t%.18f\n", s,x(4))

% ----------------------------------------------------------------------- %

% JULIO19
clc,clear

% Apartado a) 
A = pi;
x = ones(1,21); % En este caso ya tengo x(1) = 1
for k=1:20
    x(k+1) = (2*x(k) + A/x(k)^2) / 3;
end
s = x(end);

subplot(111), plot(1:20,x(2:21),'bo:')

% La sucesión es convergente hacia el valor 1.4646
% Comprobamos que s es la raíz cúbica de A
esRaiz = s == A^(1/3)
% Otra posibilidad habría sido poner s - A^(1/3) == 0

Erel = abs((x-s)./s);
% Los errores relativos (y absolutos) deben representarse con un eje
% logarítmico en las y's (semilogy). Si el eje x también fuera logarítmico
% entonces necesitaríamos un loglog para el dibujo
subplot(211), semilogy(1:20, Erel(2:21),'bo:')

Ncifras = floor(-log10(Erel));
% El número de cifras significativas debe representarse con un eje normal
% en las y's (plot). Si el eje x fuera logarítmico entonces necesitaríamos
% un semilogx para el dibujo 
subplot(212), plot(1:20,Ncifras(2:21), 'r*:')

% En la iteración 4 se obtienen 6 cifras de precisión correctas, en la
% iteración 5 se obtienen 13 cifras de precisión correctas, y la iteración
% 6 no aparece ya que se habrá alcanzado la raíz exacta o alcanzado la
% precisión de la máquina. Por ello, el tipo de convergencia es cuadrática.

% Apartado b) 
clc,clear 

A = 0:0.01:2;
% En este caso necesitamos matrices para resolver el problema. 
% En cada fila situaremos una iteracion diferente, teniendo en la ultima de
% ellas el valor final obtenido para cada uno de los valores del vector A
x = ones(6,length(A)); % En este caso ya tengo x(1,:) = 1
for k=1:6
    x(k+1,:) = (2.*x(k,:) + A./x(k,:).^2) ./ 3;
end
s = x(end,:);

% Otra opción equivalente sería hacer dos bucles anidados e ir
% rellenándolos posición a posición
x1 = ones(6,length(A)); % En este caso ya tengo x(1,:) = 1
for k=1:6 % fila k-ésima
    for n = 1:length(A) % columna n-ésima
        x1(k+1,n) = (2*x1(k,n) + A(n)/x1(k,n)^2) / 3;
    end
end
s1 = x1(end,:);

Erel = abs((s-A.^(1/3))./A.^(1/3));
Ncifras = floor(-log10(Erel));

subplot(111)
plot(A,s,'b:')
plot(A,Ncifras, 'r*:')
% Se obtiene una menor precisión numérica para A = 0

% ----------------------------------------------------------------------- %

% JULIO18
clc,clear

x=ones(1,21); % ya tenemos x0=1
for k=1:20
    x(k+1) = x(k)+(33*2^(-x(k))-1)/log(2);
    error = abs(x(k+1)-x(k));
    fprintf("Valor iteración %.16f Valor del error %.4e\n",x(k),error)
end
s = x(end) 
% La sucesión converge al valor 5.0443941193584534
plot(1:20,x(2:21),'bo:')

Erel = abs((x-s)./s);
semilogy(1:20, Erel(2:21),'bo:')

Ncifras = floor(-log10(Erel));
plot(1:20,Ncifras(2:21),'r*:')

% Se obtienen un total de 12 cifras significativas de precisión correctas
% en un total de 18 iteraciones. Al principio el tipo de convergencia es
% lineal (hasta la iteración 15), a partir de la cual el tipo de
% convergencia comienza a ser cuadrático, ya que empiezan a duplicarrse,
% aproximadamente, el número de cifras significativas de precisión. 

% Debemos despejar x de la ecuación 2^-x - 33 = 0 -> 2^-x = 33 
% -x * log(2) = log(33) -> -x = log(33)/log(2) -> x = log(33)/log(2)

esRaiz = s == log(33)/log(2)

% Ahora debemos modificar la función anterior para obtener el resultado de
% la raíz de f(x) = 2^-x - 35 = 0

% En este caso debemos modificar el método iterativo sustituyendo el 33 por
% un 35, es decir, x(k+1) = x(k)+(35*2^(-x(k))-1)/log(2);
x=ones(1,21); % ya tenemos x0=1
for k=1:20
    x(k+1) = x(k)+(35*2^(-x(k))-1)/log(2);
    error = abs(x(k+1)-x(k));
    fprintf("Valor iteración %.16f Valor del error %.4e\n",x(k),error)
end
s = x(end);
fprintf('s = %.16f\n', s)

% En este caso la solución de la ecuación es s = 5.1292830169449664

% ----------------------------------------------------------------------- %

% JULIO17
clc,clear

% Función -> f(x) = x^2 - exp(-x)
% Apartado a) 
intervalo = -2:0.01:2;
ff = intervalo.^2 - exp(-intervalo);
plot(intervalo, ff, 'b', intervalo, ff.*0, 'k')
% Un posible intervalo sería [0.5,1] Comprobamos analíticamente que en
% dicho intervalo existe una raíz aplicando el Teorema de Bolzano
ff05 = 0.5^2 - exp(-0.5);
ff1 = 1^2 - exp(-1);
existeRaiz = ff05*ff1 < 0;
% Como f(0.5)*f(1) < 0, entonces por el teorema de Bolzano existe, al
% menos, una raíz en dicho intervalo.

% Apartado b) 
error = 1;
x = 1;
iter = 0;
while error>=1e-10
    iter = iter + 1;
    x_ant = x;
    x = sqrt(exp(-x_ant));
    error = abs(x - x_ant);
    fprintf("Iter %d Sol %.15f Error %e\n",iter,x,error)
end

fprintf("\n\n") % Añado un par de saltos de línea para diferenciar entre
                % un método iterativo y el siguiente
% Apartado c)
a=0.5; b=1;
error = 1;
iter = 0;
z=a; x=b; 
while error >= 1e-10
    iter = iter+1;
    z = z - (z^2 - exp(-z))/(2*x + exp(-x));
    x = x - (x^2 - exp(-x))/(2*x + exp(-x));
    error = abs(x-z);
    fprintf("Iter %d Sol %.15f Error %e\n",iter,x,error)
end

% Podemos observar que el primer método tiene un orden de convergencia
% lineal, ya que para alcanzar una nueva cifra significativa de precisión
% necesitamos aproximadamente unas dos iteraciones. 
% Mientras que el segundo método iterativo tiene un orden de convergencia
% cuadrático, ya que entre cada par de iteraciones se duplica
% aproximadamente el número de cifras significativas de precisión correctas

% ----------------------------------------------------------------------- %

% JULIO16
clc,clear

% Apartado a) 
aprox_x = zeros(1,101);
aprox_x(1) = 7/4;
for n=1:100
    aprox_x(n+1) = 1 + 1/aprox_x(n) + 1/aprox_x(n)^2;
end
s = aprox_x(end);

plot(1:100, aprox_x(2:101), 'b.')

% La sucesión converge hacia el valor 1.8393

% Apartado b) 
Erel = abs((aprox_x-s)./s);
semilogy(1:100,Erel(2:101),'bo:')

% Apartado c) 
Ncifras = floor(-log10(Erel));
plot(1:100, Ncifras(2:101),'r*:')

% Necesitamos un total de 66 iteraciones para alcanzar 15 cifras
% significativas de precisión correctas
% Se necesitan aproximadamente unas 4/5 iteraciones para alcanzar una nueva
% cifra de precisión correcta

% ----------------------------------------------------------------------- %

% JULIO15
clc,clear

% f(x) = exp(2*x) - x - 6

% Apartado a) 
% Queremos encontrar un intervalo de longitud menor a 0.5 donde se
% encuentre la raíz de la función
x = 0:0.01:2;
ff = g(x);
plot(x,ff,'b',x,ff*0,'k')
% Un posible intervalo en el que se encuentra la raíz sería [0.8, 1.2] 

% Apartado b) 
sb = biseccion(@g, 0.8, 1.2, 0, 50);
% La raiz aproximada es 0.97087002

% Apartado c) 
iter = 0; tol = 1;
x = 0.5;
e = zeros(1,8); % Apartado d)
while iter<=50 && tol>=1e-16
    iter = iter+1;
    x_ant = x;
    x = ((2*x_ant-1)*exp(2*x_ant)+6)/(2*exp(2*x_ant)-1);
    tol = abs(x-x_ant);
    error = abs(x-sb);
    e(iter) = error; % Apartado d)
    fprintf("Iteración: %d Raíz: %.15f Error: %.15f\n", iter, x, error)
end

% Apartado d) 
semilogy(1:8, e, 'bo:')
% El orden de convergencia es cuadrático ya que entre cada par de
% iteraciones se duplican aproximadamente el número de cifras
% significativas de precisión correctas

% ----------------------------------------------------------------------- %
% FUNCIONES AUXILIARES -> DEBEN situarse DEBAJO del todo del SCRIPT en el
% que estamos trabajando, las operaciones y llamadas a la funcion deben
% estar arriba

% Si queremos trabajar con las funciones en varios scripts diferentes,
% entonces el script de la función debe llamarse exactamente igual que la
% función (por ejemplo, si nuestra funcion es f(x), el script se deberá
% llamar f.m; si la función es fp(x), el script se deberá llamar fp.m) y
% encontrarse en el mismo directorio de trabajo (OBLIGATORIO)
% ----------------------------------------------------------------------- %

% Julio20
function y = f(x)
    y = x-log(x)-2;
end

function y = fp(x)
    y = 1 - 1./x; % 1./x si es necesario aplicarlo sobre un vector, si no el . no es necesario
end

% Julio15
function y = g(x) 
    y = exp(2.*x) - x - 6;
end
