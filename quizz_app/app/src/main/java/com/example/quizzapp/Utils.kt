package com.example.quizzapp

import android.content.Context
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup

object Utils {
    fun createRaddioButtonForQuizz(id: Int, text: String, context: Context): View{
        var paddingPixel = dpToPixel(10,context)
        var radioButton: RadioButton = RadioButton(context);
        radioButton.id = id
        radioButton.text = text
        radioButton.setPadding(paddingPixel,paddingPixel,paddingPixel,paddingPixel)
        radioButton.setLayoutParams(RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT));
        return radioButton;
    }

    fun dpToPixel(paddingDp: Int, context: Context): Int{
        var density: Float = context.resources.displayMetrics.density
        var paddingPixel: Int = (paddingDp * density).toInt();
        return paddingPixel
    }
}