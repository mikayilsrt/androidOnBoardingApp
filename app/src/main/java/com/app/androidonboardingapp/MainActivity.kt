package com.app.androidonboardingapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.app.androidonboardingapp.adapters.OnBoardingViewPagerAdapter
import com.app.androidonboardingapp.models.OnBoardingModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter
    private lateinit var onBoardingViewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var nextAction: TextView
    private lateinit var sharePreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (this.restoreSharePreferences()) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        val onBoardingData: MutableList<OnBoardingModel> = mutableListOf()
        onBoardingData.add(OnBoardingModel("Fresh Food", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", R.drawable.oboarding1))
        onBoardingData.add(OnBoardingModel("Fast Delivery", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", R.drawable.oboarding1))
        onBoardingData.add(OnBoardingModel("Easy Payment", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", R.drawable.oboarding1))

        this.initViewPagerAdapter(onBoardingData)

        var positionIndex = this.onBoardingViewPager.currentItem
        this.nextAction = findViewById(R.id._nextAction)
        this.nextAction.setOnClickListener {
            if (positionIndex < onBoardingData.size) {
                positionIndex++
                this.onBoardingViewPager.currentItem = positionIndex
            }
            if (positionIndex == onBoardingData.size) {
                this.saveSharePreferences()

                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

        this.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

            override fun onTabSelected(tab: TabLayout.Tab?) {
                positionIndex = tab!!.position
                if (positionIndex == onBoardingData.size - 1) {
                    nextAction.text = "Get Started"
                } else {
                    nextAction.text = "Next"
                }
            }
        })
    }

    private fun initViewPagerAdapter(onBoardingData: List<OnBoardingModel>) {
        this.onBoardingViewPager = findViewById<ViewPager>(R.id._screenPager)
        this.onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        this.onBoardingViewPager.adapter = this.onBoardingViewPagerAdapter

        this.tabLayout = findViewById<TabLayout>(R.id._tab_indicator)
        this.tabLayout.setupWithViewPager(onBoardingViewPager)
    }

    private fun saveSharePreferences() {
        this.sharePreferences = applicationContext.getSharedPreferences("onBoardingPreferences", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = this.sharePreferences.edit()
        editor.putBoolean("isFirstTime", true)
        editor.apply()
    }

    private fun restoreSharePreferences(): Boolean {
        this.sharePreferences = applicationContext.getSharedPreferences("onBoardingPreferences", Context.MODE_PRIVATE)
        return this.sharePreferences.getBoolean("isFirstTime", false)
    }
}