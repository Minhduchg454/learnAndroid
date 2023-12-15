package eu.tutorials.basicsday2

fun main (){
    println("Entered first number: ")
    var num1 = readln().toInt();
    println("Entered second number: ")
    var num2 =readln().toInt();
    var result = add(num1,num2)

    println("Total $num1 and $num2 is:  $result")

}

fun add (num1:Int , num2:Int) :Int{
    val result = num1 +num2;
    return result
}