package get.tsu.android2022.tictactoe_minimax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import get.tsu.android2022.tictactoe_minimax.fragments.MainFragment
import get.tsu.android2022.tictactoe_minimax.fragments.PlayWithAiFragment
import get.tsu.android2022.tictactoe_minimax.fragments.PlayWithFriendFragment

class MainActivity : AppCompatActivity() {

    lateinit var mainFragment: MainFragment;
    lateinit var playWithAiFragment: PlayWithAiFragment
    lateinit var playWithFriendFragment: PlayWithFriendFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainFragment = MainFragment()
        playWithAiFragment = PlayWithAiFragment()
        playWithFriendFragment = PlayWithFriendFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,mainFragment).commit()
    }


}