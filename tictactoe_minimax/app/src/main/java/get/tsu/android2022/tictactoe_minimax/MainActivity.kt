package get.tsu.android2022.tictactoe_minimax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import get.tsu.android2022.tictactoe_minimax.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainFragment: MainFragment;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainFragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,mainFragment).commit()
    }


}