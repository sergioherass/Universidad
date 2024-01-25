import sympy as sy
from sympy.abc import x

f = sy.poly(x**6 - x**5 - x**4 + x**2 + x -1, x, domain = 'QQ')
g = sy.poly(x**5 + x**4 - 2*x**3 + x**2 + x - 2, x, domain = 'QQ')
q, r = sy.quo(f,g), sy.rem(f, g)
d = sy.gcd(f, g)
print('q = ', q, 'r = ', r, 'd = ', d)
L, M, dd = sy.gcdex(f, g)
print('L = ', L, 'M = ', M, 'dd = ', dd)
print(sy.factor(x**6 - x**5 - x**4 + x**2 + x - 1, x, domain = 'QQ'))

f3 = sy.poly(x**6 - x**5 - x**4 + x**2 + x -1, x, modulus = 3)
q3 = sy.poly(x**5 + x**4 - 2*x**3 + x**2 + x - 2, x, modulus = 3)
q3, r3 = sy.quo(f3, q3), sy.rem(f3, q3)
d3 = sy.gcd(f3, q3)
print('q3 = ', q3, 'r3  = ', r3, 'd3 = ', d3)
L3, M3, dd3 = sy.gcdex(f3, q3)
print('L3 = ', L3, 'M3 = ', M3, 'dd3 = ', dd3)
print(sy.factor(x**6 - x**5 - x**4 + x**2 + x -1, x, modulus = 3))

print("")
print("Segundo ejercicio ")
for k in range(1, 16):
    print(k, sy.factor(x**k -1, x))

print("")
print("Tercer ejercicio ")
print( sy.factor(x**2-3, x, domain = 'QQ<sqrt(3)>'))
print( sy.factor(x**2-3, x, domain = 'QQ<3**(1/2)>'))
print(sy.factor(x**2-3, x, extension = [sy.sqrt(3)]))
print(sy.factor(x**4 - 1, x, domain = 'QQ<I>'))
print(sy.factor(x**4 - 5*x**2 + 6, domain = 'QQ<sqrt(2), sqrt(3)>'))
print(sy.factor(x**4 - 5*x**2 + 6, extension = [sy.sqrt(2), sy.sqrt(6)]))

print(" ")
print("Cuarto ejercicio ")
print(sy.minpoly(2**(sy.Rational(1, 2)), x))
print(sy.minpoly(sy.sqrt(3), x, domain = sy.QQ.algebraic_field(sy.sqrt(2))))
print(sy.minpoly(sy.sqrt(3) + sy.sqrt(6), x))

print(" ")
print("Quinto ejercicio ")
from sympy.polys.agca.extensions import FiniteExtension

h5 = sy.poly (x**3 + x**2 +2 , modulus=5)
print(h5.is_irreducible)
F125= FiniteExtension(h5)
resultado=F125(x+3)/(x**2+2)
print('resultado=',resultado)
L5, M5, dd5=sy.gcdex(h5, sy.poly(x**2 +2,x,modulus=5))
contraste=sy.rem(sy.poly((x+3)*M5,x,modulus=5),h5)
print('contraste=',contraste)
