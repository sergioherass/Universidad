recorrerlista :: [ Int ] -> [Int]
recorrerlista []=[]
recorrerlista (x:xs)=recorrerlista xs


sumacifras :: Int -> Int
sumacifras n = 
              if n<10 then n 
                         else (n `mod` 10)+(sumacifras(n `div`10))

{-main :: IO()
main=do
  let lista = [ 1,2,3,4,5]
  mapM_ print lista
  putStrLn $ "Esta es la lista recorrida: " ++ show(recorrerlista lista)-}


longitudarray :: [ Int ] -> Int
longitudarray [] = 0 ---caso base en recursion
longitudarray(x:xs)=1+(longitudarray xs)

aLatinMacarronico :: String -> String
aLatinMacarronico [] = "um" 
aLatinMacarronico (l:resto) = l:(aLatinMacarronico resto)

{-main :: IO()
main=do
  pal <- getLine --scanner por teclado
  print (aLatinMacarronico pal)-}

esPrimo :: Int -> Bool
esPrimo x 
 | x < 2 =False
 | otherwise = null [ n | n <- [ 2..x-1], x `mod`n ==0]

{-main :: IO()
main = do 
  let x = 76
  case esPrimo x of
    True  -> putStrLn $ "Es primo " ++ show x
    False  -> putStrLn $ "No es primo  " ++ show x-}

elevar2 :: Num a => a -> a
elevar2 x = x ^ 2

main :: IO ()
main = do
  let x = 5
  let y = 7

  let esMayorOIgual = x >= y
  putStrLn $ show x ++ " >= " ++ show y ++ " ? " ++ show esMayorOIgual

  let esMenorOIgual = x <= y
  putStrLn $ show x ++ " <= " ++ show y ++ " ? " ++ show esMenorOIgual

primero :: [Integer] -> Integer
primero []  = error "lista vacía" 
primero (x:resto) = x


sol :: Float-> Float-> Float-> (Float, Float) 
sol a b c 
  | disc >= 0  = ((-b + raizDisc)/denom, (-b - raizDisc)/denom) 
  | disc < 0   = error  "raíces complejas"
  where 
    disc= b^2 - 4*a*c  
    raizDisc= sqrt disc 
    denom= 2*a

segundo :: [ Int ] -> Int
segundo (x:y:resto)=y

areacorona :: Float -> Float -> Float
{-areacorona r1 r2 
  | r1 > r2 = ( pi * r1*r1)-(pi*r2*r2)
  | r2 > r1 = ( pi *r2*r2)-(pi*r1*r1)-}
areacorona r1 r2 = abs (pi*(r2^2 -r1^2))

mediano :: Int -> Int -> Int -> Int
mediano a b c = if (a>c && a<b) || (a>b && a<c) then a
                        else if (b>c && b<a) || (b<c && b>a) then b
                        else c

minimo:: Int -> Int -> Int
minimo x y  = (min x y )

{-lcm=mcm-}
voltear :: [t]->[t]
voltear []=[]
voltear ( x:resto)=(voltear resto) ++[x]

voltear2 :: [t]->[t]
voltear2[]=[]
voltear2 x=reverse x

{- [ 1..10]=[1,2,3,4,5,6,7,8,9,10]
head = saca el primero
tail=quita el primero
null = es vacia?
++ = concatenacion
reverse
splitAt 3 [1,2,3,4,5]
([1,2,3],[4,5])
take 3 [4,5,6,7,6]
[4,5,6]
drop 3 [4,5,6,7,6]
[7,6]
sum [1,2,34]
37
replicate 8 'A'
replicate 10 1
[1,1,1,1,1,1,1,1,1]
import Dsta.List(sort)

[x*2 | x <-[1..10], (par x)]
[4,8,12,16,20]

[x|x<-[10..20], x/= 12, x/= 15, x/= 19] 
[10,11,13,14,16,17,18,20]

[x+y | x<-[1, 2, 3], y <-[10, 100, 1000]] 
[11,101,1001,12,102,1002,13,103,1003]

type Fecha = (Int,Int,Int)
x/y= not(x==y)

*Main> read "125" :: Int
125

lamba expresion: es una funcion sin darle un nombre
(\x -> (resto0 3 x)) 27

•Map: Recorre una lista aplicando una función a sus elementos 
map:: (a -> b) -> [a] -> [b] --predefinida
--POST: resultado = [f (11), f (12),  ..., f (1n)]  
mapf [] = []
mapf x:resto = (f x):(mapf resto)
zipWith aplica funcion a dos listas
takeWhile coge elementos de una lista mientras se cumpla una condicion dada
dropwhile igual que take pero si se cumple los quita
even dice si un numero es par
any dice si algun elemento de la lista cumple la condicion
any esMayor5 lista
all todos los elementos la cumplen

$=(.....)
-}
duplicar :: Int -> Int
duplicar x = x * 2

valoresOriginales :: [Int]
valoresOriginales = [1, 2, 3, 4, 5]

valoresDuplicados :: [Int]
valoresDuplicados = fmap duplicar valoresOriginales

cadena :: IO()
cadena = do
                  putStrLn "Introduce una cadena"
                  linea <- getLine
                  putStr ("La cadena: " ++ linea ++ "\t tiene \t")
                  putStr(show (length linea) ++ "caracteres")
                  {-putStrLn " caracteres"-}

{-main :: IO ()
main = mapM_ print [1, 2, 3, 4, 5]-}

mitadParesC :: [Int]->[Int]
mitadParesC l = [x`div`2|x<-l,even x]
