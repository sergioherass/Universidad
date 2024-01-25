import sympy as sy
u, v = sy.symbols(’u, v’, real = True)
x, y, z, t = sy.symbols(’x, y, z, t’, real = True)

sup = [u, sy.cos(v), u + v]

sdu = [sy.diff(sup[i], u) for i in range(3)]
sdv = [sy.diff(sup[i], v) for i in range(3)]
sduu = [sy.diff(sup[i], u, u) for i in range(3)]
sy.Matrix(vector1).dot(sy.Matrix(vector2))
duxdv = sy.Matrix(vector1).cross(sy.Matrix(vector2))
[k.subs([[u, u0],[v, v0]]) for k in duxdv]

n = sy.Matrix(duxdv).normalized()
sy.Matrix(vecVel).norm()
