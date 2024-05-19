package eu.tutorials.learnkotlin

open class parent(){
    val x=5
}

class children:parent(){
    fun print()= println(x)
}

open class Animal(val Animalname:String){
    fun eat(){
        println ("${Animalname}name is eat")
    }
}

class Dog(val name : String, val color: String):Animal(name){
    fun bark(){
        println("$name is barking")
    }
}




fun main(){
    var myObj=children()
    myObj.print()

    var tom=Dog("Tom", "yello")
    tom.bark()
    tom.eat()
}