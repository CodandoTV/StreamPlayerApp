package com.codandotv.streamplayerapp.feature_onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.codandotv.streamplayerapp.feature_onboarding.screens.SplashScreen

class OnboardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen()
        }
    }
}