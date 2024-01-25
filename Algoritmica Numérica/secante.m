% M�todo de la secante
function s = secante(fn,x0,x1,tol,Nmax)

% Entrada:	fn es el nombre de la funci�n.
%        	x0 y x1 son los dos puntos necesarios para arrancar el m�todo.
%        	tol es la tolerancia. Iteraremos hasta que se cumpla que abs(xk - xk-1)<tol.
%         	Nmax es el n�mero m�ximo de iteraciones a realizar en cualquier caso.
%
% Salida: 	s valor de la ra�z

u= feval(fn,x0);
v= feval(fn,x1);
s=(x0*v-x1*u)/(v-u);
if u*v>0
    fprintf('Error, no hay ninguna ra�z en el intervalo [%.2f,%.2f]', x0,x1)
    
else
    if nargin==4, Nmax=10; end % Valor por defecto de Nmax
    tol = tol + 5 * eps;
    
    fs=feval(fn,s);
    its=1;
    
    while (abs(u-v)>=tol) && (its<Nmax)
        if u*fs>0
            x0=s; u=fs;
        else
            x1=s; v=fs;
        end
        
        s=(x0*v-x1*u)/(v-u);
        fs=feval(fn,s);
        its=its+1;
        
        fprintf("Iter: %2d, xk = %.16f\n",its,fs);
    end
end 

return
end