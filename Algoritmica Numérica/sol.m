function sol(arg)
if nargin==0 
 fprintf('Teclee lab_4 n para ejecutar el código del ejercicio correspondiente\n');
 fprintf('n = 1, 2, ...\n**********************\n');
 return
end

switch arg
    
    case '1'
        
      h=10.^(0:-0.5:-15);
      x=1; 
      ok = cos(x);
      est = (sin(x+h)-sin(x-h))./(2*h);
      
      Eabs = abs(est-ok);
      Erel = Eabs/abs(ok);
      figure(1); loglog(h,Erel,'bo-');  % Error relativo
      
      % Erel min = 10^-12. Podemos estimar la derivada con unas 
      %                    12 cifras usando h ~ 3.6e-6
      
      % COTA de error absoluto.      
      cota_1 = h.^2/6;
      cota_2 = eps./h; 
      cota=cota_1+cota_2;      
      figure(2); loglog(h,Eabs,'bo-',h,cota,'r:')           
      
      % OPCIONAL
      c=Eabs(1:5)./(h(1:5));
      fprintf('Relación suponiendo h  : '); fprintf('%.3f ',c); fprintf('\n');
      c=Eabs(1:5)./(h(1:5).^2);
      fprintf('Relación suponiendo h^2: '); fprintf('%.3f ',c); fprintf('\n');
      c=Eabs(1:5)./(h(1:5).^3);
      fprintf('Relación suponiendo h^3: '); fprintf('%.3f ',c); fprintf('\n');
      
      x=(0:0.01:2*pi); h=0.001;       
      c = (sin(x+h)-sin(x-h))/(2*h);
      e = abs(c -cos(x));
      figure(3); plot(x,e)
      
                    
    case '2'   
      N=15; a=zeros(1,N); b=zeros(1,N); a(1)=2*sqrt(3); b(1)=3;
      for k=2:N
        a(k)=2*(a(k-1)*b(k-1))/(a(k-1)+b(k-1)); b(k)=sqrt(a(k)*b(k-1));  
      end
      figure(1); plot((1:N),a,'ro:',(1:N),b,'bo:');
     
      fprintf('%.8f\n',pi,a(8),b(8));
      
      E_a=abs(a-pi)/pi; 
      figure(2); semilogy(E_a,'ro:');
      
      c=(a+b)/2;  E_c=abs(c-pi)/pi; hold on; semilogy(E_c,'bo:'); hold off
      
      E_b=abs(b-pi)/pi;
      E_a./E_c
      E_b./E_c
      
      % Parte Opcional
      % Al ser el error de a el doble del de b, una combinación mejor sería
      % c = (2*b+a)/3
      c = (2*b+a)/3;
      E_c=abs(c-pi)/pi; hold on; semilogy(E_c,'ko:'); hold off

    case '3'
      a=2^23;  % De forma que la separación es 2^23*2^-23 = 1
      x=single(a);
      fprintf('%.20f\n',x+0.49,x+0.51,x+0.5,x+1.5);
      
      y=2^52;
      fprintf('%.4f\n',y,y+0.49,y+0.51);
      
      
    case '4a'
     q=1000; p=3142;
     r=p/q; Eabs=abs(r-pi); Erel=Eabs/pi;
     fprintf('p = %d. Erel %.1e\n',p,Erel);
            
     N=200;
     q=(1:N); p=round(q*pi); r = p./q;
     Eabs=abs(r-pi); Erel=Eabs/pi;      
     figure(1); semilogy(q,Erel)
      
     qq = 113; % posición del mínimo
     fprintf('Frac %d/%d Erel %.2e\n',p(qq),qq,Erel(qq))
     fprintf('Solo cifras correctas p/q ~ %.6f\n',p(qq)/qq);   
      
      N=10000;  % Repetir para N=10000
      q=(1:N); p=round(q*pi); r = p./q;
      Eabs=abs(r-pi); Erel=Eabs/pi;      
      figure(2); semilogy(q,Erel)
      fprintf('Error mínimo para N=%d -> %.2e\n',N,min(Erel)); 
      % La fracción anterior no se mejora para ningún q <=10000
      
    case '4b'
      x_from_fc([1 2 2])        
      s=x_from_fc([1 2 2 2 2 2 2 2 2 2]);
      fprintf('%.7f\n%.7f\n',s,sqrt(2))
      
      a = [3 7 15 1 292 1 1 1 2 1];      
      res=zeros(1,10);
      for n=1:10, res(n)=x_from_fc(a(1:n)); end
      Eabs=abs(res-pi); Erel=Eabs/abs(pi);      
      semilogy((1:10),Erel,'ro:');
      % Con 5 términos estamos en 10^-10 ~10 cifras ok
      % El error para 4 términos es 8.49e-8, justo el obtenido 
      % en el apartado anterior con la busqueda hasta 10000
      
    case '4c'        
      x=(sqrt(5)+1)/2;        
      a=fc_from_x(x,10) ; fprintf('%d ',a); fprintf('\n');     
      a=fc_from_x(pi,20); fprintf('%d ',a); fprintf('\n');              
end

return


function x=x_from_fc(a)
  L=length(a);
  x=a(L);
  for k=L-1:-1:1, x= a(k)+1/x; end
return

function a = fc_from_x(x,N)    
  a = zeros(1,N);
  for k=1:N
    a(k)=floor(x); r=x-a(k);
    if r==0, break; end
    x=1/r;
  end
return

