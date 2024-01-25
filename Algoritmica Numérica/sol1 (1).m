% APARTADO A    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

x=(0:0.01:2*pi);    % Solucion A4)

x(end)  % Solucion A6)

% Solucion  A9) variantes
x = sqrt(3)*(0:9)/9,   
x = (0 : sqrt(3)/9: sqrt(3))
x = linspace(0,sqrt(3),10) 


% APARTADO B  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

A = ones(3,6)*7,      % Solucion B2)
A = zeros(3,6)+7,

x=rand(1,10); m1=mean(x),     % Solucion  B6)
x=rand(1,100); m2=mean(x),
x=rand(1,100000); m3=mean(x),

x = (rand(1,5)*2)-1;   % Solucion  B7)

% APARTADO C   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
A=[1 2 3 4;5 6 7 8;9 10 11 12];

B = A(:,end)    % Solucion C2
B = A([1 3],:) % Solucion C4
A(:,end)=-1     % Solucion C8

nota = 5.5+1.2*randn(1,60);  % Solucion C10
mean(nota), max(nota)
apr = nota(nota>=5.0); mean(apr)
nota(nota>=4.7 & nota<5)=5



% APARTADO D    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
x = (0:3:30);  

x(1:2:end)=0   % Solucion 1d)
x=[1 2 3 4]; y=[5 6 7 8];  % Solucion 3c) 
A1 = [x' y'];
A2 = [x; y]';  % Ambas son equivalentes 



% APARTADO E    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%

A=[1 2; -1 1], B = [-1 0; 0 1],

A*B-B*A,       % Solucion E4
(A*B)'-B'*A'

C=A^3, D=A.^3  % Solucion E5
A*A*A   % A^3 = elevar A al cubo = A*A*A
        % A.^3 = elevar CADA ELEMENTO de A al cubo.

% APARTADO F    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
x=[1 2 3 4]; y=[5 6 7 8];

z = x./y;  % Solucion 1b)
z = 1./x;  % Solucion 1d)

z = (0:9)./(1:10)   % Solucion 3

% Solucion F8)
N=10000; n=(1:N); 
s=1./(n.^2 ); 
sum(s)-(pi^2)/6


% APARTADO G3  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

x = (-pi:0.01:pi);  f1 = cos(x)/2;  f2 = x./exp(abs(x));

% Superpuestas en la misma gráfica
figure; plot(x,f1,'r',x,f2,'b');
figure; plot(x,f1,'r'); hold on; plot(x,f2,'b'); hold off

% En la misma figuta en dos ejes distintos
figure; subplot(121); plot(x,f1,'r'); 
        subplot(122); plot(x,f2,'b'); 

% En 2 figuras distintas
figure; plot(x,f1,'r'); 
figure; plot(x,f2,'b'); 

% APARTADO G4    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

x=(0:0.1:100);  f = exp(-x);
figure; plot(x,f)
figure; semilogy(x,f);


% APARTADO G6    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
A=10;E=0.5;
th=(0:0.01:2*pi);
r=A*(1-E^2)./(1+E*cos(th));
X=r.*cos(th); Y=r.*sin(th);
subplot(121); plot(X,Y); axis equal
subplot(122); polar(th,r)