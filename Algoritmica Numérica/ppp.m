%eap2)
L=8
I=zeros(3,L)
a=0;
b=2;
%%h=(b-a)./(N-1);
for k=1:L
    N=2.^k +1
    vn=linspace(a,b);
    fun(N)
end











