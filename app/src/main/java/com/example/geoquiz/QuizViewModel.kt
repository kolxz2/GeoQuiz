package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel
import org.w3c.dom.Text

private const val TAG = "QuizViewModel"

class QuizViewModel: ViewModel() {

    var currentIndex = 0
    private val questionBlank = listOf<Question>(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    val currentQuestionAnswer: Boolean
        get() = questionBlank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBlank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBlank.size
    }

    init {
        Log.d(TAG, "ViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }
}