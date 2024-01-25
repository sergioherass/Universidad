clear

x=0.1;
xs=single(x);

% apartado a)

whos x xs

% Una variable de tipo double ocupa 8 bytes, mientras que una de tipo
% single ocupa 4 bytes

% apartado b)

fprintf("x=%.15f\nxs=%.15f\n",x,xs)

% No coinciden los valores y xs es el incorrecto

%apartado c)

Er=abs(xs-x)/abs(x);
disp(Er)

% El error relativo cometido por xs es 1.4901e-08

% apartado d)

fprintf("\n\nx=%.20f\nxs=%.20f\n",x,xs)

% En este caso ninguno de los valores es correcto ya que no es exacto a 0.1
