package jp.shts.android.sample.koin.koinsample

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy {
        ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)
    }

    private val mainViewModelFactory = MainViewModelFactory(HelloRepositoryImpl.getInstance())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainText.text = mainViewModel.sayHello()
    }
}

class MainViewModel(private val repo: HelloRepository) : ViewModel() {
    fun sayHello() = "${repo.giveHello()} from $this"
}

class MainViewModelFactory(private val repo: HelloRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}

interface HelloRepository {
    fun giveHello(): String
}

class HelloRepositoryImpl private constructor() : HelloRepository {

    companion object {
        @Volatile
        private var INSTANCE: HelloRepositoryImpl? = null

        @Synchronized
        fun getInstance(): HelloRepositoryImpl = INSTANCE
            ?: HelloRepositoryImpl().also { INSTANCE = it }
    }

    override fun giveHello() = "Hello Koin"
}
