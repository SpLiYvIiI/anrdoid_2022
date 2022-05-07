package get.tsu.android2022.shualeduri

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import get.tsu.android2022.shualeduri.databinding.LoginPageBinding
import get.tsu.android2022.shualeduri.databinding.ProfilePageBinding
import kotlinx.android.synthetic.main.login_page.view.*
import kotlinx.android.synthetic.main.profile_page.view.*

private lateinit var loginPageBinding: LoginPageBinding
private lateinit var profilePageBinding: ProfilePageBinding

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginPageBinding = LoginPageBinding.inflate(layoutInflater)
        profilePageBinding = ProfilePageBinding.inflate(layoutInflater)
        val loginPageRoot = loginPageBinding.root
        val profilePageRoot = profilePageBinding.root
        setContentView(loginPageRoot)

        // init/implement event listeners
        loginPageRoot.loginButtonImage.setOnClickListener{
            profilePageRoot.hello_text.text = "Hello, " + loginPageRoot.profile_name.text
            setContentView(profilePageRoot);
            loginPageRoot.profile_name.setText("enter your name")
        }

        loginPageRoot.loginImage.setOnClickListener{
            profilePageRoot.hello_text.text = "Hello, " + loginPageRoot.profile_name.text
            setContentView(profilePageRoot);
            loginPageRoot.profile_name.setText("enter your name")
        }

        profilePageRoot.log_out.setOnClickListener{
            profilePageRoot.hello_text.text = "";
            setContentView(loginPageRoot)
        }
    }
}