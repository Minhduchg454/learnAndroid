package eu.tutorials.rockpaperscissor


fun main (){
    println("Rock, Paper or Scissors ? Enter you choice!!!")
    val playerChoice = readln()
    var computerChoice = ""
    val randomNumber = (1..3).random();

    when (randomNumber){
        1 -> {computerChoice = "Rock"}
        2 -> {computerChoice = "Paper"}
        3 -> {computerChoice = "Scissor"}
    }
    println(computerChoice)

    var winner = when {
        computerChoice == playerChoice -> "Tie"
        computerChoice == "Rock" && playerChoice == "Scissor" -> "Computer"
        computerChoice == "Paper" && playerChoice == "Rock" -> "computer"
        computerChoice == "Scissor" && playerChoice == "Paper" -> "computer"
        else -> "Player"
    }

    if (winner == "Tie"){
        println("It's a tie")
    }else{
        println("$winner won")
    }
}