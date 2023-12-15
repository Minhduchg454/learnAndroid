package eu.tutorials.day3next
fun main (){
    /*
    var daisy = Dog("Daisy", "Dwarf poodle", 1)
    println("${daisy.name} is a ${daisy.breed} and is ${daisy.age} years old ")
     */

    /*
    var myBook = BookClass();
    var customBook=BookClass("LearnCode", "Denis", 2022)
    println("My book: Tile is ${myBook.title}, author is ${myBook.author} and year published ${myBook.yearPublished}")
    println("CustomBook: Tile is ${customBook.title}, author is ${customBook.author} and year published ${customBook.yearPublished}")
     */

    var daisy = Dog("Daisy", "Dwarf poodle", 1)
    println("${daisy.name} is a ${daisy.breed} and is ${daisy.age} years old ")
    println("\nA year has passed!")
    daisy.age = 2;
    println("${daisy.name} is a ${daisy.breed} and is ${daisy.age} years old ")

}
