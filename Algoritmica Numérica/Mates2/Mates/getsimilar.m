
X=1002;
Y=48;
E=310288;
N=4460893;

% res2={[0,0],[0,0],1.0e-09.*[-0,1164,-0.9313],1.0e-09.*[-0.4657,0],[0,0],1.0e-09.*[0.9313,0],1.0e-09.*[0.2328,0],1.0e-09.*[0.1746,0]}
% res2=[0 0 1.0e-09*-0,1164 1.0e-09*-0.4657 0 1.0e-09*0.9313 1.0e-09*0.2328 1.0e-09*0.1746]
% plot(res2)
 [P,res]=get_similar(X,Y,E,N)
fprintf('%6.3f %6.3f %9.1f\n',P');
% Q1 = (P' * P)
% fprintf('%6.3f %6.3f %9.1f\n',Q1');
Q3=[1.987050187456823e+07 0 0; 0 1.987050187456823e+07 0]
Q2=P
resta= Q3-Q2
fprintf('%6.3f %6.3f %9.1f\n',resta');
camino= Q3.*track

function [P,res]=get_similar(E,N,X,Y)
H=[X Y 1 0 ; -Y X 0 1]
v=[E;N];
c=H\v;
P=[ c(1) c(2) c(3) ; c(2) -c(1) c(4)]
res = v-H*c
return
end
