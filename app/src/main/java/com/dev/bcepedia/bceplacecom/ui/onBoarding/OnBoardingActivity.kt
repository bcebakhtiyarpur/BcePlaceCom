package com.dev.bcepedia.bceplacecom.ui.onBoarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.dev.bcepedia.bceplacecom.MainActivity
import com.dev.bcepedia.bceplacecom.R


class OnBoardingActivity : AppCompatActivity() {
  private var pagerIndicator: LinearLayout? = null
  private var dotsCount = 0
  private lateinit var dots: Array<ImageView?>
  private var onboardPager: ViewPager? = null
  private var mAdapter: OnBoardAdapter? = null
  private var btnGetStarted: Button? = null
  private var previousPos = 0
  private var prevStarted: String = "yes"
  private var onBoardItems: ArrayList<OnBoardItem> = ArrayList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_on_boarding)
    btnGetStarted = findViewById<View>(R.id.btn_get_started) as Button
    onboardPager = findViewById<View>(R.id.pager_introduction) as ViewPager
    pagerIndicator = findViewById<View>(R.id.viewPagerCountDots) as LinearLayout
    loadData()
    mAdapter = OnBoardAdapter(this, onBoardItems)
    onboardPager!!.adapter = mAdapter
    onboardPager!!.currentItem = 0
    onboardPager!!.addOnPageChangeListener(object : OnPageChangeListener {
      override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
      ) {
      }

      override fun onPageSelected(position: Int) {

        // Change the current position intimation
        for (i in 0 until dotsCount) {
          dots[i]?.setImageDrawable(
            ContextCompat.getDrawable(
              this@OnBoardingActivity,
              R.drawable.non_selected_item_dot
            )
          )
        }
        dots[position]?.setImageDrawable(
          ContextCompat.getDrawable(
            this@OnBoardingActivity,
            R.drawable.selected_item_dot
          )
        )
        val pos = position + 1
        if (pos == dotsCount && previousPos == dotsCount - 1) showAnimation() else if (pos == dotsCount - 1 && previousPos == dotsCount) hideAnimation()
        previousPos = pos
      }

      override fun onPageScrollStateChanged(state: Int) {}
    })
    btnGetStarted!!.setOnClickListener {
      val intent = Intent(this, MainActivity::class.java)
      startActivity(intent)
    }
    setUiPageViewController()
  }

  // Load data into the viewpager
  private fun loadData() {
    val header = intArrayOf(R.string.ob_header1, R.string.ob_header2, R.string.ob_header3)
    val desc = intArrayOf(R.string.ob_desc1, R.string.ob_desc2, R.string.ob_desc3)
    val imageId =
      intArrayOf(R.drawable.onboard_page1, R.drawable.onboard_page2, R.drawable.onboard_page3)
    for (i in imageId.indices) {
      val item = OnBoardItem(
        imageId[i], resources.getString(header[i]),
        resources.getString(desc[i])
      )

      onBoardItems.add(item)
    }
  }

  // Button bottomUp animation
  private fun showAnimation() {
    val show = AnimationUtils.loadAnimation(this, R.anim.slide_up)
    btnGetStarted!!.startAnimation(show)
    show.setAnimationListener(object : Animation.AnimationListener {
      override fun onAnimationStart(animation: Animation?) {
        btnGetStarted!!.visibility = View.VISIBLE
      }

      override fun onAnimationRepeat(animation: Animation?) {}
      override fun onAnimationEnd(animation: Animation?) {
        btnGetStarted!!.clearAnimation()
      }
    })
  }

  // Button Top down animation
  private fun hideAnimation() {
    val hide = AnimationUtils.loadAnimation(this, R.anim.slide_down)
    btnGetStarted!!.startAnimation(hide)
    hide.setAnimationListener(object : Animation.AnimationListener {
      override fun onAnimationStart(animation: Animation?) {}
      override fun onAnimationRepeat(animation: Animation?) {}
      override fun onAnimationEnd(animation: Animation?) {
        btnGetStarted!!.clearAnimation()
        btnGetStarted!!.visibility = View.GONE
      }
    })
  }

  // setup the Page Controller
  private fun setUiPageViewController() {
    dotsCount = mAdapter!!.count
    dots = arrayOfNulls(dotsCount)
    for (i in 0 until dotsCount) {
      dots[i] = ImageView(this)
      dots[i]?.setImageDrawable(
        ContextCompat.getDrawable(
          this@OnBoardingActivity,
          R.drawable.non_selected_item_dot
        )
      )
      val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
      )
      params.setMargins(6, 0, 6, 0)
      pagerIndicator!!.addView(dots[i], params)
    }
    dots[0]?.setImageDrawable(
      ContextCompat.getDrawable(
        this@OnBoardingActivity,
        R.drawable.selected_item_dot
      )
    )
  }

  override fun onResume() {
    super.onResume()
    val sharedPreferences = getSharedPreferences(
      resources.getString(R.string.app_name), MODE_PRIVATE)
    if (!sharedPreferences.getBoolean(prevStarted, false)) {
      val editor = sharedPreferences.edit()
      editor.putBoolean(prevStarted, true)
      editor.apply()
    } else {
      val intent = Intent(this, MainActivity::class.java)
      startActivity(intent)
    }
  }

  override fun onBackPressed() {
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_HOME)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
    finish()

  }

}
