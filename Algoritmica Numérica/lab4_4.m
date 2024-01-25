
function x=x_from_fc(a)
a=[1 2 2 2 2 2 2 2 2 2];
L=length(a)-1;
x=a(L+1);

for k=(L:-1:1)
    x = a(k) + (1/x);
end
fprintf("x=%7f",x)
end




