%examen
%ejer1
clc,clear
tk=[-3:1:3]';
yk=sinc(tk);
xx=[-3:0.01:3];
ff=sinc(xx);
H=[tk.^0 tk tk.^2 tk.^3 tk.^4 tk.^5 tk.^6];
c=H\yk;
pp=c(1)+c(2).*xx+c(3).*xx.^2+c(4).*xx.^3+c(5).*xx.^4+c(6).*xx.^5+c(7).*xx.^6;
pp2=c(1)+c(2).*tk+c(3).*tk.^2+c(4).*tk.^3+c(5).*tk.^4+c(6).*tk.^5+c(7).*tk.^6;
H2=[tk.^0 sin(tk) cos(tk) sin(2.*tk) cos(2.*tk) sin(3.*tk) cos(3.*tk)];
c2=H2\yk;
uu=c2(1)+c2(2).*sin(xx)+c2(3).*cos(xx)+c2(4).*sin(2.*xx)+c2(5).*cos(2.*xx)+c2(6).*sin(3.*xx)+c2(7).*cos(3.*xx);
uu2=c2(1)+c2(2).*sin(tk)+c2(3).*cos(tk)+c2(4).*sin(2.*tk)+c2(5).*cos(2.*tk)+c2(6).*sin(3.*tk)+c2(7).*cos(3.*tk);
plot(xx,ff,'k',tk,yk,'ko',xx,pp,'b',xx,uu,'r')

error_p=abs(ff-pp2);
error_u=abs(ff-uu2);
max(error_p);
max(error_u);
hold on;plot(xx,error_p,'b',xx,error_u,'r');hold off;

%ej2
clc,clear
tk=[0.0:0.5:3.0]';
yk=[4.46 1.94 1.34 0.92 0.76 0.60 0.55]';
tt=[0:0.01:3];
H=[tk.^0 tk tk.^2 tk.^3 tk.^4];
c=H\yk;
pp=c(1)+c(2).*tt+c(3).*tt.^2+c(4).*tt.^3+c(5).*tt.^4;
pp2=c(1)+c(2).*4+c(3).*4.^2+c(4).*4.^3+c(5).*4.^4

res=abs(yk-H*c);
norma=norm(res);
error_p=sum(res.^2);

H2=[tk.^0 tk];
v=1./yk;
c2=H2\v;
A=c2(1);
B=c2(2);
uu=1./(c2(1)+c2(2).*tt);
uu2=1./(c2(1)+c2(2).*4)

res2=abs(v-H2*c2);
norma=norm(res2);
error_u=sum(res2.^2);
plot(tk,yk,'rs',tt,pp,'r',tt,uu,'b')


H3=[tk]
v3=1./yk
c3=H3\(v3-1/4.46)
B3=c3(1)
qq=1./((1/4.46)+c3(1).*tt);
res=abs(yk-H*c)
norm(res)






