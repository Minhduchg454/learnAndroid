package eu.tutorials.rockpaperscissor

fun main (){
    val blueRoseVase = Vase(color = "Blue", design = "Rose")
    val redRoseVase = blueRoseVase.copy(color = "Red")
    println(blueRoseVase)
    println(redRoseVase)
}

data class Vase (val color: String, val design: String)