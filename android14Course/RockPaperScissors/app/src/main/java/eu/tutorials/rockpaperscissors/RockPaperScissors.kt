package eu.tutorials.rockpaperscissors

fun main (){
    var computerChoice = ""
    var playerChoice =""
    //Rock: Bua
    // Paper: Bao
    // Scissors: Keo

    println ("Rock, Scissors or Paper ? Enter you choice!")
    playerChoice = readln()

    //tao mot gia tri ngau nhien
    val randomNumber = (1..3).random()


   //Cach 1: dung If else
    /*
    if (randomNumber == 1){
        computerChoice = "Rock"
    }else if (randomNumber == 2){
        computerChoice = "Paper"
    }else{
        computerChoice= "Scissors"
    }
     */

    // Cach 2 dung When, tuong tu nhu Switch Case
    when (randomNumber) {
        1 -> {
            computerChoice = "Rock"
        }
        2 -> {
            computerChoice = "Paper"
        }
        3 -> {
            computerChoice= "Scissors"
        }
    }

    println (computerChoice)

   val winner = when {
       playerChoice == computerChoice -> "Tie"
       playerChoice == "Rock" && computerChoice == "Scissors" ->  "Player"
       playerChoice == "Paper" && computerChoice == "Rock" ->  "Player"
       playerChoice == "Scissors" && computerChoice == "Paper" ->  "Player"
       else -> "Computer"
   }

    if (winner == "Tie"){
        println("It's a tie!")
    }else{
        println("$winner Won!")
    }

}