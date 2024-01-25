R version 4.0.4 (2021-02-15) -- "Lost Library Book"
Copyright (C) 2021 The R Foundation for Statistical Computing
Platform: x86_64-w64-mingw32/x64 (64-bit)

R is free software and comes with ABSOLUTELY NO WARRANTY.
You are welcome to redistribute it under certain conditions.
Type 'license()' or 'licence()' for distribution details.

R is a collaborative project with many contributors.
Type 'contributors()' for more information and
'citation()' on how to cite R or R packages in publications.

Type 'demo()' for some demos, 'help()' for on-line help, or
'help.start()' for an HTML browser interface to help.
Type 'q()' to quit R.

> load("~/Universidad/proba2/datos (9).RData")
> load package Rcmdr
Error: unexpected symbol in "load package"
>  load package Rcmdr::
  Error: unexpected symbol in " load package"
> datos
BANCOS COMISION BENEFICIO
1     320     2.81     58.04
2    1920     5.96     27.27
3   25030   131.77   1070.16
4   19000   124.99   1092.73
5   43930   243.67   1016.55
6    3010    16.37    130.10
7    2790     8.67     60.64
8   12780    52.61    776.77
9    7810    39.95    684.69
10   2190     5.99     27.63
11    440     0.72     13.25
12   2110    16.49    205.66
13    740     2.37     33.83
14   1660     4.59     16.21
15    170     0.56      6.09
16   3930    11.44     67.93
17   3400     9.78    122.47
18   9010    56.42    464.56
19    980     3.53     28.03
20   1800     5.97     28.94
21  13400    53.75    403.67
22  18420   122.07    654.75
23    460     1.23     19.08
24   4930    19.42    134.48
25  38970   189.86   1270.38
26    930     3.17     30.35
27   4650    14.42     99.41
28  38150   216.32   2155.11
29    820     3.58     63.16
30   6530    20.04     93.90
31   2870     9.63     50.80
32   1520    11.89    234.37
33  48700   259.02   1107.78
34   8430    54.10    357.79
35   4140    16.53     85.35
36   1560     3.87     30.85
37    580     5.16     29.13
38   1980     4.66    322.58
39  55540   287.96   1131.12
40   3520    11.41    230.63
41   1380     7.19     93.18
42  21010    90.48    361.09
43   1780     5.23     15.88
44   5910    29.95    514.61
45   9690    95.03    659.44
46    230    12.54    125.70
47   5680    48.26    407.95
48   8050    35.33    463.24
49  13200    70.14    671.67
50   2190    13.89    123.50
> conjunto de datos activos
Error: unexpected symbol in "conjunto de"
> Load package MASS (Paquetes - Cargar paquete MASS)
Error: unexpected symbol in "Load package"
> library(Rcmdr)
Loading required package: splines
Loading required package: RcmdrMisc
Loading required package: car
Loading required package: carData
Loading required package: sandwich
Loading required package: effects
lattice theme set by effectsTheme()
See ?effectsTheme for details.
RcmdrMsg: [1] NOTE: R Commander Version 2.7-1: Mon May 02 16:54:43 2022

Rcmdr Version 2.7-1


Attaching package: 'Rcmdr'

The following object is masked from 'package:base':
  
  errorCondition

RcmdrMsg: [2] ERROR: Nothing is selected.
> library(MASS, lib.loc = "C:/Program Files/R/R-4.0.4/library")
> detach("package:MASS", unload = TRUE)
Warning message:
  'MASS' namespace cannot be unloaded:
  namespace 'MASS' is imported by 'lme4', 'RcmdrMisc' so cannot be unloaded 
> library(MASS, lib.loc = "C:/Program Files/R/R-4.0.4/library")
> detach("package:Rcmdr", unload = TRUE)
> library(Rcmdr)
RcmdrMsg: [1] NOTE: R Commander Version 2.7-1: Mon May 02 16:56:44 2022

Rcmdr Version 2.7-1


Attaching package: 'Rcmdr'

The following object is masked from 'package:base':
  
  errorCondition

> load
function (file, envir = parent.frame(), verbose = FALSE) 
{
  if (is.character(file)) {
    con <- gzfile(file)
    on.exit(close(con))
    magic <- readChar(con, 5L, useBytes = TRUE)
    if (!length(magic)) 
      stop("empty (zero-byte) input file")
    if (!grepl("RD[ABX][2-9]\n", magic)) {
      if (grepl("RD[ABX][2-9]\r", magic)) 
        stop("input has been corrupted, with LF replaced by CR")
      warning(sprintf("file %s has magic number '%s'\n", 
                      sQuote(basename(file)), gsub("[\n\r]*", 
                                                   "", magic)), "  ", "Use of save versions prior to 2 is deprecated", 
              domain = NA, call. = FALSE)
      return(.Internal(load(file, envir)))
    }
  }
  else if (inherits(file, "connection")) {
    con <- if (inherits(file, "gzfile") || inherits(file, 
                                                    "gzcon")) 
      file
    else gzcon(file)
  }
  else stop("bad 'file' argument")
  if (verbose) 
    cat("Loading objects:\n")
  .Internal(loadFromConn2(con, envir, verbose))
}
<bytecode: 0x000000000b33e568>
  <environment: namespace:base>
  > hist(datos1, freq=F, main="Histograma", col="darkgray")
Error in hist(datos1, freq = F, main = "Histograma", col = "darkgray") : 
  object 'datos1' not found
> hist(datos, freq=F, main="Histograma", col="darkgray")
> Hist(datos, scale="density",  main="Histograma", col="darkgray")
> Hist(datos, scale="density",  main="Histograma", col="darkgray")
> fitdistr(datos, "gamma")  
Error in fitdistr(datos, "gamma") : 
  'x' must be a non-empty numeric vector
> datos1=datos[,5]
Error in `[.data.frame`(datos, , 5) : undefined columns selected
> datos1=datos[,0]
> > hist(datos1, freq=F, main="Histograma", col="darkgray")
Error: unexpected '>' in ">"
>  hist(datos1, freq=F, main="Histograma", col="darkgray")
> datos1=datos[,3]
> hist(datos1, freq=F, main="Histograma", col="darkgray")
> datos1=datos[,2]
> hist(datos1, freq=F, main="Histograma", col="darkgray")
> hist(datos1, freq=F, main="Histograma", col="darkgray")
> datos1=datos[,1]
> hist(datos1, freq=F, main="Histograma", col="darkgray")
> datos2=datos[,1]
> hist(datos2, freq=F, main="Histograma", col="darkgray")
> datos2=datos[,2]
> datos3=datos[,3]
> 
  > Hist(datos1, scale="density",  main="Histograma", col="darkgray")
> Hist(datos2, scale="density",  main="Histograma", col="darkgray")
> Hist(datos3, scale="density",  main="Histograma", col="darkgray")
> Hist(datos2, scale="density",  main="Histograma", col="darkgray")
> Hist(datos1, scale="density",  main="Histograma", col="darkgray")
> fitdistr(datos1, "gamma")
Warning in densfun(x, parm[1], parm[2], ...) : NaNs produced
Warning in densfun(x, parm[1], parm[2], ...) : NaNs produced
Error in stats::optim(x = c(320, 1920, 25030, 19000, 43930, 3010, 2790,  : 
                              non-finite finite-difference value [1]
                            > fitdistr(datos1, "normal") 
                            mean         sd    
                            9364.800   13350.229 
                            ( 1888.008) ( 1335.023)
                            > fitdistr(datos1, "lognormal") 
                            meanlog      sdlog  
                            8.2266624   1.4310037 
                            (0.2023745) (0.1431004)
                            > fitdistr(datos2, "gamma")  
                            shape         rate    
                            0.585376436   0.011894070 
                            (0.097895522) (0.002951153)
                            > fitdistr(datos2, "normal") 
                            mean         sd    
                            49.215800   72.404069 
                            (10.239482) ( 7.240407)
                            > fitdistr(datos2, "lognormal") 
                            meanlog      sdlog  
                            2.8368293   1.5505963 
                            (0.2192874) (0.1550596)
                            > fitdistr(datos3, "gamma") 
                            shape           rate    
                            0.6821258983   0.0019083122 
                            (0.1063202921) (0.0003595666)
                            > fitdistr(datos3, "normal")
                            mean         sd    
                            357.45000   443.06926 
                            ( 62.65946) ( 44.30693)
                            > fitdistr(datos3, "lognormal") 
                            meanlog      sdlog  
                            4.9898027   1.4727623 
                            (0.2082800) (0.1472762)
                            > fitdistr(datos1, "gamma")
                            Warning in densfun(x, parm[1], parm[2], ...) : NaNs produced
                            Warning in densfun(x, parm[1], parm[2], ...) : NaNs produced
                            Error in stats::optim(x = c(320, 1920, 25030, 19000, 43930, 3010, 2790,  : 
                                                          non-finite finite-difference value [1]
                                                        > x=seq(0, 60000, 0.1)
                                                        > lines(x, dgamma(x,0.585376436, 0.011894070), col="red")
                                                        Error in plot.xy(xy.coords(x, y), type = type, ...) : 
                                                          plot.new has not been called yet
                                                        > lines(x, dnorm(x,49.215800, 72.404069), col="blue")
                                                        Error in plot.xy(xy.coords(x, y), type = type, ...) : 
                                                          plot.new has not been called yet
                                                        > lines(x, dlnorm(x,2.8368293, 1.5505963), col="cyan")
                                                        Error in plot.xy(xy.coords(x, y), type = type, ...) : 
                                                          plot.new has not been called yet
                                                        > ks.test(datos2, pgamma,0.585376436, 0.011894070) 
                                                        
                                                        One-sample Kolmogorov-Smirnov test
                                                        
                                                        data:  datos2
                                                        D = 0.17694, p-value = 0.07696
                                                        alternative hypothesis: two-sided
                                                        
                                                        > ks.test(datos2, pnorm, 49.215800, 72.404069) 
                                                        
                                                        One-sample Kolmogorov-Smirnov test
                                                        
                                                        data:  datos2
                                                        D = 0.27651, p-value = 0.0007166
                                                        alternative hypothesis: two-sided
                                                        
                                                        > ks.test(datos2, plnorm, 2.8368293, 1.5505963) 
                                                        
                                                        One-sample Kolmogorov-Smirnov test
                                                        
                                                        data:  datos2
                                                        D = 0.088143, p-value = 0.7998
                                                        alternative hypothesis: two-sided
                                                        
                                                        > #elegimos la distribucion lognormal para datos2
                                                          > 
                                                          > ks.test(datos3, pgamma,0.6821258983, 0.0019083122) 
                                                        
                                                        One-sample Kolmogorov-Smirnov test
                                                        
                                                        data:  datos3
                                                        D = 0.14525, p-value = 0.2197
                                                        alternative hypothesis: two-sided
                                                        
                                                        > ks.test(datos3, pnorm, 357.45000, 443.06926) 
                                                        
                                                        One-sample Kolmogorov-Smirnov test
                                                        
                                                        data:  datos3
                                                        D = 0.2326, p-value = 0.007323
                                                        alternative hypothesis: two-sided
                                                        
                                                        > ks.test(datos3, plnorm, 4.9898027, 1.4727623) 
                                                        
                                                        One-sample Kolmogorov-Smirnov test
                                                        
                                                        data:  datos3
                                                        D = 0.10721, p-value = 0.5764
                                                        alternative hypothesis: two-sided
                                                        
                                                        > #elegimos la lognomal para datos3
                                                          > 
                                                          > ks.test(datos1, pnorm, 9364.800, 13350.229) 
                                                        Warning in ks.test(datos1, pnorm, 9364.8, 13350.229) :
                                                          ties should not be present for the Kolmogorov-Smirnov test
                                                        
                                                        One-sample Kolmogorov-Smirnov test
                                                        
                                                        data:  datos1
                                                        D = 0.2506, p-value = 0.003746
                                                        alternative hypothesis: two-sided
                                                        
                                                        > ks.test(datos1, plnorm, 8.2266624, 1.4310037) 
                                                        Warning in ks.test(datos1, plnorm, 8.2266624, 1.4310037) :
                                                          ties should not be present for the Kolmogorov-Smirnov test
                                                        
                                                        One-sample Kolmogorov-Smirnov test
                                                        
                                                        data:  datos1
                                                        D = 0.065748, p-value = 0.9821
                                                        alternative hypothesis: two-sided
                                                        
                                                        > #elegimos la lognormal para datos1???(falta la gamma)
                                                          > 
                                                          > 
                                                          > 
                                                          > 
                                                          > #Apartado2
                                                          > 
                                                          > 
                                                          > install.packages("Rcmdr")
                                                        WARNING: Rtools is required to build R packages but is not currently installed. Please download and install the appropriate version of Rtools before proceeding:
                                                          
                                                          https://cran.rstudio.com/bin/windows/Rtools/
                                                          Warning in install.packages :
                                                          package 'Rcmdr' is in use and will not be installed
                                                        > install.packages("MASS", lib="C:/Program Files/R/R-4.0.4/library")
                                                        Error in install.packages : Updating loaded packages
                                                        > install.packages("MASS", lib = "C:/Program Files/R/R-4.0.4/library")
                                                        WARNING: Rtools is required to build R packages but is not currently installed. Please download and install the appropriate version of Rtools before proceeding:
                                                          
                                                          https://cran.rstudio.com/bin/windows/Rtools/
                                                          Warning in install.packages :
                                                          package 'MASS' is in use and will not be installed
                                                        > 