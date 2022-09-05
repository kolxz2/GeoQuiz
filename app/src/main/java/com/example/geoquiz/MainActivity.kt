package com.example.geoquiz


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var cheatButton: Button
    private lateinit var questionTextView: TextView
    private var userScore:Int = 0
    // connect ViewModel with MainActivity && inicialice Provider
    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this)[QuizViewModel::class.java]
    }
    private val KEY_INDEX = "index"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // massage register
        Log.d(TAG, "Work onCreate")
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")
        // connect UI Button with Activity
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        cheatButton = findViewById(R.id.cheat_button)
        questionTextView = findViewById(R.id.question_text_view)

        updateQuestion()

        val curretIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = curretIndex

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
            quizViewModel.moveToNext()
            updateQuestion()
            trueButton.isEnabled = true
            falseButton.isEnabled = true
        }

        cheatButton.setOnClickListener {
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            // crate intent connecting with CheatActivity
           // val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            //startActivityForResult(intent, REQUEST_CODE_CHEAT)
            // move task to ActivityManager
          //  startActivity(intent)
            Toast.makeText(
                this,
                "massageResId",
                Toast.LENGTH_SHORT
            ).show()
            updateQuestion()
        }
    }

    // DRY to update questionTextView
    private fun updateQuestion(){
        // iteration by questionBlank and setting text to UI
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    // check user answer
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val massageResId:Int
        if(correctAnswer == userAnswer){
            massageResId = R.string.correct_toast
            userScore++
            Log.d(TAG, "userScore $userScore")
            Log.d(TAG, "userScore ${quizViewModel.questionBlankSize}")
        } else{
            massageResId = R.string.incorrect_toast
        }
        Toast.makeText(
            this,
            massageResId,
            Toast.LENGTH_SHORT
        ).show()
        if (quizViewModel.currentIndex == quizViewModel.questionBlankSize - 1)
            showResult()
    }
    // start toast to show result of answer
    private fun showResult(){
        val score = (userScore.toFloat() / quizViewModel.questionBlankSize.toFloat() * 100).toInt()
        Toast.makeText(
            this,
            "Correct answers $score%",
            Toast.LENGTH_LONG
        ).show()
    }



    override fun onStart() {
        Log.d(TAG, "Work onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, " onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "Work onPause")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d(TAG, "onSaveInstanceState working")
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    override fun onStop() {
        Log.d(TAG, " onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, " onDestroy")
        super.onDestroy()
    }
}