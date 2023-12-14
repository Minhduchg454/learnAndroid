package eu.tutorials.basicsday2

fun main (){


    /*
    var count = 0
    while (count < 3){
        println ("Count is $count")
        count++
    }
    println ("Loop is done!!")
     */

    println("Entered any: ")
    var userInput = readln()
    while (userInput == "1"){
        println("While loop executed")
        println("Entered any: ")
        userInput = readln()
    }
    println ("Loop is done")
}