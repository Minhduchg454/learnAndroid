package eu.tutorials.knowledgekotlinmore

fun main (){
    val solarSystem= mutableMapOf(
        "Mercury" to 0,
        "Venus" to 0,
        "Earth" to 1,
        "Mars" to 2,
        "Jupiter" to 79,
        "Saturn" to 82,
        "Uranus" to 27,
        "Neptune" to 14
    )
    println(solarSystem.size)

    //Add element
    solarSystem["Pluto"]=5

    println(solarSystem.size)

    println(solarSystem["Pluto"])
    println(solarSystem.get("Theia"))

    //Remove
    solarSystem.remove("Pluto")
    println(solarSystem.size)

    //Modify value
    solarSystem["Jupiter"]=78
    println(solarSystem["Jupiter"])
}
