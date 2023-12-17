package eu.tutorials.day4

fun main (){
    val fruitsList = mutableListOf("Apple", "Banana", "Orange", "Cherry", "Mango")
    println(fruitsList)
    /*
    println("Name fruit add: ")
    var newFruit = readln();

     */
    fruitsList.add("Kiwi")
    println(fruitsList)

    fruitsList.removeAt(1)
    println(fruitsList)

    println("Name fruits check: ")
    var checkFruits = readln()
    if (fruitsList.contains(checkFruits)){
        println("Has fruit in List")
    }else{
        println("No fruit in List")

    }


}