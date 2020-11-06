package com.app.androidonboardingapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.app.androidonboardingapp.R
import com.app.androidonboardingapp.models.OnBoardingModel
import com.bumptech.glide.Glide

class OnBoardingViewPagerAdapter(
    private val context: Context,
    private val onBoardingList: List<OnBoardingModel> = listOf()
): PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return this.onBoardingList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    @SuppressLint("CheckResult")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.onboarding_screen_view, null)

        val title: TextView = view.findViewById<TextView>(R.id._title)
        val description: TextView = view.findViewById<TextView>(R.id._description)
        val onBoardingImage: ImageView = view.findViewById<ImageView>(R.id._onBoarding_image)

        title.text = this.onBoardingList[position].title
        description.text = this.onBoardingList[position].description
        // onBoardingImage.setImageResource(this.onBoardingList[position].image)

        Glide
            .with(context)
            .load(this.onBoardingList[position].image)
            .into(onBoardingImage)

        container.addView(view)

        return view
    }

}