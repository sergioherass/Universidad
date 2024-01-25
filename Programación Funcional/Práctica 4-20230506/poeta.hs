import Data.List (group, sort)
import Data.Char (toLower, isSpace, isAlpha)
import Data.List (isSuffixOf)
import Data.Char (toLower)
import Data.Char (toLower, isUpper)
import Data.List 


-- Contar las sílabas de una palabra
esVocal :: Char -> Bool
esVocal c = toLower c `elem` "aeiouáéíóúh"

contarVocales :: String -> Int
contarVocales palabra = length $ filter id $ zipWith f (map esVocal palabra) (False : map esVocal palabra)
  where
    f a b = a && not b

-- Extraer la rima de una palabra
extraerRima :: String -> String
extraerRima palabra = undefined -- Implementar esta función para extraer la rima de una palabra

-- Clasificar el tipo de rima
tipoRima :: [String] -> String
tipoRima rimas = undefined -- Implementar esta función para clasificar el tipo de rima (asonante o consonante)

-- Contar las sílabas de cada verso
tipoVerso :: String -> Maybe String
tipoVerso verso
  | numSilabas == 4 = Just "Tetrasilabos"
  | numSilabas == 5 = Just "Pentasilabos"
  | numSilabas == 6 = Just "Hexasilabos"
  | numSilabas == 7 = Just "Heptasilabos"
  | numSilabas == 8 = Just "Octosilabos"
  | numSilabas == 9 = Just "Eneasilabos"
  | numSilabas == 10 = Just "Decasilabos"
  | numSilabas == 11 = Just "Endecasilabos"
  | numSilabas == 14 = Just "Alejandrinos"
  | otherwise = Nothing
  where
    numSilabas = contarVocales verso

-- Extraer las rimas de cada verso
rimasVersos :: [String] -> [String]
rimasVersos versos = map extraerRima versos

-- Clasificar la estructura del poema
estructuraPoema :: [Int] -> String
estructuraPoema metrica = undefined -- Implementar esta función para clasificar la estructura del poema

-- Función principal
analizarPoema :: [String] -> (String, String, String)
analizarPoema versos =
  let metrica = tipoVerso verso
      rimas = rimasVersos versos
      estructura = estructuraPoema metrica
      tipoRimaPoema = tipoRima rimas
  in (estructura, show metrica, tipoRimaPoema)

main :: IO ()
main = do
  let poema = [ "Un soneto me manda hacer Violante,"
              , "que en mi vida me he visto en tanto aprieto;"
              , "catorce versos dicen que es soneto,"
              , "burla burlando van los tres delante."
              ]
  print $ analizarPoema poema
