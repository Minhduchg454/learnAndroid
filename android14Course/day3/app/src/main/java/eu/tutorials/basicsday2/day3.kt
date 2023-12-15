package eu.tutorials.basicsday2

fun main (){
    println("Entered first number: ")
    var num1 = readln().toInt();
    println("Entered second number: ")
    var num2 =readln().toInt();
    var total = add(num1,num2)
    var divide = divide(num1,num2)

    println("Total $num1 and $num2 is:  $total")
    println("Divide $num1 and $num2 is:  $divide")
}

//Sum
fun add (num1:Int , num2:Int) :Int{
    val result = num1 +num2;
    return result
}

fun divide(num1:Int,num2:Int):Double{
    val result =num1/num2.toDouble(); // int/double -> double
    return result
}

