import io.ktor.client.HttpClient
import io.ktor.client.request.get

class QuizzApi(private val client: HttpClient) {
    suspend fun getUserKtor(): Array<QuizApiResponse> {
        try {
            return client.get("https://quizapi.io/api/v1/questions") {
                url {
                    parameters.append("apiKey", "wNlx7cBjcy9h4NCrhCXiOBhz92Tbe2irAq6Sa59I")
                }
            }
        }
        catch (error: Error){
            println(error.message)
            return emptyArray()
        }
    }
}