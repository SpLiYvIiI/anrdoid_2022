package com.example.quizzapp.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import com.example.quizzapp.databinding.ActivityMainBinding
import android.content.Intent





class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding;
    private lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        sp = this.getSharedPreferences("quizz_options", Context.MODE_PRIVATE)
        activityMainBinding.takeQuizz.setOnClickListener{
            var category: String = activityMainBinding.categorySpinner.selectedItem.toString()
            var difficulty: String = activityMainBinding.difficultySpinner.selectedItem.toString()
            var checkedRadioButtonId: Int = activityMainBinding.numberOfQuestionsGroup.checkedRadioButtonId
            var checkedRadioButton: RadioButton = activityMainBinding.root.findViewById(checkedRadioButtonId)
            var numberOfQuestions: String = checkedRadioButton.text.toString()
            sp.edit().apply{
                putString("quizz_category",category)
                putString("quizz_difficulty",difficulty)
                putString("quizz_numberOfQuestions",numberOfQuestions)
            }.commit()
            var intent: Intent = Intent(this, QuizzActivity::class.java)
            this.startActivity(intent)
        }

    }

}