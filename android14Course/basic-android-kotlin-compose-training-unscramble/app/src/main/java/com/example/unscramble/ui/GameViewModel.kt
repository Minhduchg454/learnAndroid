package com.example.unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.allWords
import com.example.unscramble.ui.theme.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.update

// ViewModel giup luu tru trang thai thanh phan giao dien (tu xao tron, so tu, diem)
    // Du lieu ton tai ngay ca khi co thay doi ve cau hinh nhu : xoay man hinh, hoat dong bi huy
class GameViewModel:ViewModel() {

        //Luu tru va quan ly trang thai cua giao dien nguoi dung trong ung dung
        //Giup quan sat va tu dong cap nhat giao dien nguoi dung khi trang thai thay doi
    private val _uiState = MutableStateFlow(GameUiState())

        //Muc dinh de cac thanh phan khac trong ung dung truy cap duoc vao trang thai uiState
        //Nhung khong cho phep thay doi trang thai cua uiState va chi cho phep doc du lieu
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

            //Tu du doan nguoi dung nhap
        var userGuess by mutableStateOf("")
            private set


        private lateinit var currentWord: String

            // Luu tru cac tu da duoc su dung va khong trung lap
            // Tap hop nay co the thay doi trong suot qua trinh su dung
            // = mutalbeSetOf() khoi tao danh sach rong
        private var usedWords : MutableSet<String> = mutableSetOf()


        private fun pickRandomWordAndShuffle(): String {
            // Continue picking up a new random word until you get one that hasn't been used before
            currentWord = allWords.random()
            if (usedWords.contains(currentWord)) {
                return pickRandomWordAndShuffle()
            } else {
                usedWords.add(currentWord)
                return shuffleCurrentWord(currentWord)
            }
        }

        private fun shuffleCurrentWord(word: String): String {
                //Chuyen string thanh mang cac ky tu
            val tempWord = word.toCharArray()
                // Scramble the word, xao tron mang cac ky tu
            tempWord.shuffle()
            while (String(tempWord).equals(word)) {
                tempWord.shuffle()
            }
            return String(tempWord)
        }

        fun updateUserGuess (guessedWord: String){
            userGuess = guessedWord
        }

        fun resetGame () {
            usedWords.clear()
            _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
        }

        init {
            resetGame()
        }

        private fun updateGameState (updatedScore: Int){
            if (usedWords.size == MAX_NO_OF_WORDS){
                _uiState.update { currentState ->
                    currentState.copy(
                        isGuessedWordWrong = false,
                        score = updatedScore,
                        isOverGame = true
                    )
                }
            }else{
                _uiState.update { currentState ->
                    currentState.copy(
                        isGuessedWordWrong = false,
                        currentScrambledWord = pickRandomWordAndShuffle(),
                        score = updatedScore,
                        currentWordCount = currentState.currentWordCount.inc() // tang len mot don vi moi lan goi
                    )
                }
            }
        }

        fun skipWord(){
            updateGameState(_uiState.value.score)
            updateUserGuess("")
        }

        fun checkUserGuess (){
            if (userGuess.equals(currentWord, ignoreCase = true)){
                val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
                updateGameState(updatedScore)
            }else{
                _uiState.update { currentState ->
                    currentState.copy( isGuessedWordWrong = true) }
            }
            updateUserGuess("")
        }

}