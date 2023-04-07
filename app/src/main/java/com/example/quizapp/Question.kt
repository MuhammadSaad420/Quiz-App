package com.example.quizapp

data class Question(
    val id: Int,
    val questions: String,
    val image: Int,
    val questionOne: String,
    val questionTwo: String,
    val questionThree: String,
    val questionFour: String,
    val correctAnswer: Int,
)
