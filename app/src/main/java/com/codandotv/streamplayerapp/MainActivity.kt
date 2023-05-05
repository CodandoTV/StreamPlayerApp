package com.codandotv.streamplayerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codandotv.streamplayerapp.core_ui.theme.StreamPlayerTheme
import com.codandotv.streamplayerapp.feature_list_streams.presentation.screens.ListStreamsScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreamPlayerApp()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StreamPlayerApp() {
    StreamPlayerTheme {
        Scaffold { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                ListStreamsScreen()
            }
        }
    }
}
