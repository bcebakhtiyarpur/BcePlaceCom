package com.dev.bcepedia.bceplacecom.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dev.bcepedia.bceplacecom.R
import com.dev.bcepedia.bceplacecom.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

  private lateinit var binding: ActivitySignUpBinding
  private lateinit var auth: FirebaseAuth

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
    auth = Firebase.auth

    if(auth.currentUser != null){
      profileActivity()
    }

    // Sign In Button
    binding.signInButton.setOnClickListener {
      val intent = Intent(this, LoginActivity::class.java)
      if(shouldUpRecreateTask(intent)){
        startActivity(intent)
      }
      navigateUpTo(intent)
    }

    // Sign Up Button
    binding.signUpButton.setOnClickListener{
      val email: String = binding.signUpEmail.text.toString()
      val pass: String = binding.signUpPass.text.toString()
      createUserWithEmailAndPassword(email, pass)
    }
  }

  private fun createUserWithEmailAndPassword(email: String, password: String){
    auth.createUserWithEmailAndPassword(email, password)
      .addOnCompleteListener(this){
        if (it.isSuccessful){
          Toast.makeText(baseContext, "Account Created", Toast.LENGTH_SHORT).show()
          profileActivity()
        }else{
          // TODO User Sign Up Failed
          Toast.makeText(
            baseContext, "Failed to created Account: ${it.exception}", Toast.LENGTH_SHORT)
            .show()
        }
      }
  }

  private fun profileActivity() {
    val intent = Intent(this, ProfileActivity::class.java)
    if(shouldUpRecreateTask(intent)){
      startActivity(intent)
    }
    navigateUpTo(intent)
  }

}