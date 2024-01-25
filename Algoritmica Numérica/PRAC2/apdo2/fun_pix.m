function [F,J] = fun_pix(X)
global ctrl_3D ctrl_PX ctrl_PY
pos = mod_cam(ctrl_3D,X);
resx = ctrl_PX-pos;
resy = ctrl_PY-pos;
F = [resx resy]';
if nargout==1, return; end
M = length(F);
N = length(X);
J = zeros(M,N);
h = 0.1;
h1 = zeros(1,6)';
for i=1:6
    h1(i) = h;
    J(:,i) = (fun_pix(X+h1) - F) / h;
end
return 
end

% X = vector columna 6x1 con la posicion/orientación cámara
% F = vector columna con los residuos en puntos de control

% Calculo de F (vector de residuos)
 

% Aproximacion a la matriz J

