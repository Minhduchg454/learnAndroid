package eu.tutorials.basicsday2

fun main (){
    println("Please enter a number: ")
    var inputString = readln()
    var inputNumber = inputString.toInt()
    val multiplier = 5 * inputNumber
    println("Result of operation is : $multiplier")


}