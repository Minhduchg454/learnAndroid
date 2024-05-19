package eu.tutorials.businesscard

class Song (
    var title: String,
    var artist :String,
    var yearPublished : Int,
    var  playCount : Int){
    fun isPopular():Boolean{
        return when(playCount){
            in 0..1000 -> false
            else -> true
        }
    }

    fun printDescription (){
        println("$title, performed by $artist, was released in $yearPublished.")
    }
}


open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }

    open fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}


class FoldablePhone(isScreenLightOn: Boolean=true,var isFolded :Boolean=true):
        Phone(isScreenLightOn){
            override  fun switchOn(){
                if(!isFolded){
                    isScreenLightOn=true
                    println("The phone screen's light is on.")
                }else{
                    println("Can't turn on screen because phone is folded")
                }
            }

        fun fold() {
            isFolded = true
        }

        fun unfold() {
            isFolded = false
        }

        }




fun main () {
    /*
        val morningNotification = 51
        val eveningNotification = 135

        printNotificationSummary(morningNotification)
        printNotificationSummary(eveningNotification)
         */

    /*
    //Bai 3
    val child = 5
    val adult = 28
    val senior = 87

    val isMonday = true

    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
    */

    /*
    var kelvintoCelsius: (Double) -> Double = {
        it - 273.15
    }

    var celsiustoFahrenheit: (Double) -> Double = {
        (9/5.0)*it + 32
    }

    var fahrenheittoKelvin:(Double) -> Double= {
        (5/9.0)*(it-32)+273.15
    }

    printFinalTemperature(27.0,"Celsius","Fahrenheit",celsiustoFahrenheit)
    printFinalTemperature(350.0,"Kelvin","Celsius",kelvintoCelsius)
    printFinalTemperature(10.0,"Fahrenheit","Kelvin",fahrenheittoKelvin)

    */

    /*
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)

    amanda.showProfile()
    atiqah.showProfile()
    */

    /*
    val brunoSong = Song("We Don't Talk About Bruno", "Encanto Cast", 2022, 1_000_000)
    brunoSong.printDescription()
    println(brunoSong.isPopular())
    */

    /*
    val newFoldablePhone=FoldablePhone()
    newFoldablePhone.switchOn()
    newFoldablePhone.unfold()
    newFoldablePhone.switchOn()
    */




    /*
    val winningBid = Bid(5000, "Private Collector")

    println("Item A is sold at ${auctionPrice(winningBid, 2000)}.")
    println("Item B is sold at ${auctionPrice(null, 3000)}.")
     */

}


fun printNotificationSummary(numberOfMessages: Int) {
    when(numberOfMessages){
        in 1..99 -> println("You have $numberOfMessages notifications.")
        else ->println("Your phone is blowing up! You have 99+ notifications.")
    }
}


fun ticketPrice(age: Int, isMonday: Boolean): Int {
    /*
    return when{
        age in 1..12 -> 15
        age in 13..60 && isMonday ->25
        age in 13..60 ->30
        age in 61..100 ->20
        else ->-1
    }
     */
    return when(age){
        in 1..12 -> 15
        in 13..60  ->if (isMonday) 25 else 30
        in 61..100 ->20
        else ->-1
    }

}


fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}

class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
        println("Name: $name")
        println("Age: $age")
        if(hobby!=null){
            print("Like to $hobby")
        }
        if(referrer!=null){
            println(". Has a referrer named ${referrer?.name}, who likes to play ${referrer?.hobby}.")
        }else{
            println(". Doesn't have a referrer.\n")
        }

    }
}

class Bid(val amount: Int, val bidder: String)
fun auctionPrice(bid: Bid?, minimumPrice: Int): Int {
    /*
    if(bid!=null){
        return bid.amount
    }else{
        return minimumPrice
    }
     */

    return bid?.amount?:minimumPrice
}