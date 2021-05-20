package com.dev.bcepedia.bceplacecom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dev.bcepedia.bceplacecom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var activityMainBinding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

  }
}