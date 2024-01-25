function P=get_afin(xy,uv)
% xy,uv: matrices Nx2 (N>=3) con coordenadas de origen(xy) y destino(uv)
% P matriz 3x2 con la transformación afín.
H1= [xy(:,1) xy(:,2) ones(length(xy),1)];
% Otra opción sería:
% l=length(xy);
% H1(:,1)=xy(:,1);
% H1(:,2)=xy(:,2);
% H1(:,3)=ones(l,1);
v1=uv(:,1);
c1=H1\v1;
v2=uv(:,2);
c2=H1\v2;
P=[c1 c2];
% Otra opción sería:
% P(:,1)=c1;
% P(:,2)=c2;
return
end