package com.dev.bcepedia.bceplacecom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.databinding.DataBindingUtil
import com.dev.bcepedia.bceplacecom.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

  private lateinit var activityMainBinding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

  }

}