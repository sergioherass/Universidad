import Data.List (group, sort)
import Data.Char (toLower, isSpace, isAlpha)
--import Data.List (group)

-- Contar las sílabas de una palabra
contarSilabas :: String -> Int
contarSilabas palabra = undefined -- Implementar esta función según las reglas para contar sílabas

-- Extraer la rima de una palabra
extraerRima :: String -> String
extraerRima palabra = undefined -- Implementar esta función para extraer la rima de una palabra

-- Clasificar el tipo de rima
tipoRima :: [String] -> String
tipoRima rimas = undefined -- Implementar esta función para clasificar el tipo de rima (asonante o consonante)

-- Contar las sílabas de cada verso
silabasVersos :: [String] -> [Int]
silabasVersos versos = map contarSilabas versos

-- Extraer las rimas de cada verso
rimasVersos :: [String] -> [String]
rimasVersos versos = map extraerRima versos

-- Clasificar la estructura del poema
estructuraPoema :: [Int] -> String
estructuraPoema metrica = undefined -- Implementar esta función para clasificar la estructura del poema

-- Función principal
analizarPoema :: [String] -> (String, String, String)
analizarPoema versos =
  let metrica = silabasVersos versos
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
