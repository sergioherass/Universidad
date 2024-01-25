function sol3 (arg)
if nargin==0
  fprintf('Teclee sol 1, sol 2,... para ejecutar ejer 1,2, ...\n');  return
end    

switch(arg)      
    
    case '1'
      N=25000;
      x=rand(1,N)*2-1;
      y=rand(1,N)*2-1;
      plot(x,y,'b.','MarkerSize',2);
      
      dentro = (x.^2/0.81 + y.^2/0.36 <=1);
      hold on;
      plot(x(dentro),y(dentro),'r.','MarkerSize',2);
      hold off
      
      n=sum(dentro);
      ratio = (n/N);
      fprintf('n=%d puntos out of %d => %.2f%%\n',n,N,n/N*100);
      area=pi*0.9*0.6; E=abs(area-ratio*4);
      fprintf('Area correcta %.3f\nArea estimada %.3f. Erel %.3f\n',...
          area,ratio*4,E/area);
        
             
    case '2'   
      n=(0:999999);
      T = 4*((-1).^n)./(2*n+1);
      
      fprintf('%.10f\n%.10f\n',pi,sum(T))
      
      R=zeros(1,6);
      for k=1:6
       nt = 10^k;   
       R(k) = sum( T(1:nt) );
      end    
      Eabs = abs(R-pi);       
      semilogy((1:6),Eabs,'ro:');  % o loglog(10.^[1 2 3 4 5 6],Erel)
      
    
     case '3'      
        S = paseo2D(10000);
        figure(1); plot(S(1,:),S(2,:),'r')
      
        x=S(1,:); y=S(2,:);
        d = sqrt(x.^2 + y.^2);
        figure(2); plot(d)
   
        S = paseo2D_malla(10000);
        figure(1);
        hold on; plot(S(1,:),S(2,:),'b')
        hold off
      
     case '4'
        a=single(1);
        while((a+1)~=a), a=a*2; end
        a
        a+1.1
        
        
     case '5'             
      N=100000; x=rand(1,N);      
      S1 = double(0.0); S2=single(0.0);      
      whos S1 S2
      for k=1:N
        S1=S1+x(k);
        S2=S2+x(k);
      end         
      fprintf('S1 = %.5f\nS2 = %.5f\n',S1,S2); 
      Eabs = abs(S1-S2); Erel = Eabs/abs(S1);
      fprintf('Eabs %.1e  Erel %.1e\n',Eabs,Erel);
      
    case '6' 
      N=10000; L=zeros(1,N);
      for n=1:N, L(n)=collatz(n); end
      figure(1); plot(L);
      
    case '6b'            
      N=1000000;        
      tic
      L=zeros(1,N); for n=1:N, L(n)=collatz(n); end
      toc    
      
      tic, L2=collatz_hasta(N); toc      
      
      figure(1); plot(L-L2,'b.');

      Lmax=max(L2)
      figure(2); histogram(L2)
      
      z=zeros(1,6);
      for k=1:log10(N)
        nt=10^k;
        z(k)=max(L2(1:nt));
      end
      figure(3); plot(z,'bo:')                                                                                                                        
end        

%%%%%%%%%% FUNCIONES AUXILIARES %%%%%%%%%%%%%
function S=paseo2D(N)
S = zeros(2,N);
for k=2:N
  th=rand*2*pi;
  paso = [cos(th) sin(th)]';
  S(:,k)= S(:,k-1) + paso;  
end     
return

function S=paseo2D_malla(N)
S = zeros(2,N);
for k=2:N
  q = floor(rand*4);
  th= q*pi/2;
  paso = [cos(th) sin(th)]'; 
  S(:,k)=S(:,k-1) + paso;  
end     
return

function L=collatz(n)
 a=n; L=1;
 while(a~=1)
  if (mod(a,2)==0), a=a/2; else, a=3*a+1; end
  L=L+1;  
 end    
return

function LL=collatz_hasta(n)
 LL=zeros(1,n); 
 for k=1:n
   a=k; L=1; 
   while(a~=1)
    if (mod(a,2)==0), a=a/2; else, a=3*a+1; end               
     if (a<k)  
       L=L+LL(a); a=1; % Sumamos serie conocida y salimos
     else
       L=L+1; 
     end
   end 
   LL(k)=L;
 end
return

