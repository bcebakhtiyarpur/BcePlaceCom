package com.dev.bcepedia.bceplacecom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.dev.bcepedia.bceplacecom.databinding.ActivityMainBinding
import com.dev.bcepedia.bceplacecom.ui.auth.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    val intent = Intent(this, LoginActivity::class.java)

    binding.toLogin.setOnClickListener {
      startActivity(intent)
    }

    binding.root
  }

}