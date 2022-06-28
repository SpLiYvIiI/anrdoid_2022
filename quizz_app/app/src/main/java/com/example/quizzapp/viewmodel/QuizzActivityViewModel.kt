import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzapp.Utils
import kotlinx.coroutines.launch

class QuizzActivityViewModel: ViewModel() {
    fun getQuizz(category: String, difficulty: String): LiveData<Array<QuizzResponse>> {
            val result = MutableLiveData<Array<QuizzResponse>>()
            viewModelScope.launch {
                val resp: Array<QuizzResponse> = Utils.fetchQuestion(category,difficulty)
                result.postValue(resp)
            }
            return result
    }
}