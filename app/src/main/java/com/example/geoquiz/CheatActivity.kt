package com.example.geoquiz

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


private const val EXTRA_ANSWER_IS_TRUE = "CheatActivity"

class CheatActivity : AppCompatActivity() {

    private var answerIsTrue = false
    private lateinit var cheatButton: Button
    private lateinit var textAnswer : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        cheatButton = findViewById(R.id.cheat_button)
        textAnswer = findViewById(R.id.answer_text_view)
        textAnswer.text = answerIsTrue.toString()

        cheatButton.setOnClickListener {
            textAnswer.visibility = View.VISIBLE
        }
    }
    // like static method
    // used in MainActivity to sent data on CheatActivity
    companion object {
        fun newIntent(pakageContext: Context, answerIsTrue: Boolean): Intent{
            return Intent(pakageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }


}