module PF_Practica1 (mi_lista, frecuenciasVocales, escaleras, intercalar, JugadasRugby (Ensayo, Transformacion, Drop), resultadoRugby, aproxPi) where


-- Ejercicio 0
frecuenciasVocales :: String -> [Int]
frecuenciasVocales s = [count x s | x <- "aeiou" ]
  where count x s = length [ y | y <- s , y==x]

-- Ejercicio 1                                                        
escaleras :: Int -> [[Int]] 

escaleras 0 = [[0]]
escaleras n = [[n,n-1 .. 0]] ++ escaleras(n-1)

-- Ejercicio 2
intercalar :: [a] -> [a] -> [a]
intercalar [] ys = ys
intercalar xs [] = xs
intercalar (x:xs) (y:ys) = x : y : intercalar xs ys


-- Ejercicio 3
data JugadasRugby = Ensayo | Transformacion | Drop deriving (Show, Eq, Enum)

puntosJugada :: JugadasRugby -> Int
puntosJugada Ensayo = 5
puntosJugada Transformacion = 2
puntosJugada Drop = 3

resultadoRugby :: [(Int, JugadasRugby)]  -> (Int, Int)
resultadoRugby lj  = foldl (\(hc, hv) (eq, jug) -> if eq == 0 then (hc + puntosJugada jug, hv) 
                                                                                  else (hc, hv + puntosJugada jug)) (0, 0) lj

-- Ejercicio 4
aproxPi :: Integer -> Float
aproxPi n = 2 * product [(fromIntegral (2*i) / fromIntegral (2*i-1)) * (fromIntegral (2*i) / fromIntegral (2*i+1)) | i <- [n,n-1..1]]

-- Rellenar con la lista real de los ejercicios resueltos
mi_lista = [0, 1, 2, 3, 4]
                                   
