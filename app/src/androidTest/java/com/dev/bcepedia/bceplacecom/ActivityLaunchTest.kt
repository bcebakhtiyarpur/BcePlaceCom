package com.dev.bcepedia.bceplacecom

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.dev.bcepedia.bceplacecom.ui.auth.LoginActivity
import com.dev.bcepedia.bceplacecom.ui.auth.SignUpActivity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.system.exitProcess

@RunWith(AndroidJUnit4::class)
class ActivityLaunchTest {

  @Test
  fun useAppContext() {
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    Assert.assertEquals("com.dev.bcepedia.bceplacecom", appContext.packageName)
  }

  @RunWith(AndroidJUnit4::class)
  @LargeTest
  class MainActivityTest{
    @Test fun mainActivityLaunch(){
      val scenario = ActivityScenario.launch(MainActivity::class.java)
      Assert.assertEquals(scenario.result.resultCode, Activity.RESULT_OK)
    }
  }

  @RunWith(AndroidJUnit4::class)
  @LargeTest
  class SignUpActivityTest{
    @Test fun signUpActivityLaunch(){
      val scenario = ActivityScenario.launch(SignUpActivity::class.java)
      Assert.assertEquals(scenario.result.resultCode, Activity.RESULT_OK)
    }
  }
  @RunWith(AndroidJUnit4::class)
  @LargeTest
  class LoginActivityTest{
    @Test fun loginActivityLaunch(){
      val scenario = ActivityScenario.launch(LoginActivity::class.java)
      Assert.assertEquals(scenario.result.resultCode, Activity.RESULT_OK)
    }
  }
}