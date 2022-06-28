import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Answers (

  @SerialName("answer_a" ) var answerA : String? = null,
  @SerialName("answer_b" ) var answerB : String? = null,
  @SerialName("answer_c" ) var answerC : String? = null,
  @SerialName("answer_d" ) var answerD : String? = null,
  @SerialName("answer_e" ) var answerE : String? = null,
  @SerialName("answer_f" ) var answerF : String? = null

)