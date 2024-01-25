% Método de la bisección
function  s=biseccion(fn,a,b,E_cota,n_max)
% Método de la bisección.
% Entrada: fn nombre de la función 
%          [a,b] intervalo de búsqueda/incertidumbre
%          E_cota es la tolerancia. NOTA: Si este valor no se pretende tener en
%       cuenta poner 0 en su lugar y actuará solo con el número de
%       iteraciones máximas (n_max).
%          n_max es el numero máximo de iteraciones.
%
% Salida:  s es la aproximación de la raiz

fa=feval(fn,a);fb=feval(fn,b); % Evaluo la función f en a y en b.
if fa*fb > 0    
    fprintf('La función no tiene raíces simples en el intervalo [a,b]\n')
return
end

%CALCULAR  n_iter_cota PARA QUE EL ERROR SEA MENOR QUE E_cota
if E_cota~=0
    error=1;n_iter_cota=0;
    while error>E_cota
        error=error/2; % En cada iteración el error se divide a la mitad exactamente
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