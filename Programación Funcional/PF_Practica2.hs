module PF_Practica2 (Expresion(Var, Numero, Logico, Suma, If), Tipos (Entero, Booleano, General), Valor (Error, OkB, OkI), Entorno (Entorno), Funcion (Funcion), aplicar, tipoCorrecto, tipo, calcular, mi_lista) where

------------------------------------------------------------------------
-- Aquí se indican los ejercicios realizados.
-- Modificar la lista acorde a lo que se entrega
------------------------------------------------------------------------
mi_lista :: [Int]
mi_lista = [0,1,2,3]

------------------------------------------------------------------------
-- Tipos indicados en el enunciado. Añadir los deriving  o instance necesarios
-------------------------------------------------------------------------

data Expresion = Var String | 
                          Numero Integer | 
                          Logico Bool | 
                          Suma Expresion Expresion | 
                          If Expresion Expresion Expresion 
                                                                   
data Tipos = Entero | 
                   Booleano | 
                   General 
                                 
                                  
data Valor = Error | 
                   OkB Bool | 
                   OkI Integer
                   
data Entorno = Entorno [(String, Valor)] 

data Funcion = Funcion String Expresion 

instance Eq Tipos where
    Entero == Entero = True
    Booleano == Booleano = True
    General == General = True
    _ == _ = False

instance Show Expresion where
    show (Var x) = x
    show (Numero n) = show n
    show (Logico b) = show b
    show (Suma e1 e2) = "(" ++ show e1 ++ " + " ++ show e2 ++ ")"
    show (If e1 e2 e3) = "if " ++ show e1 ++ " then " ++ show e2 ++ " else " ++ show e3

instance Show Tipos where
  show Entero = "Entero"
  show Booleano = "Booleano"
  show General = "General"

instance Eq Valor where
  Error == Error = True
  OkB b1 == OkB b2 = b1 == b2
  OkI i1 == OkI i2 = i1 == i2
  _ == _ = False

instance Show Entorno where
  show (Entorno xs) = show xs

instance Show Valor where
  show Error = "Error"
  show (OkB b) = show b
  show (OkI i) = show i

instance Show Funcion where
  show (Funcion var exp) = "(\\" ++ var ++ " -> " ++ show exp ++ ")"

------------------------------------------------------------------------
-- Aquí va el código de las funciones. Sustituir por vuestro
-- código aquellas que queráis probar
-------------------------------------------------------------------------



-- Ejercicio 0
tipoCorrecto :: Expresion -> Bool

tipoCorrecto (Var _) = True 
tipoCorrecto (Numero _) = True 
tipoCorrecto (Logico _) = True 
tipoCorrecto (Suma e1 e2) = mismoTipo (tipo e1) (Just Entero) && mismoTipo (tipo e2) (Just Entero)
tipoCorrecto (If e1 e2 e3) = esBooleano (tipo e1) && mismoTipo (tipo e2) (tipo e3)

-- Ejercicio 1
tipo :: Expresion -> Maybe Tipos
tipo (Var _) = Just General
tipo (Numero _) = Just Entero
tipo (Logico _) = Just Booleano
tipo (Suma e1 e2) = if mismoTipo (tipo e1) (Just Entero) && mismoTipo (tipo e2) (Just Entero)
                    then Just Entero
                    else Nothing
tipo (If e1 e2 e3) = if esBooleano (tipo e1) && mismoTipo (tipo e2) (tipo e3)
                     then masGeneral (tipo e2) (tipo e3)
                     else Nothing

                                      
-- Ejercicio 2
calcular :: Expresion -> Entorno -> Valor
calcular (Var x) (Entorno env) = maybe Error id (lookup x env)
calcular (Numero n) _ = OkI n
calcular (Logico b) _ = OkB b
calcular (Suma e1 e2) env =
  case (calcular e1 env, calcular e2 env) of
    (OkI n1, OkI n2) -> OkI (n1 + n2)
    _ -> Error
calcular (If cond e1 e2) env =
  case calcular cond env of
    OkB True  -> calcular e1 env
    OkB False -> calcular e2 env
    _ -> Error


-- Ejercicio 3
aplicar :: Funcion -> Integer -> Maybe Integer
aplicar (Funcion varName expr) val =
  case calcular expr (Entorno [(varName, OkI val)]) of
    OkI res -> Just res
    _ -> Nothing



------------------------------------------------------------------------
-- Funciones auxiliares que se pueden usar (o no).
-------------------------------------------------------------------------

mismoTipo :: Maybe Tipos -> Maybe Tipos -> Bool
-- POST: Decide si los tipos t1 y t2 son compatibles
mismoTipo Nothing _ = False
mismoTipo _ Nothing = False
mismoTipo (Just t1) (Just t2) = (t1 == t2)  || (t1 == General) || (t2 == General)

masGeneral :: Maybe Tipos -> Maybe Tipos -> Maybe Tipos
-- POST: Devuelve el tipo más general entre dos tipos y Nothing si son incompatibles
masGeneral (Just General) t2 = t2
masGeneral t1 (Just General) = t1
masGeneral t1 t2 = t1

compatible :: Tipos -> Tipos -> Bool
compatible x y  |  (x == General) || (y == General)         = True
                       |  (x == Booleano) || (y == Booleano)     = (x /= Entero) && (y /= Entero)
                       |  (x == Entero) || (y == Entero)             = (x /= Booleano) && (y /= Booleano)
                       |  otherwise                                         = False                          

ambosEnteros :: Maybe Tipos -> Maybe Tipos -> Bool
-- POST: Decide si los dos tipos son compatibles con Entero
ambosEnteros Nothing _ = False
ambosEnteros _ Nothing = False
ambosEnteros (Just t1) (Just t2) = (t1 == Entero) && (t2 == Entero)   || 
                                                   (t1 == General) && (t2 /= Booleano)|| 
                                                   (t2 == General) && (t1/= Booleano)
                                                  
esBooleano :: Maybe Tipos -> Bool
-- POST: Decide si el tipo es compatible con Booleano
esBooleano (Just Entero) = False
esBooleano _ = True

esEntero:: Maybe Tipos -> Bool
-- POST: Decide si el tipo es compatible con Entero
esEntero (Just Booleano) = False
esEntero _ = True







