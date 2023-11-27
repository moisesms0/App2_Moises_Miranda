package com.example.app2_moisesmiranda

class Numeros {

  fun sumarNumeros(numerosA : Int, numerosB : Int): Int{
     return numerosA + numerosB
 }

    // Guarda todos los nunmeros divisibles y los va sumando para luego comparar
 fun calcularNumerosAmigos(numerosA : Int, numerosB : Int): Boolean{
     var sumaA = 0
     var sumaB = 0
     for (i in 1..numerosA-1){
         if (numerosA % i == 0){
             sumaA += i
         }
     }
     for (i in 1..numerosB-1){
         if (numerosB % i == 0){
             sumaB += i
         }
     }
     return sumaA == numerosB && sumaB == numerosA
 }


}