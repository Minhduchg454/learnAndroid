package eu.tutorials.day4

fun main (){

    // Immutable list - you cannot add items after the initlization
    //val shoppingList = listOf("Processor", "RAM", "Graphics Card", "SSD")

    // mutableListOF - you can add items later and modify
    val shoppingList = mutableListOf("Processor", "RAM", "Graphics Card RTX 3060", "SSD")

    // adding item to lists
    shoppingList.add("Cooling System")

    // remove item from lists
    shoppingList.remove("Graphics Card RTX 3060")

    shoppingList.add("Graphics Card RTX 4090")

    println(shoppingList)
}