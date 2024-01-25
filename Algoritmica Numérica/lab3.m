%Ejer 1
clc,clear
collatz(1);
collatz(2);
collatz(3);
collatz(4);
x=[];
for k = 1:10000
	x=[x, collatz(k)];
end
display(x)
plot(x,'.')
function L = collatz(n)
L=1;
while(n~=1)
	if(mod(n,2) == 0)
		n=n/2;
	else
		n=3*n+1;
	end
	L=L+1;
end
end
