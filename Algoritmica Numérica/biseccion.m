% M�todo de la bisecci�n
function  s=biseccion(fn,a,b,E_cota,n_max)
% M�todo de la bisecci�n.
% Entrada: fn nombre de la funci�n 
%          [a,b] intervalo de b�squeda/incertidumbre
%          E_cota es la tolerancia. NOTA: Si este valor no se pretende tener en
%       cuenta poner 0 en su lugar y actuar� solo con el n�mero de
%       iteraciones m�ximas (n_max).
%          n_max es el numero m�ximo de iteraciones.
%
% Salida:  s es la aproximaci�n de la raiz

fa=feval(fn,a);fb=feval(fn,b); % Evaluo la funci�n f en a y en b.
if fa*fb > 0    
    fprintf('La funci�n no tiene ra�ces simples en el intervalo [a,b]\n')
return
end

%CALCULAR  n_iter_cota PARA QUE EL ERROR SEA MENOR QUE E_cota
if E_cota~=0
    error=1;n_iter_cota=0;
    while error>E_cota
        error=error/2; % En cada iteraci�n el error se divide a la mitad exactamente
        n_iter_cota=n_iter_cota+1;
    end
    %n_iter_cota=100;   % Valor por defecto, hasta que se calcule n_iter_cota
    n=min(n_iter_cota,n_max);
else
    n=n_max;
end

for i=1:n   
    c=(a+b)/2;    
    fc=feval(fn,c);
    if fa*fc < 0        
        b=c;
    else
        a=c;        
        fa=fc;  % Ahorramos una evaluacion en la siguiente iteracion
    end
end
s=a; % pueder ser s=b o s=(a+b)/2,
fprintf('La raiz aproximada es %.8f\n',s)
return 
end