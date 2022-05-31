package get.tsu.android2022.tictactoe_minimax.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import get.tsu.android2022.tictactoe_minimax.GameActivity
import get.tsu.android2022.tictactoe_minimax.R
import get.tsu.android2022.tictactoe_minimax.databinding.FragmentPlayWithFriendBinding

class PlayWithFriendFragment: Fragment(R.layout.fragment_play_with_friend) {
    private lateinit var sp: SharedPreferences
    private lateinit var fragmentPlayWithFriendBinding: FragmentPlayWithFriendBinding
    private lateinit var mainFragment: MainFragment;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentPlayWithFriendBinding = FragmentPlayWithFriendBinding.inflate(inflater, container, false)
        return fragmentPlayWithFriendBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainFragment = MainFragment()
        fragmentPlayWithFriendBinding.goBack.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.fragment_container,mainFragment).commit()
        }
        fragmentPlayWithFriendBinding.startGame.setOnClickListener{
            this.sp.edit().apply{
                putString("YOUR_NAME",fragmentPlayWithFriendBinding.yourName1.text.toString())
                putString("FRIENDS_NAME",fragmentPlayWithFriendBinding.friendsName.text.toString())
            }.commit()
            var intent = Intent(this.activity, GameActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sp = context.getSharedPreferences("PLAYERS_INFO", Context.MODE_PRIVATE)
    }
}