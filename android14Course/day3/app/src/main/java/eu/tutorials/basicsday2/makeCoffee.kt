package eu.tutorials.basicsday2

fun main (){
        val coffeeForDenis = CoffeeDetail(0, "Denis", "xxl",0)
        makeCoffee(coffeeForDenis)
        //askCoffeeDetail()
}

fun askCoffeeDetail(){
    println("Who is this coffee for?")
    val name = readln()
    println("How many pieces of sugar do you want?")
    val sugarCount = readln().toInt()
    //(sugarCount,name)
}

fun makeCoffee (coffeeDetail: CoffeeDetail){
    if (coffeeDetail.sugarCount == 0) {
        println("Coffee with no sugar for ${coffeeDetail.name}" + " and cream: ${coffeeDetail.creamAmount}")
    }else if(coffeeDetail.sugarCount == 1){
        println("Coffee with ${coffeeDetail.sugarCount} spoon sugar for $${coffeeDetail.name}"  + " and cream: ${coffeeDetail.creamAmount}")
    }else {
        println("Coffee with ${coffeeDetail.sugarCount} spoons sugar for $${coffeeDetail.name}"  + " and cream: ${coffeeDetail.creamAmount}")
    }
}

data class CoffeeDetail (
    val sugarCount:Int,
    val name:String,
    val size:String,
    val creamAmount: Int
)
