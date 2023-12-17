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

    println(shoppingList[3])
    shoppingList[3] = "Graphics Card RX 7800XT"
    println(shoppingList)

    //Replace items

    shoppingList.set(1,"Water Cooling")
    println(shoppingList)


    //Check items in shopping: Boolean
    val hasRam = shoppingList.contains("RAM")
    if (hasRam){
        println("Has Ram in list")
    }else{
        println("No Ram in List")
    }

    //Method 1
    //Print item in List

    println("\nItem in list: ")
    for (item in shoppingList){
        println("+ $item")
        if (item == "Water Cooling"){
            shoppingList.removeLast()
            break
        }
    }
    println(shoppingList)

    //Method 2
    //Print index in List
    //Return index
    println("\n" + shoppingList.size)
    println("Item in list: ")
    for (index in 0 until shoppingList.size){
        println(" +item ${shoppingList[index]} is at index $index")
    }
    println(shoppingList)

    //Method 3
    println("\nItem in list: ")
    for(index in 0..(shoppingList.size-1)){
        println(" +item ${shoppingList[index]} is at index $index")
    }
}