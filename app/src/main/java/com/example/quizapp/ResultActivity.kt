package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val userName = intent.getStringExtra(Constants.USER_NAME);
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTION,0);
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0);

        val tvName : TextView = findViewById(R.id.tv_name);
        val tvScore: TextView = findViewById(R.id.tv_score);
        tvName.text = userName;
        tvScore.text = "Your score is $correctAnswers aout of $totalQuestions";
    }
}