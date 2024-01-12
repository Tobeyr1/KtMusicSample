package com.tobery.ktmusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tobery.ktmusic.route.KTNavHost
import com.tobery.ktmusic.ui.theme.KtMusicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            KtMusicTheme {
                KTNavHost()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KtMusicTheme {}
}