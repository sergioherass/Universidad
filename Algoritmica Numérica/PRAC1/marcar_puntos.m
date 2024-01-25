fich1=uigetfile('willis2.jpg'); im1=imread(fich1);
fich2=uigetfile('ant2.jpg');    im2=imread(fich2);

N=size(im1,2); M=size(im1,1); % Alto ancho imágenes

figure;

subplot(121); image(im1); axis off; hold on; eje1=gca;
subplot(122); image(im2); axis off; hold on; eje2=gca;


x1 = [1 1 N N]; y1 = [1 M 1 M];  % Inicializo con las esquinas.
x2 =x1; y2=y1;


% Guardamos puntos hasta que pulsamos boton derecho.
[px,py,but] = ginput(1);
while (but==1)             
  if (gca==eje1), 
      col='r'; x1 = [x1 round(px)];  y1 = [y1 round(py)];
  else
      col='g'; x2 = [x2 round(px)];  y2 = [y2 round(py)];
  end
  plot(px,py,[col 'o'],'MarkerSize',3,'MarkerFaceColor',col);         
  [px py but] = ginput(1);    
end    


x1 = x1'; y1 = y1'; x2=x2'; y2=y2'; % Vectores columna



% Guardo variables x/y en fichero mat de nombre xy_fich1_fich2
%pos = strfind(fich1,'.jpg'); fich1=fich1(1:pos-1);
%pos = strfind(fich2,'.jpg'); fich2=fich2(1:pos-1);
%name = ['xy_' fich1 '_' fich2]
save('new_puntos','x1','y1','x2','y2');   
