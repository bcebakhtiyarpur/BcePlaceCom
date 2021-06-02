package com.dev.bcepedia.bceplacecom.ui.auth

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dev.bcepedia.bceplacecom.R
import com.dev.bcepedia.bceplacecom.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.OAuthProvider
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
    binding.signInButton.setOnClickListener {

      if (auth.currentUser != null){
        profileActivity()
      }

      val email: String = binding.loginEmail.text.toString()
      val pass: String = binding.loginPass.text.toString()

      if (email.isNotEmpty() && pass.isNotEmpty()) {
        signWithEmailAndPassword(email, pass)
      } else {
        Toast.makeText(
          baseContext, "Fill the required Fields", Toast.LENGTH_SHORT
        ).show()
      }
    }
    // Google Sign In Button
    binding.loginUsingGoogle.setOnClickListener {
      signInWithGoogle()
    }

    // Github Sign In Button
    binding.loginUsingGithub.setOnClickListener {
      signInWithGithub()
    }

    // Github Logo on Dark & Light Theme
    @RequiresApi(29)
    when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
      Configuration.UI_MODE_NIGHT_YES -> {
        binding.loginUsingGithub.setBackgroundResource(R.drawable.github_sign_in_logo_dark)
        binding.loginUsingGithub
      }
      Configuration.UI_MODE_NIGHT_NO -> {
        binding.loginUsingGithub.setBackgroundResource(R.drawable.github_sign_in_logo_light)
      }
    }

  }

  private fun signWithEmailAndPassword(email: String, password: String) {
    auth.signInWithEmailAndPassword(email, password)
      .addOnCompleteListener(this) {
        if (it.isSuccessful) {
          // User sign in success
          val user = auth.currentUser
          Toast.makeText(
            baseContext, "Signed In as: ${user!!.email}", Toast.LENGTH_SHORT
          ).show()
          profileActivity()
        } else {
          // TODO User Sign in Failed
          Toast.makeText(
            baseContext, "Signed In Failed: ${it.exception}", Toast.LENGTH_SHORT
          ).show()
        }
      }
  }

  private fun signInWithGoogle() {
    val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      .requestIdToken(R.string.default_web_client_id.toString())
      .requestEmail()
      .build()

    val googleSignInClient = GoogleSignIn.getClient(this, googleSignInOption)

    val signInIntent = googleSignInClient.signInIntent
    startActivityForResult(signInIntent, RC_SIGN_IN)

  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == RC_SIGN_IN) {
      val tsk = GoogleSignIn.getSignedInAccountFromIntent(data)
      try {
        val account = tsk.getResult(ApiException::class.java)!!
        firebaseAuthWithGoogle(account.idToken!!)
      } catch (e: ApiException) {
        Toast.makeText(baseContext, "Failed to login: $e", Toast.LENGTH_SHORT).show()
      }
    }
  }

  private fun firebaseAuthWithGoogle(token: String) {
    val credential = GoogleAuthProvider.getCredential(token, null)
    auth.signInWithCredential(credential)
      .addOnCompleteListener(this) {
        if (it.isSuccessful) {
          // Sign In Successful
          val user = auth.currentUser
          Toast.makeText(
            baseContext, "Failed to login: ${user!!.email}", Toast.LENGTH_SHORT
          ).show()
          profileActivity()
        } else {
          // TODO Failed to Sign in
          Toast.makeText(
            baseContext, "Failed to login: ${it.exception}", Toast.LENGTH_SHORT
          ).show()
        }
      }
  }

  private fun signInWithGithub() {

    val currentUser = auth.currentUser

    if (currentUser != null) {
      Toast.makeText(baseContext, "User Already Signed In", Toast.LENGTH_SHORT).show()
      profileActivity()
    }

    val provider = OAuthProvider.newBuilder("github.com")

    auth.startActivityForSignInWithProvider(this, provider.build())
    val pendingResultTask: Task<AuthResult>? = auth.pendingAuthResult
    if (pendingResultTask != null) {
      pendingResultTask
        .addOnSuccessListener {
          // User is signed in.
          // IdP data available in
          // authResult.getAdditionalUserInfo().getProfile().
          // The OAuth access token can also be retrieved:
          // authResult.getCredential().getAccessToken().
        }
        .addOnFailureListener {
          // TODO Handle failure.
        }
    } else {
      // Handle Pending
      Toast.makeText(baseContext, "Request Pending...", Toast.LENGTH_SHORT).show()
    }
  }

  public override fun onStart() {
    super.onStart()
    // Checking whether user is signed in or not
    val currentUser = auth.currentUser
    if (currentUser != null) {
      // user already signed in
      Toast.makeText(baseContext, "Already Signed In", Toast.LENGTH_SHORT).show()
      profileActivity()
    }
  }

  private fun profileActivity() {
    val intent = Intent(this, ProfileActivity::class.java)
    if(shouldUpRecreateTask(intent)){
      startActivity(intent)
    }
    navigateUpTo(intent)
  }

  companion object {
    const val RC_SIGN_IN = 9001
  }

}