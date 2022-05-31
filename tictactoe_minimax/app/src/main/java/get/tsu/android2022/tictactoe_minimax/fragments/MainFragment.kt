package get.tsu.android2022.tictactoe_minimax.fragments

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import get.tsu.android2022.tictactoe_minimax.R
import get.tsu.android2022.tictactoe_minimax.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {

    private lateinit var fragmentMainBinding: FragmentMainBinding;
    private lateinit var playWithFriendFragment: PlayWithFriendFragment;
    private lateinit var playWithAiFragment: PlayWithAiFragment;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return fragmentMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playWithFriendFragment = PlayWithFriendFragment()
        playWithAiFragment = PlayWithAiFragment()
        fragmentMainBinding.playWithFriend.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.fragment_container,playWithFriendFragment).commit()
        }
        fragmentMainBinding.playWithAi.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.fragment_container,playWithAiFragment).commit()
        }
    }

}