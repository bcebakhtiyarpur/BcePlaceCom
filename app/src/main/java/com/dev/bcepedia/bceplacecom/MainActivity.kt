package com.dev.bcepedia.bceplacecom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dev.bcepedia.bceplacecom.databinding.ActivityMainBinding
import com.dev.bcepedia.bceplacecom.ui.onBoarding.OnBoardingActivity


class MainActivity : AppCompatActivity() {

  private lateinit var activityMainBinding: ActivityMainBinding


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

  }

  override fun onBackPressed() {
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_HOME)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
    finish()
  }

}