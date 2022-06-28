package com.example.quizzapp

import Answers
import CorrectAnswers
import QuizzResponse
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*



// esari chemi yvelaferi dzmao repozitorica chemica gulica da sulicaa mashaa
object Utils {
    private const val TIME_OUT = 60_000
    private const val API_KEY = "wNlx7cBjcy9h4NCrhCXiOBhz92Tbe2irAq6Sa59I"
    private const val LIMIT = "20"

    private val client = HttpClient(Android) {

        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })

            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Logger Ktor =>", message)
                }

            }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

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

    fun answersToArray(answers: Answers): Array<String>{
        println("-----------------")
        println(answers)
        var array: Array<String> = emptyArray()
        if(answers.answerA != null){
            array += answers.answerA!!
        }
        if(answers.answerB != null){
            array += answers.answerB!!
        }
        if(answers.answerC != null){
            array += answers.answerC!!
        }
        if(answers.answerD != null){
            array += answers.answerD!!
        }
        if(answers.answerE != null){
            array += answers.answerE!!
        }
        if(answers.answerF != null){
            array += answers.answerF!!
        }
        println("------------------")
        println(array[0])
        return array
    }

    fun correctAnswersToArray(correctAnswers: CorrectAnswers): Array<String>{
        var array: Array<String> = emptyArray()
        array += correctAnswers.answerACorrect!!
        array += correctAnswers.answerBCorrect!!
        array += correctAnswers.answerCCorrect!!
        array += correctAnswers.answerDCorrect!!
        array += correctAnswers.answerECorrect!!
        array += correctAnswers.answerFCorrect!!

        return array
    }

    suspend fun fetchQuestion(category: String, difficulty: String): Array<QuizzResponse> {
        try {
            return client.get("https://quizapi.io/api/v1/questions") {
                url {
                    parameters.append("apiKey", API_KEY)
                    parameters.append("limit", LIMIT)
                    parameters.append("category",category)
                    parameters.append("difficulty",difficulty)
                }
            }
        }
        catch (error: Error){
            println(error.message)
            return emptyArray()
        }
    }
}