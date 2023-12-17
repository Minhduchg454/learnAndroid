package eu.tutorials.rockpaperscissors

fun main (){
    println("Rock, Paper or Scissor ? Enter you choice!!!")
    var playerChoice = readln().lowercase()
    while (playerChoice != "rock" && playerChoice != "paper" && playerChoice != "scissor"){
        println("Rock, Paper or Scissor ? Enter you choice!!!")
        playerChoice = readln().lowercase()
    }

    var computerChoice = ""
    val randomNumber = (1..3).random();

    when (randomNumber){
        1 -> {computerChoice = "Rock"}
        2 -> {computerChoice = "Paper"}
        3 -> {computerChoice = "Scissor"}
    }
    println(computerChoice)
    computerChoice = computerChoice.lowercase()

    var winner = when {
        computerChoice == playerChoice -> "Tie"
        computerChoice == "rock" && playerChoice == "scissor" -> "Computer"
        computerChoice == "paper" && playerChoice == "rock" -> "Computer"
        computerChoice == "scissor" && playerChoice == "paper" -> "Computer"
        else -> "Player"
    }

    if (winner == "Tie"){
        println("It's a tie")
    }else{
        println("$winner won")
    }
}