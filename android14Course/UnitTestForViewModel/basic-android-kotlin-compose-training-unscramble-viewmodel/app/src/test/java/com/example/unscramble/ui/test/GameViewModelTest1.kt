package com.example.unscramble.ui.test

import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.getUnscrambledWord
import com.example.unscramble.ui.GameViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertNotEquals
import org.junit.Test

class GameViewModelTest1 {
    private val viewModel = GameViewModel() //tao bien luu tru trang thai va logic theo lop

    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset()  {
        var currentGameUiState = viewModel.uiState.value
            //Lay tu chua duoc xao tron va luu tru trong correctPlayWord
        val correctPlayWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value
        assertFalse(currentGameUiState.isGuessedWordWrong)
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER,currentGameUiState.score)
    }

    companion object {
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE
    }

    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet(){
        val incorrectPlayerWord = "and"

        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()

        val currentGameUiState = viewModel.uiState.value

        assertEquals(0,currentGameUiState.score)
        assertTrue(currentGameUiState.isGuessedWordWrong)
    }

    //Kiem tra trang thai khoi tao ban dau dung nhu thiet ke
    @Test
    fun gameViewModel_Initialization_FirstWordLoaded(){
        val gameUiState = viewModel.uiState.value
        val unScrambleWord = getUnscrambledWord(gameUiState.currentScrambledWord)

        //Kiem tra da xao tron va tu kq khac nhau
        assertNotEquals(gameUiState.currentScrambledWord, unScrambleWord)

        //Assert that current word count is set to 1
        assertTrue(gameUiState.currentWordCount==1)

        //Assert that initially the score is 0
        assertTrue(gameUiState.score == 0)

        //Assert that the wrong guessed is false
        assertFalse(gameUiState.isGuessedWordWrong)

        //Assert that game is not over
        assertFalse(gameUiState.isGameOver)
    }



    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdateCorrectly(){
        var expectedScore = 0
        var currentGameUiState = viewModel.uiState.value
        var correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        //loop for times max_no_of_words
        repeat(MAX_NO_OF_WORDS){
            expectedScore += 20
            viewModel.updateUserGuess(correctPlayerWord)
            viewModel.checkUserGuess()
            currentGameUiState = viewModel.uiState.value
            correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

            //Assert that after each correct answer, score is update correctly
            assertEquals(expectedScore, currentGameUiState.score)
        }
        assertEquals(MAX_NO_OF_WORDS, currentGameUiState.currentWordCount)
        assertTrue(currentGameUiState.isGameOver)
    }

    @Test
    fun gamViewModel_WordSkipped_ScoreUnchangeAndWordCountIncreased(){
        var currentGameUiState =viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value
        val lastWordCount = currentGameUiState.currentWordCount
        viewModel.skipWord()
        currentGameUiState = viewModel.uiState.value

        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
        assertEquals(lastWordCount +1 , currentGameUiState.currentWordCount)
    }





}