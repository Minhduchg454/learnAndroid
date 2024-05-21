package eu.tutorials.knowledgekotlinmore

fun main (){
   val rockPlants = arrayOf<String>("Mercury", "Venus", "Earth", "Mars")
    val gasPlanets = arrayOf("Jupiter", "Saturn", "Uranus", "Neptune")

    val solarSystem=rockPlants+gasPlanets

    /*
    println(solarSystem[0])
    println(solarSystem[1])
    println(solarSystem[2])
    println(solarSystem[3])
    println(solarSystem[4])
    println(solarSystem[5])
    println(solarSystem[6])
    println(solarSystem[7])
    */
    solarSystem[3]="Little Earth"
    println(solarSystem[3])
    //solarSystem[8] = "Pluto"

    val newSolarSystem = arrayOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto")
    println(newSolarSystem[8])
}