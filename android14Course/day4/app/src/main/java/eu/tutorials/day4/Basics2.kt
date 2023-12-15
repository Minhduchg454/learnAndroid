package eu.tutorials.day4

fun main (){

    // Immutable list - you cannot add items after the initlization
    //val shoppingList = listOf("Processor", "RAM", "Graphics Card", "SSD")

    // mutableListOF - you can add items later and modify
    val shoppingList = mutableListOf("Processor", "RAM", "Graphics Card RTX 3060", "SSD")

    // adding item last to lists
    shoppingList.add("Cooling System")

    // remove item from lists
    shoppingList.remove("Graphics Card RTX 3060")

    shoppingList.add("Graphics Card RTX 4090")


    println(shoppingList)

    //Index number in List begin 0

    //SSD index number is 2
    shoppingList.removeAt(2)
    println(shoppingList)

    //RAM index number is 1
    shoppingList.removeAt(1)
    println(shoppingList)

    //Add items at index
    shoppingList.add(2,"RAM")
    println(shoppingList)

    /*
    [0] [1] [2] [3] [4]
     */
}