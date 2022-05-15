package get.tsu.android2022.shualeduri.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import get.tsu.android2022.shualeduri.R

class ProfileFragment: Fragment(R.layout.fragment_profile) {
    lateinit var sp: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fm: FragmentManager = fragmentManager!!
        val loginFragment: LoginFragment = LoginFragment()

        val helloText: TextView = view.findViewById<TextView>(R.id.hello_text)
        helloText.text = "Hello, " + this.sp.getString("USER_NAME","")

        view.findViewById<ImageButton>(R.id.log_out).setOnClickListener{
            fm.beginTransaction().replace(R.id.fragment_container,loginFragment).commit()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sp = context.getSharedPreferences("login",Context.MODE_PRIVATE)
    }
}