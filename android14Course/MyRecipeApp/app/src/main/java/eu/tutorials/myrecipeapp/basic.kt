package eu.tutorials.myrecipeapp

fun main(){
    var number:Int
    println("Please, enter a number: ")
    try {
        number=readln().toInt();
        println("User enter number $number")
    }catch (e: Exception){
        println("error ${e.message}")
    }finally {
        number=0;
    }
    println("User enter number $number")
}