import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Tags (

  @SerialName("name" ) var name : String? = null

)