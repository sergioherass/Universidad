 X=125;
 Y=1163;
 E=304556;
 N=4458946;
 
[P,res]=get_similarr(X,Y,E,N)
fprintf('%6.3f %6.3f %9.1f\n',P');

function [P,res]=get_similarr(X,Y,E,N)
H = [X Y 1 0 ; -Y X 0 1];

v= [E;N];

c=H\v;
% oE=E-c(1)*X-c(2)*Y;
% oN=N-c(2)*X+c(1)*Y;
% c(3)=oE;
% c(4)=oN;

P=[c(1) c(2) c(3) ; c(2) -c(1) c(4)]; 
res = v - H*c;
return
end
