import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class QuizzResponse (

  @SerialName("id"                       ) var id                     : Int?            = null,
  @SerialName("question"                 ) var question               : String?         = null,
  @SerialName("description"              ) var description            : String?         = null,
  @SerialName("answers"                  ) var answers                : Answers        = Answers(),
  @SerialName("multiple_correct_answers" ) var multipleCorrectAnswers : String?         = null,
  @SerialName("correct_answers"          ) var correctAnswers         : CorrectAnswers = CorrectAnswers(),
  @SerialName("correct_answer"           ) var correctAnswer          : String?         = null,
  @SerialName("explanation"              ) var explanation            : String?         = null,
  @SerialName("tip"                      ) var tip                    : String?         = null,
  @SerialName("tags"                     ) var tags                   : ArrayList<Tags> = arrayListOf(),
  @SerialName("category"                 ) var category               : String?         = null,
  @SerialName("difficulty"               ) var difficulty             : String?         = null

)
