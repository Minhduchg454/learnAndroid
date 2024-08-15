package com.example.unscramble.ui.theme

data class GameUiState (
    val currentScrambledWord : String = "",
    val currentWordCount: Int = 1,
    val isGuessedWordWrong : Boolean = false,
    val score : Int = 0,
    val isOverGame: Boolean = false
)