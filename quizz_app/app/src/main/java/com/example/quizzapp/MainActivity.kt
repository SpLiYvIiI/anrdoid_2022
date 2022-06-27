package com.example.quizzapp

import QuizApiResponse
import QuizzApi
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizzapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ktorHttpClient

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding;
    private lateinit var quizzApi: QuizzApi;
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        quizzApi = QuizzApi(ktorHttpClient)
        activityMainBinding.radioGroup1.addView(Utils.createRaddioButtonForQuizz(1,"rasdvsdvsdcxarasdvsdvsdcxarasdvsdvsdcxarasdvsdvsdcxarasdvsdvsdcxarasdvsdvsdcxa",this))
        activityMainBinding.radioGroup1.addView(Utils.createRaddioButtonForQuizz(2,"acsascascasvsdvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvasv ascxasc asc",this))
        activityMainBinding.radioGroup1.addView(Utils.createRaddioButtonForQuizz(3," ascascas cas cas cascascassdvsdvsdvcascasc asc asc ",this))
        activityMainBinding.radioGroup1.addView(Utils.createRaddioButtonForQuizz(4," aascascc  brbrrtbtrrasdvsdvsdcxarasdvsdvsdcxarasdvsdvsdcxarasdvsdvsdcxart bbbbdf ",this))
       GlobalScope.launch {
           val racxa: Array<QuizApiResponse> = quizzApi.getUserKtor()
           println("----------------------------------")
           println(racxa[0].id)
       }
    }
}