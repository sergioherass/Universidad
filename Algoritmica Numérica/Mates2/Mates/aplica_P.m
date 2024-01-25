function im = aplica_P(im,P)
im=double(im)/255;
N=size(im,1);
M=size(im,2);
X=zeros(N,M);
Y=zeros(N,M);
PP=[P, [0 0 1]'];
QQ=inv(PP);
Q=[QQ(:,1) QQ(:,2)];
for v=1:N
    for u=1:M
        coord=[u v 1]*Q;
        X(v,u)=coord(1);
        Y(v,u)=coord(2);
    end 
end
for k=1:3
    im(:,:,k)=interp2(im(:,:,k),X,Y,'bilinear');
end
image(im);
return


