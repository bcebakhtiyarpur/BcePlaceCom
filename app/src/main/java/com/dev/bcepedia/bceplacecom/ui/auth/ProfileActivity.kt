package com.dev.bcepedia.bceplacecom.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dev.bcepedia.bceplacecom.R
import com.dev.bcepedia.bceplacecom.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

  private lateinit var profileBinding: ActivityProfileBinding
  private lateinit var auth: FirebaseAuth

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    profileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
    auth = Firebase.auth

    if(auth.currentUser == null){
      loginActivity()
    }

    profileBinding.profileEmail.text = auth.currentUser!!.email.toString()

    // Sign out
    profileBinding.signOutButton.setOnClickListener{
      fireBaseLogOut()
    }
  }
  fun fireBaseLogOut(){
    Firebase.auth.signOut()
    loginActivity()
  }

  fun loginActivity(){
    val intent = Intent(this, LoginActivity::class.java)
    startActivity(intent)
  }

}