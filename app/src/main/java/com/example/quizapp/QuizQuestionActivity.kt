package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class QuizQuestionActivity : AppCompatActivity() {

    var tvQuestion: TextView? = null;
    var ivFlag: ImageView? = null;
    var progressBar: ProgressBar? = null;
    var tvRemain : TextView? = null;
    var tvOption1 : TextView? = null;
    var tvOption2 : TextView? = null;
    var tvOption3 : TextView? = null;
    var tvOption4 : TextView? = null;
    var btnSubmit : Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        tvQuestion = findViewById(R.id.tv_question);
        ivFlag = findViewById(R.id.iv_flag);
        progressBar = findViewById(R.id.progress_bar);
        tvRemain = findViewById(R.id.tv_remaining);
        tvOption1= findViewById(R.id.tv_option1);
        tvOption2 = findViewById(R.id.tv_option2);
        tvOption3 = findViewById(R.id.tv_option3);
        tvOption4 = findViewById(R.id.tv_option4);
        btnSubmit = findViewById(R.id.btn_submit);

        var questionsList = Constants.getQuestions();
        var currentQuestion = 1;
        val question: Question = questionsList[currentQuestion - 1];
        tvQuestion?.text  = question.questions;
        ivFlag?.setImageResource(question.image);
        progressBar?.progress = currentQuestion;
        tvRemain?.text = "$currentQuestion/${questionsList.size}"
        tvOption1?.text = question.questionOne
        tvOption2?.text = question.questionTwo
        tvOption3?.text = question.questionThree
        tvOption4?.text = question.questionFour





        Log.i("saad",Constants.getQuestions()[1].toString());
    }
}