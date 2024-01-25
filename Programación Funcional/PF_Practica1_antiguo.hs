module PF_Practica1 (mi_lista, frecuenciasVocales, escaleras, JugadasRugby (Ensayo, Transformacion, Drop), resultadoRugby, aproxPi) where


-- Ejercicio 0
frecuenciasVocales :: String -> [Int]
frecuenciasVocales s = [count x s | x <- "aeiou" ]
  where count x s = length [ y | y <- s , y==x]

-- Ejercicio 1                                                        
escaleras :: Int -> [[Int]] 
escaleras 0 = [[0]]
escaleras n = [[n,n-1 .. 0]] ++ escaleras(n-1)

-- Ejercicio 2 // mirar pq no me sale bien
intercalar:: [a] -> [a] -> [a]
intercalar iz der = [x | (x,y) <- zip iz der] ++ if length iz >= length der then drop (length der) iz else drop (length iz) der


-- Ejercicio 3 //esta mal 
data JugadasRugby = Ensayo | Transformacion | Drop

resultadoRugby :: [(Int, JugadasRugby)]  -> (Int, Int)
resultadoRugby lj  = ( puntos 0 lj, puntos 1 lj)
where puntos equipo xs = sum [puntosJugada | (e, j) <- xs, e == equipo, let puntosJugada = puntosJugada equipo j]
        puntosJugada 0 Ensayo = 5
        puntosJugada 1 Ensayo = 5
        puntosJugada 0 Transformacion = 2
        puntosJugada 1 Transformacion = 2
        puntosJugada 0 Drop = 3
        puntosJugada 1 Drop = 3

-- Ejercicio 4 //mal
aproxPi :: Integer -> Float
aproxPi n = foldl (*) (2 :: Float) (map (\x -> (2 * x) / (2 * x - 1) * (2 * x) / (2 * x + 1)) [1..n])

-- Rellenar con la lista real de los ejercicios resueltos
mi_lista = [0, 1, 2, 3, 4]
                                   

