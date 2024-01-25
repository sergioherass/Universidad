%%CLASE 6
%Julio2020
clc,clear
f(3)*f(4);
%como f(3)*f(4) es negativo entonces por el teorema de bolzano 
%podemos afirmar que la funcion tiene al menos una raiz en [3,4]
x=zeros(8,1);
x(1)=3;
for n=1:7
    x(n+1)=x(n)-f(x(n))/fp(x(n));
end
s=x(end)
%ahora comprobamos que s es raiz de la funcion, f(s)=0
f(s) %luego sí es raiz

Erel=abs((x-s)./s);
ncif=floor(-log10(Erel))

% subplot(131);plot(1:7,x(2:8),'bs:');
% subplot(132);semilogy(1:7,Erel(2:8),'bo:');
% subplot(133);plot(1:7,ncif(2:8),'r*:');

fprintf('s=%.18f x(4)=%.18f',s,x(4));

%julio2019
clc,clear

x=zeros(1,21);
x(1)=1;
A=pi;
for k=1:20
    x(k+1)=(2*x(k)+A/x(k)^2)/3;
end
s=x(end)
subplot(111);plot(1:20,x(2:21),'bo:');

s==A^(1/3)

Erel=abs((x-s)./s);
%subplot(211);semilogy(1:20,Erel(2:21),'bo:');
ncif=floor(-log10(Erel));
%subplot(212);plot(1:20,ncif(2:21),'r*:');

clc,clear
A=[0:0.01:2];
x=zeros(6,length(A));
x(1,:)=1;
for k=1:6
    x(k+1,:)=(2.*x(k,:)+A./x(k,:).^2)./3;
end
s=x(end,:)

Erel=abs((A.^(1/3)-s)./A.^(1/3));
Ncif=floor(-log10(Erel));

hold on;plot(A,s,'b:')
plot(A,Ncif, 'r*:');hold off;

%julio2018
clc,clear

x=zeros(1,21);
x(1)=1;
for k=1:20
    x(k+1)=x(k)+(33*2^(-x(k))-1)/log(2);
    error=abs(x(k+1)-x(k));
    fprintf('\nValor iteracion %.16f Valor del error %.4e',x(k),error)
end
s=x(end);
plot(1:20,x(2:21));

Erel=abs((x-s)./s);
semilogy(1:20,Erel(2:21));
Ncif=floor(-log10(Erel))
plot(1:20,Ncif(2:21));
s== (log10(33)/log10(2))

clc,clear
x=zeros(1,21); 
x(1)=1;
for k=1:20
    x(k+1) = x(k)+(35*2^(-x(k))-1)/log(2);
    error = abs(x(k+1)-x(k));
    fprintf("Valor iteración %.16f Valor del error %.4e\n",x(k),error)
end
s = x(end);
fprintf('s = %.16f\n', s)

%julio2017
clc,clear
% xx=[-2:0.001:2];
% ff=xx.^2-exp(-xx);
% plot(xx,ff);
% %intervalo de [0.5,1.5]
% ff1=0.5^2-exp(-0.5)
% ff2=1.5^2-exp(-1.5)
% %como ff1*ff2 es negativo estonces por el tma de bolzano existe almenos una
% %raiz en ese intervalo
% 
% error=1;
% x=1;
% iter=0;
% while error>=1e-10
%     iter=iter+1;
%     x_ant=x;
%     x=sqrt(exp(-x_ant));
%     error = abs(x - x_ant);
%     fprintf("Iter %d Sol %.15f Error %e\n",iter,x,error)
% end
% 
% 
% error=1;
% a=0.5;
% b=1.0;
% z=a;
% x=b;
% iter=0;
% while error>=1e-10
%     iter=iter+1;
%     z = z - (z^2 - exp(-z))/(2*x + exp(-x)); %la del ejercicio y lo ponemos nosotros la derivada
%     x = x - (x^2 - exp(-x))/(2*x + exp(-x));
%     error = abs(x-z);
%    
%     fprintf("Iter %d Sol %.15f Error %e\n",iter,x,error)
% end

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
    ncif=floor(-log10(error));
    fprintf("Iter %d Sol %.15f Error %e ncif %d\n",iter,x,error,ncif)
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
    ncif=floor(-log10(error));
    fprintf("Iter %d Sol %.15f Error %e ncif %d\n",iter,x,error,ncif)
end

%julio2016
clc,clear
aprox_x=zeros(1,101);
aprox_x(1)=7/4;
for n=1:100
    aprox_x(n+1)=1+1/aprox_x(n)+1/aprox_x(n)^2;
end
s=aprox_x(end);
% subplot(131);plot(1:100,aprox_x(2:101),'.');
Erel=abs((aprox_x-s)./s);
% subplot(132);semilogy(1:100,Erel(2:101));
ncif=floor(-log10(Erel))
% subplot(133);plot(1:100,ncif(2:101));

%julio2015
clc,clear

xx=[0:0.01:2];
ff=exp(2.*xx)-xx-6;
plot(xx,ff,xx,ff*0);

%intervalo de [0.8,1.2]
sb = biseccion(@g, 0.8, 1.2, 0, 50);

tol=1;
x=0.5;
error=1;
iter=0;
e=zeros(1,8);
while iter<=50 && tol>=1e-16
    iter=iter+1;
    x_ant=x;
    x=((2*x_ant-1)*exp(2*x_ant)+6)/(2*exp(2*x_ant)-1);
    tol=abs(x-x_ant);
    error=abs(x-sb);
    e(iter)=error;
    fprintf('Iteracion: %d Raiz: %.15f Error: %.15f\n',iter,x,error);
end

semilogy(1:8,e);
ncif=floor(-log10(e));

%julio2014
clc,clear
x=zeros(1,31);
x(1)=1;
iter=0;
s=sqrt(2);
Eabs=zeros(1,31);
Erel=zeros(1,31);
for n=1:30
    iter=iter+1;
    x(n+1)=(2+x(n))/(1+x(n));
    eabs=abs(s-x(n+1));
    Eabs(n)=eabs;
    erel=eabs./abs(s);
    Erel(n)=erel;
    ncif=floor(-log10(erel));
    fprintf('\nIteracion: %2d Raiz: %.15f Eabs: %.2e Ncif: %2d',iter,x(n+1),Eabs(n),ncif);
end

%b
clc,clear
x=zeros(1,31);
x(1)=1;
iter=0;
s=sqrt(2);
Eabs=zeros(1,31);
Erel=zeros(1,31);
for n=1:30
    iter=iter+1;
    x(n+1)=(x(n)^2+2)/(2*x(n));
    eabs=abs(s-x(n+1));
    Eabs(n)=eabs;
    erel=eabs./abs(s);
    Erel(n)=erel;
    ncif=floor(-log10(erel));
    fprintf('\nIteracion: %2d Raiz: %.15f Eabs: %.2e Ncif: %2d',iter,x(n+1),Eabs(n),ncif);
end

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
    fprintf("\nIteración: %2d x = %.15f Eabs = %.2e Ncifras = %2d\n",iter,x,eabs,ncif);
end

% 
%%CLASE 7
%simulacro julio2020
clc,clear
s1=sqrt(2);
x=zeros(1,21);
Erel = zeros(1,20);
x(1)=1;
iter=0;
for n=1:20
    iter=iter+1;
    x(n+1)=(2+x(n))/(1+x(n));
    erel=abs((s1-x(n+1))/s1);
    Erel(n)=erel;
    fprintf('\nIteracion: %2d Raiz: %.15f Erel: %.2e',iter,x(n+1),erel);
end
s2=x(end);
abs(s1-s2);
semilogy(1:20,Erel)
ncif=floor(-log10(Erel));

%Diciembre 2018
clc,clear
x=zeros(1,6);
x(1)=-2.5;
for n=1:5
    x(n+1)=x(n)-f(x(n))/fp(x(n));
    error=abs(x(n+1)-x(n));
    fprintf('iter: %d Raiz %.15f Error %.4e\n', n, x(n+1), error);
end

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

%julio2013
clc,clear
aprox_x=zeros(1,11);
aprox_x(1)=-2.5;
for n=1:10
    aprox_x(n+1)=aprox_x(n)-(aprox_x(n)^3-3*aprox_x(n)+2)/(3*aprox_x(n)^2-3);
    fprintf('\niter: %d Raiz: %.15f',n,aprox_x(n+1))
end
s1=aprox_x(end)
plot(1:10, aprox_x(2:11),'bo:')
Erel=abs((aprox_x-s1)./s1);
semilogy(1:10,Erel(2:11));
ncif=floor(-log10(Erel))

clc,clear

aprox_x=zeros(1,51);
aprox_x(1)=1.5;
for n=1:50
    aprox_x(n+1)=aprox_x(n)-(aprox_x(n)^3-3*aprox_x(n)+2)/(3*aprox_x(n)^2-3);
    fprintf('\niter: %d Raiz: %.15f',n,aprox_x(n+1))
end
s2=aprox_x(end)
plot(1:50, aprox_x(2:51),'bo:')
Erel=abs((aprox_x-s2)./s2);
semilogy(1:50,Erel(2:51));
ncif=floor(-log10(Erel))

xx=[-2.5:0.01:1.5];
ff=xx.^3-3.*xx+2;
plot(xx,ff,xx,ff*0);

%%enero2012ejer1
clc,clear
%1
xx = 0:0.01:4;
ff = xx.^3 + 4.*xx.^2-10;
plot(xx,ff,'b',xx,ff*0,'k')
%escogemos el intervalo de[1,2]

s = 1.3652300134140969;
x = 1;
for n=1:10
    x = (2*x^3 + 4*x^2+10)/(3*x^2+8*x);
    fx = x.^3 + 4.*x.^2-10;
    erel = abs(x-s)/abs(s);
    ncif = floor(-log10(erel));
    fprintf("Iteración: %2d valor x_k: %.16f valor f(x_k): %.3e Ncifras = %2d\n",n,x,fx,ncif);
end
s1=x(end);
%2
xx=[-2:0.01:4];
gg=exp(xx)-2.*xx.^2;
plot(xx,gg,xx,gg*0)

s = 2.6178666130668127; % raiz s3
x = 3;
for n=1:10
    x = ((x-1)*exp(x) - 2*x^2) / (exp(x)-4*x);
    fx = exp(x)-2*x^2;
    erel = abs(x-s)/abs(s);
    ncif = floor(-log10(erel));
    fprintf("Iteración: %2d valor x_k: %.16f valor f(x_k): %.3e Ncifras = %2d\n",n,x,fx,ncif);
end
s1=x(end)

%enero2012ejer2
clc,clear
r=3;
K=1;
x=zeros(1,11);
R=zeros(1,11);
x(1)=1;
for n=1:10
    x(n+1)=r/(1+x(n)*K);
     R(n) = x(n+1); 
    fprintf('\nIter: %d Raiz: %.15f',n,x(n+1));
end
s=x(end)


xx=[0:0.01:3];
RR=r./(1+xx.*K);
plot(x(2:10),R(1:9),'rs',x(11),R(10),'gs',xx,RR,'b')


r=3;
K=1;
x=zeros(1,71);
R=zeros(1,71);
x(1)=1;
for n=1:70
    x(n+1)=r/(1+x(n)*K);
     R(n) = x(n+1); 
    fprintf('\nIter: %d Raiz: %.15f',n,x(n+1));
end
s=x(end)
erel=abs((x-s)./s);
ncif=floor(-log10(erel))
plot(1:70, ncif(2:71), 'r*')


r=3;
K=1;
x=zeros(1,71);
S=zeros(1,71);
x(1)=1;
for n=1:70
    x(n+1)=r/(1+(x(n)/K)^2);
     S(n) = x(n+1); 
    fprintf('\nIter: %d Raiz: %.15f',n,x(n+1));
end
s=x(end)
erel=abs((x-s)./s);
ncif=floor(-log10(erel));
plot(1:70, ncif(2:71), 'r*');

%julio2012
clc,clear
xx=[2:0.01:10];
ff=cos(xx)./sqrt(xx);
plot(xx,ff,xx,ff*0);
%intervalo [4,9]o [4,8]

%enero2011
clc,clear
xx=[-4:0.01:8];
ff=xx.^3-3.*xx+2;
plot(xx,ff,xx,ff*0);


% x=zeros(1,5);
% x(1)=-2.5;
% for k=1:4
%     x(k+1)=(7*x(k)^4+21*x(k)^3+24*x(k)^2+14*x(k)+6)/(9*(x(k)+1)^3);
%     error=abs(x(k)-x(k+1));
%     fprintf('\nIter: %d Raiz: %.15f error: %.4e',k,x(k+1),error);
% end
% s1=x(end)

iter=0;
x=-2.5;
s1 =-2.0000
for k=1:4
    iter=iter+1;
    x_ant=x;
    x=(7*x_ant^4+21*x_ant^3+24*x_ant^2+14*x_ant+6)/(9*(x_ant+1)^3);
    error=abs(x-x_ant);
    Erel=abs(x-s1)./abs(s1);
    ncif=floor(-log10(Erel));
    fprintf('\nIter: %d valor: %.15f error: %.4e Ncif: %d',iter,x,error,ncif);
end

%LAb10dic examen 1 
clc,clear
s=sqrt(5);
x=zeros(1,10);
x(1)=2;
Eabs=zeros(1,10);
error=zeros(1,10);
for n=1:9
    x(n+1)=(5+x(n))/(1+x(n));
    eabs=abs(x(n+1)-s);
    Eabs(n)=eabs;
    e = abs(x(n)-s);
    error(n)=e;
    fprintf('\nn=%2d x=%.16f e=%.2e s=%.16f',n,x(n),error(n),s);
end
semilogy(1:9,Eabs(2:10))
% 
 Erel = abs((x-s)./s);
 Ncifras = floor(-log10(Erel))
 plot(1:9,Ncifras(2:10))
for n=1:9
    k=error(n+1)/error(n)
end
%si con 9 iteraciones tengo 4 cifras decimales, para llegar a 15 cifras
%necesito unas 34 iteraciones aproximadamente
fprintf('\n')
x2=zeros(1,10);
x2(1)=2;
Eabs2=zeros(1,10);

s1=sqrt(5);
for n=1:9
    x2(n+1)=(5+x2(n))/(1+x2(n));
    if rem(n,2)==0
        s1=x2(n+1)-((x2(n+1)-x2(n))^2)/(x2(n+1)-2*x2(n)+x2(n-1));
    end
        
    eabs2=abs(x(n+1)-s1);
    Eabs2(n)=eabs;
    e(n) = abs(x(n)-s1);
   
    fprintf('\nn=%2d x=%.16f e=%.2e s1=%.16f ',n,x2(n),e(n),s1);
end
hold on;semilogy(1:9,Eabs2(2:10));hold off;

Erel2 = abs((x-s1)./s1);
Ncifras2 = floor(-log10(Erel2));
hold on;plot(1:9,Ncifras2(2:10));hold off;

%es igual??%

%Examen2 
clc,clear
% x=zeros(1,16);
% x(1)=2;
% s=power(6,1/3);
% Eabs=zeros(1,16);
% for n=1:15
%     x(n+1)=sqrt(6/x(n));
%     eabs=abs(x(n+1)-s);
%     Eabs(n)=eabs;
%   
%     fprintf('\nIter %2d :: x=%.15f -> Eabs=%.2e',n,x(n+1),Eabs(n));
% end
% 
% Erel=abs((x-s)./s);
% Ncif=floor(-log10(Erel))
% plot(1:15,Ncif(2:16))
% 
% 
% 
% for n=1:14
% K=abs(x(n+2)-x(n+1))/abs(x(n+1)-x(n))
% end
% %convergencia lineal 
% %vel=0,33 cif/iter
% 

%Lab3dic
clc,clear
s = 1.839286755214161;
x=zeros(1,11);
x(1)=1.8;
Erel=zeros(1,11);
for n=1:10
   % x(n+1)=power(1+x(n)+x(n)^2,1/3);
    x(n+1)=x(n)-(x(n)^3-x(n)^2-x(n)-1)/(3*x(n)^2-2*x(n)-1);
    erel=abs((x(n+1)-s)./s);
    Erel(n)=erel;
    fprintf('\nIter %d :: x=%.16f -> Erel=%.2e',n,x(n+1),Erel(n));
end
semilogy(1:10,Erel(2:11))
%convergencia lineal
erel=abs(x-s)/abs(s);
ncif=floor(-log10(erel))
%ganamos una cifra decimal cada 3 iteraciones
%25 iteraciones para tener 10 cifras significativas

%ahora necesitamos 4 iteraciones

%Para calcular la K del primer apartado

% ratio=erel(2:11)./erel(1:10)
%  K=erel(end)/erel(end-1)
%  cif=floor(-log10(erel))

%Para calcular la K del segundo aparatdo

ratio=erel(2:11)./erel(1:10)
 K=erel(end)/erel(end-1)^2
 cif=floor(-log10(erel))


%ejercicio5
clc,clear
x=zeros(1,71);
x(1)=1;
for n=1:70
    x(n+1)=exp(-x(n));
end
s=x(end);
eabs=abs(x-s);
semilogy(1:70,eabs(2:71),'bo:')
%27 iteraciones

ratio=eabs(2:71)./eabs(1:70)
K=eabs(end)/eabs(end-1)
cif=floor(-log10(eabs))



x=zeros(1,71);
x(1)=1;
for n=1:70
    x(n+1)=x(n)-(x(n)-exp(-x(n)))/(1+exp(-x(n)));
end
s=x(end);
eabs=abs(x-s);
hold on;semilogy(1:70,eabs(2:71),'ro:');hold off;
%27 iteraciones

ratio=eabs(2:71)./eabs(1:70)
K=eabs(end)/eabs(end-1)
cif=floor(-log10(eabs))

K2=eabs(2:4)./eabs(1:3)

fprintf('x(7)=%.100f',x(7))

%examen Tibe
clc,clear
%a
xx=[1:0.01:2];
ff=xx.^3+4.*xx.^2-10;
plot(xx,ff,xx,ff*0);
ff1=1.^3+4.*1.^2-10;
ff2=2.^3+4.*2.^2-10;
ff1*ff2<0 % Luego por el tma de bolzano existe almenos una razi en el intervalo 1 2
% 
%b
% x=zeros(1,21);
% x(1)=1.5;
% for n=1:20
%     x(n+1)=sqrt(10-x(n)^3)/2;
%     fprintf('iteracion %d Raiz %.16f\n',n,x(n))
% end


%c
iter=0;
x=1.5;
e=1;
s=1.3652300134139224
while e>=1e-12
    iter=iter+1;
    x_ant=x;
    x=sqrt(10-x_ant^3)/2;
    e=abs(x-x_ant);
    error(iter)=e;
    errorreal=abs(x-s);
    er(iter)=errorreal;
    
    fprintf('Iteracion %d Raiz %.16f Error estimado %e Error real %e\n',iter,x,e,errorreal);
end
s=x(end)
fprintf('s=%.16f',s)
semilogy(1:41,error)
%aproximadamente 34 iteraciones
%lineal ya que por cada par o cada tres iteraciones aproximadamente se va
%aumentando una cifra 

k=abs(error(5)/error(4))
%2 o 3 iteraciones aproximadamente
%d
hold on;semilogy(1:41,er);hold off;
%el error en la segunda grafica es menor en cada iteracion comparando con
%la otra

%enero2012wuolah
clc,clear
%eje1.1
xx=[-4:0.01:4];
ff=xx.^3+4.*xx.^2-10;
plot(xx,ff,xx,ff*0); %intervalo [1,2]

x=zeros(1,11);
x(1)=1;
for n=1:10
    x(n+1)=(2*x(n)^3+4*x(n)^2+10)/(3*x(n)^2+8*x(n));
    fprintf('\nIteracion: %d valor x_k: %.16f valor f(x-k): %.2e',n,x(n),t(n))
    
end
s=x(end)
Erel=abs(x-s)./abs(s);
ncif=floor(-log10(Erel)) %5 iteraciones
%en la iter 1 hay 1 cifra
%cuadratica .En cada iteracion se duplica el num de cifras significativas
%,luego la vel de convergencia es cuadratica

%ejer1.2
clc,clear
xx=[-4:0.01:4];
gg=exp(xx)-2.*xx.^2;
plot(xx,gg,xx,gg*0);

x=zeros(1,10);
x(1)=3;  %s =-0.5398 x=0
         %s=1.4880 x=1
         %s= 2.6179 x=3
for n=1:10
    x(n+1)=(exp(x(n))*(x(n)-1)-2*x(n)^2)/(exp(x(n))-4*x(n));
end
s=x(end)
Erel=abs(x-s)./abs(s);
ncif=floor(-log10(Erel))

%lineal
........................................................................................................................................................................................................................................................................................

function y=f(x)
y=x-log(x)-2;
end
function y = fp(x)
    y = 1 - 1./x;
end
function y = g(x) 
    y = exp(2.*x) - x - 6;
end
function y=h(x)
    y=cos(x)/sqrt(x);
end

function y=t(x)
    y=x^3+4*x^2-10;
end



