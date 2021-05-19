package com.dev.bcepedia.bceplacecom.ui.auth

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.dev.bcepedia.bceplacecom.R
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

  private lateinit var auth: FirebaseAuth

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sign_up)

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