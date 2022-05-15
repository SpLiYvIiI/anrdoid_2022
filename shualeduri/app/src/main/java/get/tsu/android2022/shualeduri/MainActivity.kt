package get.tsu.android2022.shualeduri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import get.tsu.android2022.shualeduri.fragments.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginFragment = LoginFragment()

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,loginFragment).commit()
    }
}