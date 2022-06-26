package com.example.quizzapp

import android.content.Context
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup

object Utils {
    fun createRaddioButtonForQuizz(text: String, context: Context): View{
        var radioButton: RadioButton = RadioButton(context);
        radioButton.setText(text)
        radioButton.layoutParams.width = 20
        radioButton.layoutParams.height = 20
        return radioButton;
    }
}