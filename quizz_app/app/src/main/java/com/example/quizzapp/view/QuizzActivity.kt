package com.example.quizzapp.view

import QuizzActivityViewModel
import QuizzResponse
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizzapp.R
import com.example.quizzapp.Utils
import com.example.quizzapp.databinding.ActivityQuizzBinding

class QuizzActivity : AppCompatActivity() {
    private lateinit var quizzBinding: ActivityQuizzBinding
    private lateinit var sp: SharedPreferences
    private lateinit var quizzActivityViewModel: QuizzActivityViewModel
    private lateinit var category: String
    private lateinit var difficulty: String
    private lateinit var numberOfQuestions: String
    private lateinit var visited: MutableSet<Int>
    private lateinit var answerToCurrentQuestion: String
    private var numberOfQuestionsAnswered: Int = 0
    private var numberOfQuestionsAnsweredCorrectly: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quizzBinding = ActivityQuizzBinding.inflate(layoutInflater)
        setContentView(quizzBinding.root)
        quizzActivityViewModel = ViewModelProvider(this).get(QuizzActivityViewModel::class.java)
        sp = getSharedPreferences("quizz_options", Context.MODE_PRIVATE)
        category = sp.getString("quizz_category","Docker").toString()
        difficulty = sp.getString("quizz_difficulty", "Easy").toString()
        numberOfQuestions = sp.getString("quizz_numberOfQuestions","5").toString()
        visited = mutableSetOf()
        init()
    }

    fun init() {
        quizzBinding.nextQuestion.isEnabled = false
        quizzBinding.sumbitAnswer.isEnabled = true
        quizzBinding.answerType.text = ""
        loadNewQuestion()
        quizzBinding.nextQuestion.setOnClickListener{
            quizzBinding.nextQuestion.isEnabled = false
            quizzBinding.sumbitAnswer.isEnabled = true
            quizzBinding.answerType.text = ""
            quizzBinding.question.text = ""
            quizzBinding.possibleAnswers.removeAllViews()
            loadNewQuestion()
        }
        quizzBinding.sumbitAnswer.setOnClickListener{
            var correctAnswerColor: Int = ContextCompat.getColor(this, R.color.correctAnswer)
            var wrongAnswerColor: Int = ContextCompat.getColor(this, R.color.wrongAnswer)
            var checkedButtonId: Int = quizzBinding.possibleAnswers.checkedRadioButtonId
            numberOfQuestionsAnswered++
            var radioButtons: Sequence<View> = quizzBinding.possibleAnswers.children
            for (View in radioButtons) {
                View.isEnabled = false
            }
            quizzBinding.possibleAnswers.get(answerToCurrentQuestion.toInt()).setBackgroundColor(correctAnswerColor)
            if (checkedButtonId.toString() == answerToCurrentQuestion) {
                numberOfQuestionsAnsweredCorrectly++
                quizzBinding.answerType.setTextColor(correctAnswerColor)
                quizzBinding.answerType.text = "Correct answer"
            } else {
                quizzBinding.possibleAnswers.get(checkedButtonId).setBackgroundColor(wrongAnswerColor)
                quizzBinding.answerType.setTextColor(wrongAnswerColor)
                quizzBinding.answerType.text = "Wrong answer"
            }
            if(numberOfQuestionsAnswered.toString() == numberOfQuestions){
                quizzBinding.sumbitAnswer.isVisible = false
                quizzBinding.nextQuestion.isEnabled = true
                quizzBinding.nextQuestion.text = "Finish"
                quizzBinding.nextQuestion.gravity = Gravity.CENTER_HORIZONTAL
                quizzBinding.nextQuestion.setOnClickListener{
                    println(numberOfQuestionsAnsweredCorrectly.toString())
                    sp.edit().apply{
                        putString("quizz_correct_answers_count",numberOfQuestionsAnsweredCorrectly.toString())
                    }.commit()
                    var intent: Intent = Intent(this, QuizResultActivity::class.java)
                    this.startActivity(intent)
                }
            }
            else {
                quizzBinding.sumbitAnswer.isEnabled = false
                quizzBinding.nextQuestion.isEnabled = true
            }
        }
    }

    fun loadNewQuestion() {
        quizzActivityViewModel.getQuizz(category,difficulty).observe(this, Observer { resp:Array<QuizzResponse> ->
            for(quiz: QuizzResponse in resp){
                if(quiz.multipleCorrectAnswers == "true" && visited.contains(quiz.id))
                    continue
                else{
                    visited.add(quiz.id!!)
                    quizzBinding.question.text = quiz.question
                    var answersArray: Array<String> = Utils.answersToArray(quiz.answers)
                    var correctAnswersArray: Array<String> = Utils.correctAnswersToArray(quiz.correctAnswers)
                    for(i in answersArray.indices) {
                        quizzBinding.possibleAnswers.addView(Utils.createRaddioButtonForQuizz(i, answersArray[i], this))
                        if (correctAnswersArray[i] == "true")
                            answerToCurrentQuestion = i.toString()
                    }
                    break
                }
            }
        })
    }
}