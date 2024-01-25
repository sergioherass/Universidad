% Ejercicios Ecuaciones no lineales

esRaiz = fun1(0)*fun1(1)<0;
% Como fun1(0)*fun1(1)<0, entonces por el Teorema de Bolzano existe, al
% menos, una ra�z en el intervalo [0,1]

% Ejercicio 1
s1=biseccion(@fun1,0,1,1e-8,5);
fprintf("\n\n");
s2=biseccion(@fun1,0,1,1e-8,10);
fprintf("\n\n");
s3=biseccion(@fun1,0,1,1e-8,30);
fprintf("\n\n");

% Ejercicio 2
s=newton(@fun1,0);
fun1(s); % Como el resultado es 0, entonces hemos
         % encontrado la ra�z de la funci�n 
fprintf("\n\n");
         
% Ejercicio 3
s=secante(@fun1,0,1,1e-8);
fprintf("\n\n");

% Ejercicio 4
s1 = miraiz1(1,2);
fprintf("\n\n");
s2 = miraiz2(1,2);
fprintf("\n\n");

% En un principio ambos m�todos presentan una convergencia cuadr�tica ya
% que entre cada par de iteraciones se duplica aproximadamente el n�mero de
% cifras significativas de precisi�n correctas; pero el segundo de ellos
% alcanza la ra�z exacta en apenas 6 iteraciones (al producir un error
% igual a 0), mientras que el primero de ellos se estanca en la quinta
% iteraci�n sin llegar a encontrar la ra�z exacta del problema. Por todo
% ello, el m�todo que seleccionar�a ser�a el segundo de los dos.

% Ejercicio 5
x = 0:0.01:2;
y = -1-x+x.^3;
plot(x,y,'b',x,y*0,'k')
% En el intervalo [0,2] nos encontramos una �nica ra�z. Un posible
% intervalo de longitud igual a 0.5 ser�a [1,1.5]

s = ejerc_5(1,1e-5,10);
f(s)

% FUNCIONES AUXILIARES

 function [f,fp]=fun1(x)
    % Devuelve el valor de la funci�n cuyo raiz se busca en x
    % Opcionalmente devuelve el valor de su derivada.
    f = x - cos(x); % Funci�n a evaluar f(x) = x-cos(x)
    if nargout==1, return; end % No se requiere derivada
    
    fp = 1+sin(x); % Si nos lo piden calculamos derivada en x
    return
 end
 
 % Ejercicio 2
 function s=newton(fx,x0,n_max)
    % Entrada:  fx nombre de la funci�n (debe devolver f(x) y f'(x))
    %           x0 punto de arranque
    %           n_max es el numero maximo de iteraciones
    
    % Salida:   s es la aproximaci�n de la raiz
    
    if nargin==2, n_max=10; end % Valor por defecto de n_max
    
    % Asignar n_max = minimo entre n_max y el n�mero necesario para
    % alcanzar la tolerancia
    
    x=x0; % Inicio
    for k=1:n_max
        [f, fp]=feval(fx,x); % Obtengo valores de f(x) y f'(x)
        if f==0, break;end % Ya estoy en la raiz
        % CALCULAR AQU� LA ITERACION DE NEWTON: 
        x=x-f/fp;
    end
    
    s=x; % Devuelvo el �ltimo t�rmino de la sucesi�n
    return
 end
 
 % Ejercicio 4
 function s=miraiz1(x0,a) 
    s = x0;
    error = 1;
    iter = 0;
    while error>=1e-16 && iter<20
        iter = iter+1;
        s = (1/2) * (s + a/s);
        error = abs(s-sqrt(a))/sqrt(a);
        fprintf('ITERACI�N %d RA�Z %.20f ERROR %e \n',iter,s,error);
    end
 end
 
 function s=miraiz2(x0,a) 
    s = x0;
    error = 1;
    iter = 0;
    while error>=1e-16 && iter<20
        iter = iter+1;
        s = (s/2) * (3 - s^2/a);
        error = abs(s-sqrt(a))/sqrt(a);
        fprintf('ITERACI�N %d RA�Z %.20f ERROR %e \n',iter,s,error);
    end
 end
 
 % Ejercicio 5
 function s = ejerc_5(X0,TOL,NMAX)
 iter = 1;
 x_ant = X0;
 x = (x_ant+1)^(1/3);
 while abs(x-x_ant) >= TOL && iter < NMAX
    iter = iter + 1;
    x_ant = x;
    x = (x_ant+1)^(1/3);
    fx = -1-x+x^3;
    fprintf('ITERACI�N %d RA�Z %.20f FUNCION %e \n',iter,x,fx);
 end
 s = x;
 end
 