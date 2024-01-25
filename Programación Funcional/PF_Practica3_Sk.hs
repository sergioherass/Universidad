module PF_Practica3 (mi_lista, itera, orbita, collatz, aprox_e, serie, termino, aleatorios, semilla, tirada, espiral, en_circulo) where

------------------------------------------------------------------------
-- Aquí se indican los ejercicios realizados.
-- Modificar la lista acorde a lo que se entrega
------------------------------------------------------------------------
mi_lista :: [Int]
mi_lista = [0, 1, 2, 3]

-- Practica 3 - comun
itera :: (a -> a) -> a -> [a]
itera f x = x : itera f (f x)


-- Ejercicio 0
orbita :: Integer -> [Integer]
orbita n = itera (\x -> if even x then x `div` 2 else 3*x+1) n

collatz :: Integer -> [Integer]
collatz n = takeWhile (/= 1) $ itera (\x -> if even x then x `div` 2 else 3*x+1) n

-- Ejercicio 1

termino :: (Float, Float, Float) -> Float
termino (x, y, z) = y / z

aprox_x :: Float -> Float
aprox_x x = sum $ takeWhile (>0.0001) $ map termino $ serie x

serie :: Float -> [(Float, Float, Float)]
serie x = zip3 (itera (+1) 1) (itera (*x) 1) (itera (*1) 1)



-- Ejercicio 2

semilla :: Int
semilla = 1239

aleatorios :: Int-> [Int]
aleatorios sem = [] 

tirada :: Int -> Int
tirada n = 0

-- Ejercicio 3

espiral :: [(Integer, Integer)]
espiral = [(1, 0)]

en_circulo ::  Integer -> [(Integer, Integer)]
en_circulo r = [(1, 0)]

-- Ejercicio 2

f :: Int -> Int
f x = (77 * x + 1) `mod` 1024

semilla :: Int
semilla = 1239

aleatorios :: Int -> [Int]
aleatorios semilla = itera f semilla

dado :: Int -> Int
dado x = x `mod` 6 + 1

tiradas :: Int -> [Int]
tiradas semilla = map dado (aleatorios semilla)

tirada :: Int -> Int
tirada n = (tiradas n) !! n

-- Ejercicio 3

espiral :: [(Integer, Integer)]
espiral = itera siguiente (1, 0)
  where
    siguiente (x, y)
      | y >= 0 && abs x <= abs y = (x - signum y, y)
      | y <  0 && abs x <  abs y = (x - signum y, y)
      | x <= 0 && abs y <= abs x = (x, y + signum x)
      | x >  0 && abs y <  abs x = (x, y + signum x)


distanciaOrigen :: (Integer, Integer) -> Integer
distanciaOrigen (x, y) = x * x + y * y

en_circulo :: Integer -> [(Integer, Integer)]
en_circulo r = takeWhile (\p -> distanciaOrigen p <= r * r) espiral

