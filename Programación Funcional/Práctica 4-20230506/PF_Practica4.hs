import Data.List (group, sort)
import Data.Char (toLower, isSpace, isAlpha)
import Data.List (isSuffixOf)
import Data.Char (toLower)
import Data.Char (toLower, isUpper)
import Data.List (findIndex)
import Data.List
import Data.Maybe (mapMaybe)
import Data.Maybe (fromMaybe)


--Funciones para el conteo de las silbas de un verso
esVocal :: Char -> Bool
esVocal c = toLower c `elem` "aeiouáéíóúh"

esSinalefa :: Char -> Char -> Bool
esSinalefa c1 c2 = esVocal c1 && esVocal c2

contarVocales :: String -> Int
contarVocales frase = (length . filter esVocal $ sinEspacios)
                    - length (filter (uncurry esSinalefa) $ zip sinEspacios (tail sinEspacios))
  where
    sinEspacios = filter (/=' ') frase



--Funciones para averiguar el tipo de rima entre dos versos
tipoRima :: String -> String -> String
tipoRima verso1 verso2
  | ultimaPalabra verso1 `rimaConsonante` ultimaPalabra verso2 = "Rima consonante"
  | ultimaPalabra verso1 `rimaAsonante` ultimaPalabra verso2 = "Rima asonante"
  | otherwise = "No hay rima"

longitudSufijo :: String -> Int
longitudSufijo palabra = case clasificarPalabra palabra of
  Just Aguda    -> 2 --Asumo que si es aguda deberian ser iguales los 2 ultimos caracteres del sufijo
  Just Llana    -> 3 --Asumo que si es aguda deberian ser iguales los 3 ultimos caracteres del sufijo
  Just Esdrújula -> 3 --Asumo que si es aguda deberian ser iguales los 3 ultimos caracteres del sufijo
  Nothing       -> 0  

rimaConsonante :: String -> String -> Bool
rimaConsonante palabra1 palabra2 =
  let n = max (longitudSufijo palabra1) (longitudSufijo palabra2)
  in lastN n palabra1 `isSuffixOf` lastN n palabra2

rimaAsonante :: String -> String -> Bool
rimaAsonante palabra1 palabra2 =
  let n = max (longitudSufijo palabra1) (longitudSufijo palabra2)
  in lastN n palabra1 `isSuffixOf` lastN n palabra2

-- Función auxiliar para obtener la última palabra de un verso
ultimaPalabra :: String -> String
ultimaPalabra verso = last (words verso)

-- Función auxiliar para obtener los últimos n caracteres de una cadena
lastN :: Int -> [a] -> [a]
lastN n = reverse . take n . reverse




--Funcion para sacar el tipo de verso con respecto al numero de sílabas
tipoVerso :: String -> Maybe String
tipoVerso verso
  | numSilabas == 4 = Just "Arte menor (Tetrasilabos)"
  | numSilabas == 5 = Just "Arte menor (Pentasilabos)"
  | numSilabas == 6 = Just "Arte menor (Hexasilabos)"
  | numSilabas == 7 = Just "Arte menor (Heptasilabos)"
  | numSilabas == 8 = Just "Arte menor (Octosilabos)"
  | numSilabas == 9 = Just "Arte Mayor (Eneasilabos)"
  | numSilabas == 10 = Just "Arte Mayor (Decasilabos)"
  | numSilabas == 11 = Just "Arte Mayor (Endecasilabos)"
  | numSilabas == 14 = Just "Arte Mayor (Alejandrinos)"
  | otherwise = Nothing
  where
    numSilabas = contarVocales verso

--Funcion para realizar tipoVerso sobre un conjunto de versos
tiposDeVersos :: String -> [Maybe String]
tiposDeVersos poema = map tipoVerso (lines poema)


--Funcion que cuenta el numero de versos elimnando las lineas vacias
numeroDeVersos :: [String] -> Int
numeroDeVersos poema = length $ filter (not . null) poema



--Funcion para sacar el caracter que lleva el acento
indiceCaracterAcentuado :: String -> Maybe Int
indiceCaracterAcentuado palabra = aux (reverse $ map toLower palabra) 1
  where
    esCaracterAcentuado c = c `elem` "áéíóú"
    aux [] _ = Nothing
    aux (c:cs) i
      | esCaracterAcentuado c = Just i
      | otherwise = aux cs (i+1)


--Funcion para saber si una palabra es esdrújula
esdrújula2 :: String -> Bool
esdrújula2 palabra = case indiceCaracterAcentuado palabra of
                        Just n  -> n >3 --Asumo que las esdrujulas deben tener la tilde más allá del tercer caracter empezando por el final de la palabra
                        Nothing -> False


data TipoPalabra = Aguda | Llana | Esdrújula deriving Show

--Funcion para sacar si una palabra es Aguda,llana o esdrújula asumiendo las reglas basicas
clasificarPalabra :: String -> Maybe TipoPalabra
clasificarPalabra palabra
  | esAguda palabra = Just Aguda
  | esLlana palabra = Just Llana
  | esEsdrújula palabra = Just Esdrújula
  | otherwise = Nothing
  where
    esAguda p = (not(esdrújula2 p ) && (terminaEnVocal p || terminaEnNS p) ) || ( all (`notElem` "áéíóú") (map toLower p) && not(terminaEnVocal p || terminaEnNS p) )
    esLlana p = ( esUltimaSilabaAcentuada p && terminaEnConsonanteNoNS p ) || ( all (`notElem` "áéíóú") (map toLower p) && (terminaEnVocal p || terminaEnNS p) )
    esEsdrújula p = esdrújula2 p
    esUltimaSilabaAcentuada = any (`elem` "áéíóúÁÉÍÓÚ") . last . palabras
    terminaEnVocal = (`elem` "aeiouáéíóúAEIOUÁÉÍÓÚ") . last . quitarAcentos
    terminaEnNS p = last (quitarAcentos p) `elem` "nsNS"
    terminaEnConsonanteNoNS p = last (quitarAcentos p) `notElem` "aeiouáéíóúAEIOUÁÉÍÓÚnsNS"
    quitarAcentos = map quitarAcento
    quitarAcento c
      | c `elem` "áÁ" = 'a'
      | c `elem` "éÉ" = 'e'
      | c `elem` "íÍ" = 'i'
      | c `elem` "óÓ" = 'o'
      | c `elem` "úÚ" = 'u'
      | otherwise = c
    palabras = words . map toLower

--Funcion para saber la metrica de los versos
modoDeRima :: [String] -> Maybe String
modoDeRima versos
  | length versos == 2 && rimaAA = Just "Rima AA"
  | length versos == 2 && rimaaa = Just "Rima aa"
  | length versos == 3 && rimaABA = Just "Rima ABA"
  | length versos == 4 && rimaABBA = Just "Rima ABBA"
  | length versos == 4 && rimaABAB = Just "Rima ABAB"
  | length versos == 4 && rimaabba = Just "Rima abba"
  | length versos == 4 && rimaabab = Just "Rima abab"
  | length versos == 4 && rimaAAAA = Just "Rima AAAA"
  | length versos == 5 && rimaABABA = Just "Rima ABABA"
  | length versos == 5 && rimaababa = Just "Rima ababa"
  | rimaABABABCC = Just "Rima ABABABCC"
  | rimaabbaaccddc = Just "Rima abbaaccddc"
  | otherwise = Nothing
  where
    todosVersos11Silabas = all (\verso -> contarVocales verso == 11) versos
    todosVersos8Silabas = all (\verso -> contarVocales verso == 8) versos
    rimaAA = todosVersos11Silabas && tipoRima (versos !! 0) (versos !! 1) == "Rima consonante"
    rimaaa = todosVersos8Silabas && tipoRima (versos !! 0) (versos !! 1) == "Rima consonante"
    rimaABA = todosVersos11Silabas && tipoRima (versos !! 0) (versos !! 2) == "Rima consonante"
    rimaABBA = todosVersos11Silabas && tipoRima (versos !! 0) (versos !! 3) == "Rima consonante" &&
               tipoRima (versos !! 1) (versos !! 2) == "Rima consonante"
    rimaABAB = todosVersos11Silabas && tipoRima (versos !! 0) (versos !! 2) == "Rima consonante" &&
               tipoRima (versos !! 1) (versos !! 3) == "Rima consonante"
    rimaAAAA = todosVersos11Silabas && all (== "Rima consonante") [ tipoRima v1 v2 | v1 <- versos, v2 <- versos, v1 /= v2 ]
    rimaabba = todosVersos8Silabas && tipoRima (versos !! 0) (versos !! 3) == "Rima consonante" &&
               tipoRima (versos !! 1) (versos !! 2) == "Rima consonante"
    rimaabab = todosVersos8Silabas && tipoRima (versos !! 0) (versos !! 2) == "Rima consonante" &&
               tipoRima (versos !! 1) (versos !! 3) == "Rima consonante"
    rimaABABA = todosVersos11Silabas && tipoRima (versos !! 0) (versos !! 2) == "Rima consonante" &&
               tipoRima (versos !! 1) (versos !! 3) == "Rima consonante" && 
               tipoRima (versos !! 0) (versos !! 4) == "Rima consonante" 
    rimaababa = todosVersos8Silabas && tipoRima (versos !! 0) (versos !! 2) == "Rima consonante" &&
               tipoRima (versos !! 1) (versos !! 3) == "Rima consonante" && 
               tipoRima (versos !! 0) (versos !! 4) == "Rima consonante" 
    rimaABABABCC = length versos == 8
    rimaabbaaccddc = length versos == 10

--Funcion para dividir el poema que se le pasa a modoDeRima en parrafos para poder clasificar cada parrafo
dividirEnParrafos :: [String] -> [[String]]
dividirEnParrafos [] = []
dividirEnParrafos xs = parrafo : dividirEnParrafos (dropWhile null resto)
  where
    (parrafo, resto) = break null xs

--Funcion para aplicar modoDeRima a cada parrafo
modosDeRima :: [String] -> [Maybe String]
modosDeRima poema = map modoDeRima (dividirEnParrafos poema)


--Funcion que devuelve si la rima es consonante cuando encuentre una metrica válida
esConsonante :: [String] -> Maybe String
esConsonante versos
  | any (\rima -> rima == Just "Rima ABBA" || rima == Just "Rima ABAB" || rima == Just "Rima AAAA" 
            || rima == Just "Rima AA" || rima == Just "Rima aa"  
            || rima == Just "Rima abab" || rima == Just "Rima abba" || rima == Just "Rima ABA" || rima == Just "Rima ABABA" || rima == Just "Rima ababa" 
            || rima == Just "Rima ABABABCC" || rima == Just "Rima abbaaccddc" ) result
  = Just "Rima Consonante"
  | otherwise = Nothing
  where result = modosDeRima versos


--Funcion que cuenta el numero de parrafos del poema
numeroDeParrafos :: String -> Int
numeroDeParrafos poema = length (dividirEnParrafos (lines poema))

{-separaParrafos :: [String] -> [[String]]
separaParrafos [] = []
separaParrafos xs = parrafo : (separaParrafos $ dropWhile null rest)
  where
    (parrafo, rest) = break null xs-}

--Funcion para saber el tipo de poema 
data TipoPoema = Pareado | Terceto | Cuarteto | Redondilla | Serventesio | Cuarteta | CuadernaVia | Quinteto | Quintilla | OctavaReal | Décima | Soneto deriving (Show,Eq)
clasificarPoema :: String -> Maybe TipoPoema
clasificarPoema poema
  | numVersos == 2 && todosVersos11Silabas && modoDeRima versos == Just "Rima AA"= Just Pareado
  | numVersos == 2 && todosVersos8Silabas && modoDeRima versos == Just "Rima aa"= Just Pareado
  | numVersos == 3 && todosVersos11Silabas && modoDeRima versos == Just "Rima ABA" = Just Terceto
  | numVersos == 4 && todosVersos11Silabas && modoDeRima versos == Just "Rima ABBA" = Just Cuarteto
  | numVersos == 4 && todosVersos8Silabas && modoDeRima versos == Just "Rima abba"= Just Redondilla
  | numVersos == 4 && todosVersos11Silabas && modoDeRima versos == Just "Rima ABAB" = Just Serventesio
  | numVersos == 4 && todosVersos8Silabas && modoDeRima versos == Just "Rima abab" = Just Cuarteta
  | numVersos == 4 && todosVersos14Silabas && modoDeRima versos == Just "Rima AAAA"= Just CuadernaVia
  | numVersos == 5 && todosVersos11Silabas && modoDeRima versos == Just "Rima ABABA"= Just Quinteto
  | numVersos == 5 && todosVersos8Silabas && modoDeRima versos == Just "Rima ababa"= Just Quintilla
  | numVersos == 8 && todosVersos11Silabas = Just OctavaReal
  | numVersos == 10 && todosVersos8Silabas = Just Décima
  | numVersos == 14 && todosVersos11Silabas = Just Soneto
  | otherwise = Nothing
  where
    versos = lines poema
    numVersos = length versos
    todosVersos11Silabas = all (\verso -> contarVocales verso == 11) versos
    todosVersos8Silabas = all (\verso -> contarVocales verso == 8) versos
    todosVersos14Silabas = all (\verso -> contarVocales verso == 14) versos


--Funcion para saber el tipo de estrofa de cada parrafo
clasificarParrafos :: String -> [Maybe TipoPoema]
clasificarParrafos poema = map (clasificarPoema . unlines) (dividirEnParrafos (lines poema))


--Funcion main con una décima de ejemplo. Recoge todas las funciones creadas anteriormente
main :: IO ()
main = do
  let poema1 =[ "Qué prueba de la existencia"
               , "habrá mayor que la suerte"
               , "de estar viviendo sin verte"
               , "y muriendose en tu presencia"
               , "Esta lúcida conciencia"
               , "de amar a lo menos visto"
               , "y de esperarse lo imprevisto"
               , "este caerse mal sin llegar"
               , "es esa angustia de pensar"
               , "que puesto que muero existo"]

  let numeroVersos = numeroDeVersos poema1
  putStrLn $ "El poema tiene: " ++ show numeroVersos ++ " versos"

  case tipoVerso (filter (/= ',') (head poema1)) of
    Just tipo -> putStrLn $ "El poema está escrito en: " ++ show tipo
    Nothing -> return ()

  let poemaSinComas = map (filter (/= ',')) poema1

  putStrLn "La clasificación de los párrafos es:"
  mapM_ (\parrafo -> case parrafo of
    Just p -> putStrLn $ "- " ++ show p
    Nothing -> return ()) (clasificarParrafos (unlines poemaSinComas))

  let poemaSinComas2 = map (filter (/= ',')) poema1
  let poemaSinLineasVacias = filter (not . null) poemaSinComas2

  case clasificarPoema (unlines poemaSinLineasVacias) of
    Just estructura -> putStrLn $ "La estructura del poema es: " ++ show estructura
    Nothing -> return ()

  putStrLn "Sigue un esquema métrico de:"
  mapM_ (\esquema -> case esquema of
    Just e -> putStrLn $ "- " ++ show e
    Nothing -> return ()) (modosDeRima poema1)

  case esConsonante poema1 of
    Just rima -> putStrLn $ "La rima es: " ++ rima
    Nothing -> return ()


