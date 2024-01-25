function a = fc_from_x(x,N) 
N=20;
a=zeros(1,N);
x=pi
for k=(1:N)
    a(k)=floor(x);
    r=1./a(k);
    if(r==0)
        break
    else
        x=1./r;
end
end
