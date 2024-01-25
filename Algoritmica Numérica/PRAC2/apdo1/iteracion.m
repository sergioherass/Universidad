% Script que implementa un bucle usando el método tipo secante
% El punto inicial de partida es X (vector 2x1).  
X = X0;  % punto inicial capturado por la aplicación

% inicializar dX y contador de iteracion n
dX=1;
n=0;
% Bucle hasta cumplir condiciones de paradademo
while  norm(dX)>0.001 && n<200   
    % Evaluar funcion F y jacobiano J     
     [F,J]=fun(X);         
    % Hallar dX y actualizar X        
     dX=J\-F;
    % Incrementad contador de iteracion
     X=X+dX;
     n=n+1;
    % Llamad a show_results(X);
     show_results(X);
% End del bucle 
end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Funcion auxiliar para mostrar resultados
function show_results(X)
global traza txt 
  x = get(traza,'Xdata'); y = get(traza,'Ydata');      
  set(traza,'Xdata',[x X(1)],'Ydata',[y X(2)]);   
  n=length(x);
    
  msg = sprintf('iter:%3d -> (%+10.7f,%+10.7f)',n,X);
  set(txt(1),'String',msg);
  pause(0.01)    
end
    

 