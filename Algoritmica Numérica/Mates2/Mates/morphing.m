im1=imread('sergio3.jpg');
im2=imread('pablo3.jpg');
% im1=imread('willis.jpg');
% im2=imread('ant.jpg');
% % Puntos obtenidos al marcar las imágenes siguiendo las instrucciones: 
% % marcar_puntos, load puntos
% x1 = [1 1 270 270 144 71 205 58 215 65 207 98 111 121 162 178 192 125 150 164 112 168 109 169]';
% x2 = [1 1 270 270 158 76 233 60 228 61 211 108 118 136 175 188 203 130 156 170 111 175 97 173]';
% y1 = [1 320 1 320 15 77 79 128 129 189 190 129 127 130 129 126 129 164 163 171 201 201 254 259]';
% y2 = [1 320 1 320 18 70 102 120 146 187 210 132 134 139 144 143 149 183 185 193 207 219 253 261]';
% 
% subplot(121), image(im1), 
% hold on; plot(x1,y1,'r+','MarkerSize',6,'LineWidth',2), hold off;
% subplot(122), image(im2), 
% hold on; plot(x2,y2,'r+','MarkerSize',6,'LineWidth',2), hold off;
% NP=length(x1); % Calculamos el número de puntos 
% % Variamos el código para obtener una modificación diferente a la mitad en
% % cada caso
% 
% 
% 
% u = (x1+x2)/2; 
% v = (y1+y2)/2;
% u=(1-0.7)*x1+0.7*x2; % Caso similar con 0.3
% v=(1-0.7)*y1+0.7*y2; % Caso similar con 0.3
% T=delaunay(u,v);
% NT=length(T); % Calculamos el número de triángulos
% subplot(121), image(im1), 
% hold on; plot(x1,y1,'r+','MarkerSize',6,'LineWidth',2), triplot(T,x1,y1), hold off;
% subplot(122), image(im2), 
% hold on; plot(x2,y2,'r+','MarkerSize',6,'LineWidth',2), triplot(T,x2,y2), hold off;
% 
% 
% P1=zeros(3,2,NT);
% P2=zeros(3,2,NT);
% for k=1:NT
%     indices=T(k,:);
%     xy2=zeros(3,2);
%     xy=zeros(3,2);
%     uv=zeros(3,2);
%     for i=1:3
%         xy(i,1)=x1(indices(i));
%         xy(i,2)=y1(indices(i));
%         xy2(i,1)=x2(indices(i));
%         xy2(i,2)=y2(indices(i));
%         uv(i,1)=u(indices(i));
%         uv(i,2)=v(indices(i));
%     end
%     P1(:,:,k)=get_afin(xy,uv);
%     P2(:,:,k)=get_afin(xy2,uv);
% end

N=size(im1,1); M=size(im1,2); zona = determina_triang(T,u,v,N,M);
im1_d=warp_img(im1,P1,zona);
im2_d=warp_img(im2,P2,zona);
% res=0.5*im1_d+0.5*im2_d;
% subplot(1,1,1), image(res)
res=(1-0.6)*im1_d+0.6*im2_d; % Caso similar con 0.3
subplot(1,1,1), image(res)

% Para el GIF
% t=[(0:0.025:1) (1:-0.025:0)];
% for k=1:length(t)
%     u=(1-t(k))*x1+t(k)*x2;
%     v=(1-t(k))*y1+t(k)*y2;
%     T=delaunay(u,v);
%     NT=length(T); % Calculamos el número de triángulos
%     P1=zeros(3,2,NT);
%     P2=zeros(3,2,NT);
%     for i=1:NT
%         indices=T(i,:);
%         xy2=zeros(3,2);
%         xy=zeros(3,2);
%         uv=zeros(3,2);
%         for j=1:3
%             xy(j,1)=x1(indices(j));
%             xy(j,2)=y1(indices(j));
%             xy2(j,1)=x2(indices(j));
%             xy2(j,2)=y2(indices(j));
%             uv(j,1)=u(indices(j));
%             uv(j,2)=v(indices(j));
%         end
%         P1(:,:,i)=get_afin(xy,uv);
%         P2(:,:,i)=get_afin(xy2,uv);
%     end
%     im1_d=warp_img(im1,P1,zona);
%     im2_d=warp_img(im2,P2,zona);
%     res=(1-t(k))*im1_d+t(k)*im2_d; % Caso similar con 0.3
%     fich=sprintf('morph%02d.jpg',k); imwrite(res,fich,'Quality',95);
% end



