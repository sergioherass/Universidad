import sympy as sy
u,v=sy.symbols('u,v',real=True)
x,y,z,t=sy.symbols('x,y,z,t',real=True)



sup=[sy.cosh(u)*sy.cos(v),sy.cosh(u)*sy.sinh(v),u]

sdu=[sy.diff(sup[i],u)for i in range(3)]
sdv=[sy.diff(sup[i],v)for i in range(3)]
duxdv=sy.Matrix(sdu).cross(sy.Matrix(sdv))


E=sy.Matrix(sdu).dot(sy.Matrix(sdu))
F=sy.Matrix(sdu).dot(sy.Matrix(sdv))
G=sy.Matrix(sdv).dot(sy.Matrix(sdv))
u0=0
v0=1
n=sy.Matrix(duxdv).normalized()
[k.subs([[u,0],[v,1]])for k in n]
E=sy.Matrix(sdu).dot(sy.Matrix(sdu))
F=sy.Matrix(sdu).dot(sy.Matrix(sdv))
G=sy.Matrix(sdv).dot(sy.Matrix(sdv))

print(E)
print(F)
print(G)

##from scipy.integrate import dblquad
#elemArea=sy.lambdify((u,v),sy.sqrt(E*G-F**2),"numpy")
#dblquad(elemArea,v1,v2,lambda u: u1,lambda u: u2)


#sy.Matrix(vecVel).norm3