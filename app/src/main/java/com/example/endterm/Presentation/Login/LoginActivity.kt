package com.example.endterm.Presentation.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.endterm.MainActivity
import com.example.endterm.R
import com.example.endterm.Utils.AppPreferences
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity:"
    private lateinit var loginViewModel: LoginViewModel
    private var userLogin : String? = null
    private var userPassword : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        buttonLogin.setOnClickListener{
            userLogin = editTextUserName.text.toString()
            userPassword = editTextPassword.text.toString()
            userLogin?.let { userLogin->
                userPassword?.let { userPassword ->
                    //Log.d("loging", "what is the hell" )
                    loginViewModel.login(
                        "o_bazarbekov", "oljas123"
                    )

                }
            }
        }
        setData()
    }

    private fun setData() {
        loginViewModel.liveData.observe(this, Observer { state ->
            when(state) {
                is LoginViewModel.State.ShowLoading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is LoginViewModel.State.HideLoading -> {
                    progressBar.visibility = View.INVISIBLE
                }
                is LoginViewModel.State.Result -> {
                    if (state.success && state.sessionId.isNotEmpty()) {
                        val intent = Intent(this, MainActivity::class.java)
                        AppPreferences.setSessionId(this, state.sessionId)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Invalid username or password credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
