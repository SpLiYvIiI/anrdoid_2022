package get.tsu.android2022.shualeduri.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import get.tsu.android2022.shualeduri.R


class LoginFragment: Fragment(R.layout.fragment_login) {
    lateinit var sp: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fm: FragmentManager = fragmentManager!!
        val input: EditText = view.findViewById<EditText>(R.id.profile_name)
        val profileFragment: ProfileFragment = ProfileFragment()

        view.findViewById<ImageButton>(R.id.loginButtonImage).setOnClickListener {
            this.sp.edit().apply{
                remove("USER_NAME")
            }.commit()
            if(input.text.toString() != ""){
                this.sp.edit().apply{
                    putString("USER_NAME",input.text.toString())
                }.commit()
            }
            input.text.clear()
            fm.beginTransaction().replace(R.id.fragment_container,profileFragment).commit()
        }

        view.findViewById<ImageView>(R.id.loginImage).setOnClickListener{
            input.text.clear()
            fm.beginTransaction().replace(R.id.fragment_container,profileFragment).commit()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sp = context.getSharedPreferences("login",Context.MODE_PRIVATE)
    }
}