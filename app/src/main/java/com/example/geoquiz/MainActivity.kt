package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private var currentIndex = 0
    private var userScore:Int = 0
    private val questionBlank = listOf<Question>(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // massage register
        Log.d(TAG, "Work onCreate")
        setContentView(R.layout.activity_main)
        // connect UI Button with Activity
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        updateQuestion()

        // set Listener
        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
            // deactivate button
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
            // deactivate button
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBlank.size
            updateQuestion()
            trueButton.isEnabled = true
            falseButton.isEnabled = true
        }
    }

    // DRY to update questionTextView
    private fun updateQuestion(){
        // iteration by questionBlank and setting text to UI
        val questionTextResId = questionBlank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    // check user answer
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = questionBlank[currentIndex].answer
        val massageResId:Int
        if(correctAnswer == userAnswer){
            massageResId = R.string.correct_toast
            userScore++
            Log.d(TAG, "userScore $userScore")
            Log.d(TAG, "userScore ${questionBlank.size}")
        } else{
            massageResId = R.string.incorrect_toast
        }
        Toast.makeText(
            this,
            massageResId,
            Toast.LENGTH_SHORT
        ).show()
        if (currentIndex == questionBlank.size - 1)
            showResult()
    }

    private fun showResult(){
        val score = (userScore.toFloat() / questionBlank.size.toFloat() * 100).toInt()
        Toast.makeText(
            this,
            "Correct answers $score%",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onPause() {
        Log.d(TAG, "Work onPause")
        super.onPause()
    }

    override fun onStart() {
        Log.d(TAG, "Work onStart")
        super.onStart()
    }

    override fun onStop() {
        Log.d(TAG, " onStop")
        super.onStop()
    }

    override fun onResume() {
        Log.d(TAG, " onResume")
        super.onResume()
    }

    override fun onDestroy() {
        Log.d(TAG, " onDestroy")
        super.onDestroy()
    }
}