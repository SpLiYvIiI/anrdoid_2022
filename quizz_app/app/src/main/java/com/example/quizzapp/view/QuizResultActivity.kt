package com.example.quizzapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizzapp.databinding.ActivityQuizResultBinding
import com.example.quizzapp.databinding.ActivityQuizzBinding

class QuizResultActivity : AppCompatActivity() {
    private lateinit var activityQuizResultBinding: ActivityQuizResultBinding
    private lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityQuizResultBinding = ActivityQuizResultBinding.inflate(layoutInflater)
        setContentView(activityQuizResultBinding.root)
        sp = getSharedPreferences("quizz_options", Context.MODE_PRIVATE)
        var correctAnswersCount: String = sp.getString("quizz_correct_answers_count","0").toString()
        var numberOfQuestions = sp.getString("quizz_numberOfQuestions","5").toString()
        activityQuizResultBinding.quizResult.text = "You scored " + correctAnswersCount + " out of " + numberOfQuestions
        activityQuizResultBinding.retakeQuiz.setOnClickListener{
            sp.edit().clear().commit()
            var intent: Intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
    }
}