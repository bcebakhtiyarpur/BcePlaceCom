package com.dev.bcepedia.bceplacecom.ui.auth

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.dev.bcepedia.bceplacecom.R
import com.dev.bcepedia.bceplacecom.databinding.ActivityLoginBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

  private lateinit var binding: ActivityLoginBinding
  private lateinit var auth: FirebaseAuth

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Initializing DataBinding & Firebase Authentication
    binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    auth = Firebase.auth

    // Buttons & Field
    // On Sign Up Click
    val intent = Intent(this, SignUpActivity::class.java)
    binding.signUpButton.setOnClickListener {
      startActivity(intent)
    }

    // Sign In Button
    binding.signInButton.setOnClickListener{
      val email: String = binding.loginEmail.text.toString()
      val pass: String = binding.loginPass.text.toString()
      signWithEmailAndPassword(email, pass)
    }

    // Github Logo on Dark & Light Theme
    @TargetApi(30)
    when(resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK){
      Configuration.UI_MODE_NIGHT_YES->{
        binding.loginUsingGithub.setImageResource(R.drawable.github_sign_in_logo_dark)
      }
      Configuration.UI_MODE_NIGHT_NO->{
        binding.loginUsingGithub.setImageResource(R.drawable.github_sign_in_logo_light)
      }
    }

    binding.root
  }
  private fun signWithEmailAndPassword(email: String, password: String){
    auth.signInWithEmailAndPassword(email, password)
      .addOnCompleteListener(this){it->
        if(it.isSuccessful){
          // TODO User sign in success
          val user = auth.currentUser
          Toast.makeText(
            baseContext, "Signed In as: ${user!!.email}", Toast.LENGTH_SHORT).show()
        }else{
          // TODO User Sign in Failed
          Toast.makeText(
            baseContext, "Signed In Failed: ${it.exception}", Toast.LENGTH_SHORT).show()
        }
      }
  }
  public override fun onStart(){
    super.onStart()
    // Checking whether user is signed in or not
    val currentUser = auth.currentUser
    if(currentUser != null){
      // TODO user already signed in
      Toast.makeText(baseContext, "Already Signed In", Toast.LENGTH_SHORT).show()
    }
  }
}