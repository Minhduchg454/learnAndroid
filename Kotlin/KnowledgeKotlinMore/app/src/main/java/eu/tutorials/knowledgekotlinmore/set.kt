package eu.tutorials.knowledgekotlinmore

fun main (){
    val solarSystem = mutableSetOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    println(solarSystem.size)

    solarSystem.add("Pluto")
    println(solarSystem.size)

    println(solarSystem.contains("Pluto"))


    solarSystem.remove("Pluto")
    println(solarSystem.size)
    println(solarSystem.contains("Pluto"))




}