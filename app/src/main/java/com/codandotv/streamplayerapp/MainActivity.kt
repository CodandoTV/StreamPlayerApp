package com.codandotv.streamplayerapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.codandotv.streamplayerapp.feature_onboarding.OnboardingActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@MainActivity, OnboardingActivity::class.java))
    }
}