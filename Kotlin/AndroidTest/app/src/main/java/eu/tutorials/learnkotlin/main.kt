package eu.tutorials.learnkotlin

import java.time.MonthDay
fun print(text: String, numLoop: Int){
    for(e in 0..numLoop-1) {
        println(text)
    }
}

fun sum(numb1: Int, numb2: Int):Int{
    return numb1+numb2
}

fun subtraction(numb1: Int,numb2: Int)=numb1-numb2



fun main(args : Array<String>){
    /*
    println("Hello world")
    println(3+3)
    println("I am learning kotlin")

    val name:String = "john"
    val pi = 3.13232413413
    var birthday= 1998
    println(name)
    birthday=2000
    println(birthday)
    println(pi)

    println("\n")
    val nameAndBirhday =name + birthday
    println(nameAndBirhday)

    val x=2
    val y=1
    println(x==y)


    //Kotlin data type
    val myNum:Long =1
    val myDoubleNum=5.99
    val myFloat=5.0f
    val myFloat1: Float=1.0F
    val myLetter='D'
    val myBoolean=false
    val myText="hello"

    val myDouble=5E4
    println(myDouble)

    val numInt=10+20
    val numDouble=numInt.toDouble()
    println(numDouble)

    var sum1=2
    val sum2=sum1+250
    val sum3=sum1+sum2
    val divide =5/sum1.toFloat()
    sum1+=2
    println(sum1)


    val greeting="Hello World"
    val greeting2="Hello World"
    val greeting3="Hello"
    //println(greeting[0])
    //println(greeting[1])
    //println("The lenght of the test is: "+greeting.length)
    //println(greeting.uppercase())

    //println(greeting.compareTo(greeting3))
    println(greeting.indexOf("World"))


    val firstName = "Duc"
    val lastName= "Nguyen Huu"
    println("Full name is $lastName $firstName")
    println("Full name is "+firstName+" "+lastName)

    val x=5
    val y=6
    println(x>y)

    val time =20
    val greeting=if(time<20){
        "Good day"
    }else{
        "Good evening"
    }
    println(greeting)


    //Tuong tu nhu swtich case nhung chap nhat du kieu du lieu dau vao
    val day=4
    val dayOfWeek=when(day){
        1 -> "Sunday"
        2 -> "Monday"
        2 -> "Tuesday"
        4 -> "Wednesday"
        5 -> "Thurday"
        6 -> "Saturday"
        else ->"Invaild day"
    }
    println(dayOfWeek)


    val date="Sunday"
    val day1=when(date){
        "Sunday" -> 1
        "Monday" -> 2
        "Tuesday" ->3
        "Wednesday" ->4
        "Thurday" ->5
        "Saturday" ->6
        "Friday" ->7
        else -> 0
    }

    println(day1)



    var i=5
    while(i!=0){
        println(i)
        i--
    }



    var i=5
    do{
        println(i)
        i--
    }while(i!=0)


    var i=5
    while(i!=0){
        if(i==2){
            i--
            continue
        }
        println(i)
        i--
    }

    val cars = arrayOf("Volvo","Toyota", "Honda")
    var i=0
    while (i<cars.size){
       println(cars[i])
       i++;
    }

    if("Volvo" in cars){
        println("Yes")
    }else{
        println("No")
    }

    val cars1 = arrayOf("Volvo","Toyota", "Honda")

    for(e in cars1){
        println(e)
    }



    val numb = arrayOf(1,3,5,4,2)
    for(e in numb){
        print("$e ")
    }

    for(i in 0..numb.size-2){
        for(j in numb.size-1 downTo i+1){
            if(numb[j]<numb[j-1]){
                var temp =numb[j]
                numb[j]=numb[j-1]
                numb[j-1]=temp
            }
        }
    }

    println("\n")

    for(e in numb){
        print("$e ")
    }


    for(char in 'a'..'e'){
        print(char+" ")
    }

    println()
    for(char in 'e' downTo 'a'){
        println(char)
    }

    print("Hello world", 5)


    println("1 + 2 = ${sum(1,2)}")
    print("1 - 2 = ${subtraction(1,2)}")


    var c1 = Cars("Honda","SH150",2020)
    c1.brand="Honda"
    c1.model="SH150"
    c1.year=2020

    println("${c1.brand} ${c1.model} ${c1.year}")

    var c2=Cars()
    println("${c2.brand} ${c2.model} ${c2.year}")
    */

    val s1= Person("Nguyen Huu Duc", 26)

    var c2=Cars("Honda","SH150", 2020)
    println("${c2.getModel()}")
    c2.maxSpeed(120)


}
