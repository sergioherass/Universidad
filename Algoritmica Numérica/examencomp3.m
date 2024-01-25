%1 
clc,clear
x=zeros(1,5);
x(1)=2;
c=10;
s=log(c);
Eabs=zeros(1,5);
for n=1:5
    x(n+1)=x(n)+(c-exp(x(n)))/(4*x(n));
    eabs=abs(x(n)-log(10));
    Eabs(n)=eabs;
    fprintf('\n%2d) x=%.16f -> e=%.4e',n,x(n),eabs);
end

semilogy(1:5,Eabs(1:5));
Erel=Eabs./abs(log(10));
ncif=floor(-log10(Erel));

k=Eabs(2:5)./Eabs(1:4)
fprintf('\n')
%met2

x2=zeros(1,5);
x2(1)=2;
c=10;
s=log(c);
Eabs2=zeros(1,5);
for n=1:5
    x2(n+1)=x2(n)-(exp(x2(n))-10)/(exp(x2(n)));
    eabs2=abs(x2(n)-log(10));
    Eabs2(n)=eabs2;
    fprintf('\n%2d) x=%.16f -> e=%.4e',n,x2(n),eabs2);
end
hold on;semilogy(1:5,Eabs2(1:5));hold off;
fprintf('\n')
%met3

x3=zeros(1,5);
x3(1)=2;
c=10;
s=log(c)
fprintf('\ns=%.16f',s)
Eabs3=zeros(1,5);
for n=1:5
    x3(n+1)= x(n)+2*(c-exp(x(n)))/(c+exp(x(n)));
    eabs3=abs(x3(n)-log(10));
    Eabs3(n)=eabs3;
    fprintf('\n%2d) x=%.16f -> e=%.4e',n,x3(n),eabs3);
end
hold on;semilogy(1:5,Eabs3(1:5));hold off;

%met final

c=10;
xx=[0:0.01:5];

x=zeros(1,length(xx));
x=c.*exp(-xx)+xx-1;
x(1)=xx(1);
n=1;
while x(n+1)-x(n)>=1e-05
    n=n+1;
    x(n+1)=c*exp(-x(n))+x(n)-1;
    fprintf('n=%2d x=%.15f',n,x(n))
end
plot(xx,x)






