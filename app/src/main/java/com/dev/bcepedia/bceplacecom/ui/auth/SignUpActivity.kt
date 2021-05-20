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

    // Sign In Button
    binding.signInButton.setOnClickListener {
      val intent: Intent = Intent(this, LoginActivity::class.java)
      startActivity(intent)
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
      .addOnCompleteListener(this){it->
        if (it.isSuccessful){
          // TODO Sign Up success
          Toast.makeText(baseContext, "Account Created", Toast.LENGTH_SHORT).show()
        }else{
          // TODO User Sign Up Failed
          Toast.makeText(
            baseContext, "Failed to created Account: ${it.exception}", Toast.LENGTH_SHORT)
            .show()
        }
      }
  }

}