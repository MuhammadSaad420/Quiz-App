package com.example.quizapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.lang.reflect.Type

class QuizQuestionActivity : AppCompatActivity() , View.OnClickListener{
    private var mQuestionList: ArrayList<Question>? = null;
    private var mCurrentQuestion: Int = 1;
    private  var mSelectedOptionsPosition: Int = 0;
    private var mUserName: String? = null;
    private var mCorrectAnswers: Int = 0;


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
        mUserName = intent.getStringExtra(Constants.USER_NAME);
        tvQuestion = findViewById(R.id.tv_question);
        ivFlag = findViewById(R.id.iv_flag);
        progressBar = findViewById(R.id.progress_bar);
        tvRemain = findViewById(R.id.tv_remaining);
        tvOption1= findViewById(R.id.tv_option1);
        tvOption2 = findViewById(R.id.tv_option2);
        tvOption3 = findViewById(R.id.tv_option3);
        tvOption4 = findViewById(R.id.tv_option4);
        btnSubmit = findViewById(R.id.btn_submit);
        tvOption1?.setOnClickListener(this)
        tvOption2?.setOnClickListener(this)
        tvOption3?.setOnClickListener(this)
        tvOption4?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        setQuestion()
        setDefaultOption()
        Log.i("saad",Constants.getQuestions()[1].toString());
    }

    private fun setDefaultOption() {

        var options = ArrayList<TextView>()
        tvOption1?.let {
            options.add(it);
        }
        tvOption2?.let {
            options.add(it);
        }
        tvOption3?.let {
            options.add(it)
        }
        tvOption4?.let {
            options.add(it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_bg)
        }
    }

    private fun setQuestion() {
        setDefaultOption()
        mQuestionList = Constants.getQuestions();
        val question: Question = mQuestionList!![mCurrentQuestion - 1];
        tvQuestion?.text = question.questions;
        ivFlag?.setImageResource(question.image);
        progressBar?.progress = mCurrentQuestion;
        tvRemain?.text = "$mCurrentQuestion/${mQuestionList!!.size}"
        tvOption1?.text = question.questionOne
        tvOption2?.text = question.questionTwo
        tvOption3?.text = question.questionThree
        tvOption4?.text = question.questionFour
    }

    override fun onClick(v: View?) {
        when(v?.id) {
           R.id.tv_option1 -> {
               tvOption1?.let {
                   setSelectedOption(it, 1)
               }
           }
            R.id.tv_option2 -> {
                tvOption2?.let {
                    setSelectedOption(it, 2)
                }
            }
            R.id.tv_option3 -> {
                tvOption3?.let {
                    setSelectedOption(it, 3)
                }
            }
            R.id.tv_option4 -> {
                tvOption4?.let {
                    setSelectedOption(it, 4)
                }

            }
            R.id.btn_submit -> {
                if(mSelectedOptionsPosition == 0) {
                    mCurrentQuestion++
                    when{
                        mCurrentQuestion <= mQuestionList!!.size -> {
                            setQuestion();
                        } else -> {
                            val intent = Intent(this, ResultActivity::class.java);
                            intent.putExtra(Constants.USER_NAME, mUserName);
                        intent.putExtra(Constants.TOTAL_QUESTION, mQuestionList?.size)
                        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers);
                        startActivity(intent);
                        }
                    }
                } else {
                    val question = mQuestionList?.get(mCurrentQuestion-1);
                    if(question!!.correctAnswer != mSelectedOptionsPosition) {
                        answerView(mSelectedOptionsPosition, R.drawable.wrong_answer_bg)
                    } else {
                        mCorrectAnswers++;
                    }
                    answerView(question.correctAnswer, R.drawable.correct_answer_bg)

                    if(mCurrentQuestion == mQuestionList!!.size) {
                        btnSubmit?.text = "FINISH";
                    }
                    else {
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionsPosition = 0;
                }
            }
        }
    }

    private fun setSelectedOption(view: TextView, optionIndex: Int) {
        setDefaultOption();
        mSelectedOptionsPosition = optionIndex;
        view.setTypeface(view.typeface, Typeface.BOLD);
        view.background = ContextCompat.getDrawable(this,R.drawable.selected_option_bg)
    }
    private fun answerView(answer: Int, drawableView: Int) {
        when(answer) {
            1 -> {
                tvOption1?.background = ContextCompat.getDrawable(this,drawableView);
            }
            2 -> {
                tvOption2?.background = ContextCompat.getDrawable(this,drawableView)
            }
            3 -> {
                tvOption3?.background = ContextCompat.getDrawable(this,drawableView)
            }
            4 -> {
                tvOption4?.background = ContextCompat.getDrawable(this,drawableView)
            }

        }
    }
}