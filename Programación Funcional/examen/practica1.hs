esVocal :: Char -> Bool
esVocal x = if x `elem` "aeiou" then True
                                             else False

frecuenciaVocales :: String -> [Int]
frecuenciaVocales s = [ count x s | x <- "aeiou"]
    where count x s = length [ y | y <-s , y ==x]
 

escaleras :: Int -> [[Int]]
escaleras 0=[[0]]
escaleras n = [[n,n-1..0]] ++ escaleras (n-1)


intercalar :: [a] -> [a] -> [a]
intercalar [] ys= ys
intercalar xs [] = xs
intercalar (x:xs) (y:ys) = x:y : intercalar xs ys


-- Ejercicio 3
data JugadasRugby = Ensayo | Transformacion | Drop deriving (Show, Eq, Enum)

puntosJugada :: JugadasRugby -> Int
puntosJugada Ensayo = 5
puntosJugada Transformacion = 2
puntosJugada Drop = 3

resultadoRugby :: [(Int, JugadasRugby)]  -> (Int, Int)
resultadoRugby lj  = foldl (\(hc, hv) (eq, jug) -> if eq == 0 then (hc + puntosJugada jug, hv) 
                                                                                  else (hc, hv + puntosJugada jug)) (0, 0) lj