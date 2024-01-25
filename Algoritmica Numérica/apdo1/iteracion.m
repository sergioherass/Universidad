% Script que implementa un bucle usando el método tipo secante
% El punto inicial de partida es X (vector 2x1).  
X = X0;  % punto inicial capturado por la aplicación

% inicializar dX y contador de iteracion n

% Bucle hasta cumplir condiciones de parada
    
    % Evaluar funcion F y jacobiano J     
              
    % Hallar dX y actualizar X        
    
    % Incrementad contador de iteracion
     
    % Llamad a show_results(X);

% End del bucle 


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
    

 