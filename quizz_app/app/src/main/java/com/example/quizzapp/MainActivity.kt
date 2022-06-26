package com.example.quizzapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizzapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.radioGroup1.addView(Utils.createRaddioButtonForQuizz("racxa",this))
        activityMainBinding.radioGroup1.addView(Utils.createRaddioButtonForQuizz("acsascascasvasv ascxasc asc",this))
        activityMainBinding.radioGroup1.addView(Utils.createRaddioButtonForQuizz(" ascascas cas cas cascascascascasc asc asc ",this))
        activityMainBinding.radioGroup1.addView(Utils.createRaddioButtonForQuizz(" aascascc  brbrrtbtrrt bbbbdf ",this))
    }
}