global ctrl_3D ctrl_PX ctrl_PY
load datos; 
im=imread('gredos.jpg'); 
figure(1); 
image(im);
ctrl_PX(1) = 1042;
ctrl_PY(1) = 725;
hold on
plot(ctrl_PX,ctrl_PY,'ro:');
 
X=[308000;4458000;2000;-100;0;0];
[F,J] = fun_pix(X)
 
X0 = zeros(6,1);
Este = ((ctrl_3D(1,1) + ctrl_3D(1,2) + ctrl_3D(1,3) + ctrl_3D(1,4))/4) + 2000;
Norte = (ctrl_3D(2,1) + ctrl_3D(2,2) + ctrl_3D(2,3) + ctrl_3D(2,4))/4;
Altura = ((ctrl_3D(3,1) + ctrl_3D(3,2) + ctrl_3D(3,3) + ctrl_3D(3,4))/4) - 500;
X0 = [Este Norte Altura -90 0 0]';
X = X0;
dX=1;
while abs(dX)>=0.1
 [F,J]=fun_pix(X);
 dX=J\F;
 X=X-dX;
 fprintf('||F|| = %.2f\n', norm(F,inf))
end
fprintf('Posición Este: %.1f \nPosición Norte: %.1f \nAltura: %.1f \nAzimuth: %.1f \nElevacion: %.1f \nInclinación: %.1f\n',X(1),X(2),X(3),X(4),X(5),X(6))
pix=mod_cam(ctrl_3D,X)
residuosX=abs(pix-ctrl_PX)
residuosY=abs(pix-ctrl_PY)
residuos = [residuosX;residuosY]
plot(pix,'go'); hold off;
