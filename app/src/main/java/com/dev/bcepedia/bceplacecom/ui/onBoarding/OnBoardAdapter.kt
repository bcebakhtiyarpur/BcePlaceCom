package com.dev.bcepedia.bceplacecom.ui.onBoarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.dev.bcepedia.bceplacecom.R


class OnBoardAdapter(private val mContext: Context,
                     private val onBoardItems: ArrayList<OnBoardItem>): PagerAdapter() {

  override fun getCount(): Int {
    return onBoardItems.size
  }

  override fun isViewFromObject(view: View, `object`: Any): Boolean {
    return view == `object`
  }

  override fun instantiateItem(container: ViewGroup, position: Int): Any {

    val itemView = LayoutInflater.from(mContext).inflate(
      R.layout.onboard_item, container, false
    )

    val item: OnBoardItem = onBoardItems[position]

    itemView.findViewById<ImageView>(R.id.iv_onboard).setImageResource(item.getImageId())
    itemView.findViewById<TextView>(R.id.tv_header).text = item.getTitle()
    itemView.findViewById<TextView>(R.id.tv_desc).text = item.getDescription()
    container.addView(itemView)

    return itemView
  }

  override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

    container.removeView(container)

  }

}