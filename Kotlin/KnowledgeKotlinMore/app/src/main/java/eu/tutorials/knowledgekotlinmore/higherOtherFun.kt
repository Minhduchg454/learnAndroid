package eu.tutorials.knowledgekotlinmore

class Cookie(
    val name: String,
    val softBaked: Boolean,
    val hasFilling: Boolean,
    var price: Double
)

val cookies= listOf(
    Cookie(
        name = "Chocolate Chip",
        softBaked = false,
        hasFilling = false,
        price = 1.69
    ),
    Cookie(
        name = "Banana Walnut",
        softBaked = true,
        hasFilling = false,
        price = 1.49
    ),
    Cookie(
        name = "Vanilla Creme",
        softBaked = false,
        hasFilling = true,
        price = 1.59
    ),
    Cookie(
        name = "Chocolate Peanut Butter",
        softBaked = false,
        hasFilling = true,
        price = 1.49
    ),
    Cookie(
        name = "Snickerdoodle",
        softBaked = true,
        hasFilling = false,
        price = 1.39
    ),
    Cookie(
        name = "Blueberry Tart",
        softBaked = true,
        hasFilling = true,
        price = 1.79
    ),
    Cookie(
        name = "Sugar and Sprinkles",
        softBaked = false,
        hasFilling = false,
        price = 1.39
    )
)


fun main (){

   //ForEach

    /*
    cookies.forEach{
        println("Menu item: ${it.name}")
    }
     */


    /*
    //Map
    val fullMenu= cookies.map {
        "${it.name} - ${it.price}"
    }
    println("Full menu")
    fullMenu.forEach {
        println(it)
    }

    */


    /*
    val softBakedMenu = cookies.filter {
        it.softBaked
    }

    println("Soft cookies:")
    softBakedMenu.forEach {
        println("${it.name} - $${it.price}")
    }

    val chipMenu = cookies.filter {
        it.price<=1.5
    }

    println("Chip cookies:")
    chipMenu.forEach {
        println("${it.name} - $${it.price}")
    }
    */


    /*
    val groupedMenu = cookies.groupBy {
        it.softBaked
    }

    val softBakedMenu = groupedMenu[true] ?: listOf()
    val crunchyMenu = groupedMenu[false] ?: listOf()

    println("Soft Banked Menu")
    softBakedMenu.forEach{
        println("${it.name} - ${it.price}")
    }

    println("Crunchy cookies:")
    crunchyMenu.forEach {
        println("${it.name} - ${it.price}")
    }

    */

    /*
    val totalPrice = cookies.fold(0.0){
        total,cookie -> total +cookie.price
    }

    println("Total price: $${totalPrice}")
    */

    val alphabeticalMenu = cookies.sortedBy {
        it.name
    }

    println("Alphabetical menu:")
    alphabeticalMenu.forEach {
        println(it.name)
    }


    val alphabeticalMenuDes = cookies.sortedByDescending {
        it.name
    }

    println("\n\nAlphabetical menu (descending):")
    alphabeticalMenuDes.forEach {
        println(it.name)
    }
}