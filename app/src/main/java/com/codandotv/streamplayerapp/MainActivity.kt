package com.codandotv.streamplayerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.codandotv.streamplayerapp.screens.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            SplashScreen()
        }
    }
}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun StreamPlayerApp() {
//    StreamPlayerTheme {
//        Scaffold { innerPadding ->
//            Box(Modifier.padding(innerPadding)) {
//                ListStreamsScreen()
//            }
//        }
//    }
//}
