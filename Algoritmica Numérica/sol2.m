function sol2(prob)
if nargin==0, prob='H4'; end
fprintf('\nResolviendo problema %s **************************\n',prob);

switch(prob)
        
    case 'H4'  
        clear
        x=0.1; xs=single(x);
        whos x xs
        fprintf('%.15f\n%.15f\n',x,xs);
        Er = abs(xs-x)/x
        fprintf('%.20f\n%.20f\n',x,xs);

    case 'I3'        
        cosn(0.3,4),cos(0.3)^4,        
        x=(-2:0.01:2);
        plot(x,cosn(x,5),x,cosn(x,2));
                
    case 'J4'         
        a=1; b=1/sqrt(2); t=1/4; x=1;        
        k=1;
        while abs(a-b)>1e-14,  % Cambiar a while (k<=10)
          y = a;  a = (a+b)/2; b = sqrt(b*y); t = t-x*(y-a)^2;  x=2*x;
          aprox = (a+b)^2/(4*t);
          err = abs(aprox-pi);
          fprintf('Iter %d aprox %.15f Error %.1e\n',k,aprox,err);
          k=k+1;
        end        
        
    case 'K1'                      
        F=ones(1,20); F(1:2)=1;
        for k=3:20, F(k)=F(k-1)+F(k-2); end
        C=F(2:end)./F(1:end-1);
        
        figure(1); plot(C,'ro:');
        
        s=(1+sqrt(5))/2; dif=abs(C-s); figure(2); plot(dif);
        figure(3); semilogy(dif);
           
    case 'J5'        
        e = exp(1);
       
        % Opcion a)
        n=(0:50); 
        terminos = 1./factorial(n); suma = sum(terminos); 
        fprintf('Dif = %e\n',suma-e)
   
        % Opcion b)
        t = 1; suma = 1; 
        for k=1:50
            t = t/k; suma = suma+t; 
        end
        fprintf('Dif = %e\n',suma-e)
        
        % Opcion c)
        t = 1; suma = 1;
        for k=1:50 
          t = t/k; suma = suma+t; 
          if abs(t)<1e-18, break; end
        end
        fprintf('Dif = %e tras k=%d terminos \n',suma-e,k)              
    
    case 'K2'
        x=(1/4:1e-5:4);  xs=single(x); e = (xs-x);
        plot(x,e);
        
    case 'K3'          
        a=1234; b=5638;
        mult_rusa(a,b)-a*b          
        tic, for k=1:100000, mult_rusa(1234567890,2); end; toc
        tic, for k=1:100000, mult_rusa(2,1234567890); end; toc        
        
    
                
     otherwise,  fprintf('No existe prob %s !! \n',prob);                                 
end

return


%%%%%%%%%%%%%  FUNCIONES AUXILIARES PEDIDAS %%%%%%%%%%%%%%%%%%%%%%%%%%%%
function res=cosn(x,n)
  res = cos(x).^n;
return

function P=mult_rusa(a,b)
  P = 0; 
  while (b~=0)
    if mod(b,2)==1, P=P+a; end      % Si b es impar
    a=a*2;
    b = floor(b/2);
  end    
return