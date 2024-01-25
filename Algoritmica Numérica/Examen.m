
% H=[xi.^0 xi xi.^2 xi.^3 xi.^4 xi.^5 xi.^6 xi.^7 xi.^8 xi.^9 xi.^10];


% pp = c(1) + c(2).*xx + c(3).*xx.^2 + c(4).*xx.^3 + c(5).*xx.^4 + c(6).*xx.^5 
% + c(7).*xx.^6 + c(8).*xx.^7 + c(9).*xx.^8 + c(10).*xx.^9 + c(11).*xx.^10;

k=0:30;
vex=pi/2;
vap=sum(((2.^k).*(factorial(k)).^2)./(factorial(2.*k + 1)));
erel= abs((vex - vap)./vex);
fprintf("Valor de pi/2:%.21f Valor calculado: %.21f Error relativo cometido %e\n",vex, vap,erel)
%b)
ncif=0;
i=0;
vap=0;
vErel=zeros(1,93);
vNcif=zeros(1,93);
while ncif<16
  
    vex=pi/2;
    vap=vap + ((2.^i).*(factorial(i)).^2)./(factorial(2.*i + 1));
    erel= abs((vex - vap)./vex);
    ncif=floor(-log10(erel));
    i=i+1;
    vErel(i)=erel;
    vNcif(i)=ncif;
    fprintf("Iteracion:%d Aproximacion lograda: %.21f Error relativo %.21f Cifras significativas:%d\n",i,vap,erel,ncif)    
    
end

subplot(121);semilogy(1:93,vErel,'r')
subplot(122);plot(1:93,vNcif,'b*')

%Ejercico 2
xi=[-2 0 1 2]';
yi=[9 -3 -3 1]';
xx=-2:0.001:2;
%Apartado a
%Al tener 4 datos el polinomio mínimo será de grado 3
H=[xi.^0 xi xi.^2 xi.^3];
b=yi;
c=H\b;
 
pp = c(1) + c(2).*xx + c(3).*xx.^2 + c(4).*xx.^3; 
 
subplot(111);plot(xx,pp,'b',xi,yi,'r*')
 

 
%Apartado b)

H=[xi.^0 xi./(1 + xi.^2) cos(xi) sin(xi)];
b=yi;
c1=H\b;
pp1=c1(1) + c1(2).*xx./(1 + xx.^2) + c1(3).*cos(xx) + c1(4).*sin(xx);
 
hold on
plot(xx,pp1,'g')
hold off
 

%Apartado c)
%Al tener otro dato mas el polinomio minimo será de grado 4
H=[xi.^0 xi xi.^2 xi.^3 xi.^4];
H=[H;0 1 -2 3 -4];
b=[yi;-1];
c=H\b;
pp = c(1) + c(2).*0.5 + c(3).*(0.5.^2) + c(4).*(0.5.^3) + c(5).*(0.5.^4)


















































