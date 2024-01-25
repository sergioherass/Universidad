# -*- coding: utf-8 -*-
"""
Created on Wed May 18 16:13:31 2022

@author: 34656
"""

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
