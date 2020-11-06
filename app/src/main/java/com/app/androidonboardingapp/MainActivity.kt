package com.app.androidonboardingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.app.androidonboardingapp.adapters.OnBoardingViewPagerAdapter
import com.app.androidonboardingapp.models.OnBoardingModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter
    private lateinit var onBoardingViewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onBoardingData: MutableList<OnBoardingModel> = mutableListOf()
        onBoardingData.add(OnBoardingModel("Fresh Food", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", R.drawable.oboarding1))
        onBoardingData.add(OnBoardingModel("Fast Delivery", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", R.drawable.oboarding1))
        onBoardingData.add(OnBoardingModel("Easy Payment", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", R.drawable.oboarding1))


        this.initViewPagerAdapter(onBoardingData)
    }

    private fun initViewPagerAdapter(onBoardingData: List<OnBoardingModel>) {
        this.onBoardingViewPager = findViewById<ViewPager>(R.id._screenPager)
        this.onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        this.onBoardingViewPager.adapter = this.onBoardingViewPagerAdapter

        this.tabLayout = findViewById<TabLayout>(R.id._tab_indicator)
        this.tabLayout.setupWithViewPager(onBoardingViewPager)
    }
}