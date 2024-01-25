function im=warp_img(im,P,zona)
% im = imagen a deformar (im), P = familia de matrices afines a utilizar
% zona = información sobre el triángulo al que pertenece cada píxel.
im=double(im)/255;
N=size(im,1);
M=size(im,2);
X=zeros(N,M);
Y=zeros(N,M);
Q=zeros(size(P));
for k=1:size(P,3)
    PP=[P(:,:,k), [0 0 1]'];
    QQ=inv(PP);
    Q(:,:,k)=[QQ(:,1) QQ(:,2)];
end
for v=1:N
    for u=1:M
        z=zona(v,u);
        coord=[u v 1]*Q(:,:,z);
        X(v,u)=coord(1);
        Y(v,u)=coord(2);
    end 
end
for k=1:3
    im(:,:,k)=interp2(im(:,:,k),X,Y,'bilinear');
end
image(im);
return