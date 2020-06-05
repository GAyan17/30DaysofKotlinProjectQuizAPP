package com.example.quizapp.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates

class GameViewModel : ViewModel() {

    data class Question(
        val question: String,
        val answer: Boolean
    )

    //Event which triggers the end of the game
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    //question
    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
    get() = _question

    //score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    //questions list with ans
    private var questionList: MutableList<Question>
    private var numberOfQuestion: Int = 5
    private var currentQuestionNumber: Int = 0;

    init {
        Log.i("GameViewModel", "GameViewModel Created")
        questionList = mutableListOf(
            Question("Cyclones spin in a clockwise direction in the southern hemisphere", true),
            Question("Goldfish only have a memory of three seconds", false),
            Question("The capital of Libya is Benghazi", false),
            Question("Brazil is the only country in the Americas to have the official language of Portuguese", true),
            Question("The Channel Tunnel is the longest rail tunnel in the world", false),
            Question("Darth Vader famously says the line “Luke, I am your father” in The Empire Strikes Back", false),
            Question("Olivia Newton-John represented the UK in the Eurovision Song Contest in 1974, the year ABBA won with “Waterloo”", true),
            Question("Stephen Hawking declined a knighthood from the Queen", true),
            Question("The highest mountain in England is Ben Nevis", false),
            Question("Nicolas Cage and Michael Jackson both married the same woman", true),
            Question("Japan and Russia did not sign a peace treaty after World War Two so are technically still at war", true),
            Question("The mathematical name for the shape of a Pringle is hyperbolic paraboloid", true),
            Question("Charlie Chaplin came first in a Charlie Chaplin look-alike contest", false),
            Question("Michael Keaton’s real name is Michael Douglas", true),
            Question("Napoleon was of below-average height", false),
            Question("Donald Duck’s middle name is Fauntelroy", true),
            Question("The Statue of Liberty was a gift from France", true),
            Question("According to Scottish law, it is illegal to be drunk in charge of a cow", true),
            Question("The Great Wall of China is visible from space", false),
            Question("The first tea bags were made of silk", true),
            Question("Meghan Markle’s first name is Rachel", true),
            Question("Warsaw is the capital of Bulgaria", false),
            Question("A metre is further than a yard", true),
            Question("A woman has walked on the moon", false),
            Question("Flying in an aeroplane is statistically safer than driving in a car", true),
            Question("John Challis plays Boycie in Only Fools and Horses", true),
            Question("Valletta is the capital of Cyprus", false),
            Question("The currency of France is the Franc", false),
            Question("Ben Nevis is the tallest mountain in the UK", true),
            Question("Radiohead wrote the song Love is All Around", false)
        )
        questionList.shuffle()
        _question.value = questionList.removeAt(0)
        _score.value = 0
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel Destroyed")
    }

    private fun nextQuestion() {
        if (questionList.isNotEmpty() && currentQuestionNumber < numberOfQuestion) {
            questionList.shuffle()
            _question.value = questionList.removeAt(0)
        } else {
            onGameFinish()
        }
    }

    fun onTrueButtonClick() {
        if (_question.value?.answer!!) {
            _score.value = (score.value)?.plus(1)
        }
        currentQuestionNumber++
        nextQuestion()
    }

    fun onFalseButtonClick() {
        if (!_question.value?.answer!!) {
            _score.value = (score.value)?.plus(1)
        }
        currentQuestionNumber++
        nextQuestion()
    }

    //Method when the game is completed event
    private fun onGameFinish() {
        _eventGameFinish.value = true
    }

    //Method for the completed game event
    fun onGameFinishCompleted() {
        _eventGameFinish.value = false
    }
}