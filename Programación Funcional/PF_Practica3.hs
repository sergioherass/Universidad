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

termino :: (Integer, Integer, Integer) -> Float
termino (i, xn, fact) = fromInteger xn / fromInteger fact

aprox_e :: Integer -> Float
aprox_e x = 1 + sum (takeWhile (\t -> abs t > 1e-4) (map termino (serie x)))

serie :: Integer -> [(Integer, Integer, Integer)]
serie x = itera (\(i, xn, fact) -> (i + 1, xn * x, fact * (i + 1))) (1, x, 1)



-- Ejercicio 2

semilla :: Int
semilla = 1239

f :: Int -> Int
f x = (77 * x + 1) `mod` 1024

aleatorios :: Int -> [Int]
aleatorios sem = iterate f sem

dado :: Int -> Int
dado x = (x `mod` 6) + 1

tirada :: Int -> Int
tirada n = (map dado (aleatorios semilla)) !! n


-- Ejercicio 3

espiral :: [(Integer, Integer)]
espiral = concatMap f [1..]
    where f n = [(signum n * n, 0), (0, -signum n * (n + 1))]

en_circulo :: Integer -> [(Integer, Integer)]
en_circulo r = takeWhile (\(x, y) -> x*x + y*y <= r*r) espiral






