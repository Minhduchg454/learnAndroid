package eu.tutorials.basicsday2

fun main (){
    askCoffeeDetail()
}

fun askCoffeeDetail(){
    println("Who is this coffee for?")
    val name = readln()
    println("How many pieces of sugar do you want?")
    val sugarCount = readln().toInt()
    makeCoffee(sugarCount,name)
}

fun makeCoffee (sugarCount:Int, name:String){
    if (sugarCount == 0) {
        println("Coffee with no sugar for $name")
    }else if(sugarCount == 1){
        println("Coffee with $sugarCount spoon sugar for $name")
    }else {
        println("Coffee with $sugarCount spoons sugar for $name")
    }
}

