package jp.shts.android.sample.koin.koinsample

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainText.text = mainViewModel.sayGoodBye()
    }
}

class MainViewModel(private val helloRepo: HelloRepository,
                    private val goodbyeRepository: GoodbyeRepository) : ViewModel() {
    fun sayHello() = "${helloRepo.giveHello()} from $this"
    fun sayGoodBye() = "${goodbyeRepository.giveGoodbye()} from $this"
}

interface HelloRepository {
    fun giveHello(): String
}

class HelloRepositoryImpl : HelloRepository {
    override fun giveHello() = "Hello Koin"
}

interface GoodbyeRepository {
    fun giveGoodbye(): String
}

class GoodbyeRepositoryImpl: GoodbyeRepository {
    override fun giveGoodbye() = "ByeBye"
}
