import androidx.lifecycle.ViewModel
import dependencies.MyRepository

class MyViewModel(
    private val repository: MyRepository
) : ViewModel() {

    fun getHelloWorldString(): String {
        return repository.helloWorld()
    }
}
