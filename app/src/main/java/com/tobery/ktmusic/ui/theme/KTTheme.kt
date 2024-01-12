package com.tobery.ktmusic.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.tobery.ktmusic.ui.component.NoRippleInteractionSource
import com.tobery.ktmusic.ui.component.viewModelOf
import com.tobery.ktmusic.ui.viewmodel.KTAppViewModel

var LocalFullWindowEnabled = staticCompositionLocalOf {
    false
}

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),

)

@Composable
fun KtMusicTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    appViewModel: KTAppViewModel = viewModelOf(keepAlive = true),
    content: @Composable () -> Unit
) {
    val ctx = LocalContext.current
    val colorScheme by remember {
        derivedStateOf {
            when {
                appViewModel.enableDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                    if (darkTheme) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
                }

                darkTheme -> DarkColorScheme
                else -> LightColorScheme
            }
        }
    }

    val windowBgColor = colorScheme.background
    LaunchedEffect(appViewModel) {
        val activity = ctx as Activity
        appViewModel.isDarkMode = darkTheme
        val window = activity.window
        window.decorView.setBackgroundColor(windowBgColor.toArgb())
    }

    KTSystemBarSide(colorScheme)
    CompositionLocalProvider(
        LocalDensity provides Density(
            LocalDensity.current.density,
            1f
        )
    ) {
        val focusManager = LocalFocusManager.current
        Box(modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = NoRippleInteractionSource(),
                indication = null,
            ) {
                focusManager.clearFocus()
            }) {
            MaterialTheme(
                colorScheme = colorScheme,
                typography = Typography,
                content = content
            )
        }
    }
}

@Composable
private fun KTSystemBarSide(
    colorScheme: ColorScheme,
    darkTheme: Boolean = isSystemInDarkTheme(),
    appViewModel: KTAppViewModel = viewModelOf(keepAlive = true)
) {
    CompositionLocalProvider(
        LocalFullWindowEnabled provides appViewModel.enableFullWindow,
    ) {
        val view = LocalView.current
        val window = (view.context as Activity).window
        val windowController = WindowCompat.getInsetsController(window, view)
        if (!view.isInEditMode) {
            SideEffect {
                window.statusBarColor = if (appViewModel.enableFullWindow) Color.Transparent.toArgb()
                 else colorScheme.background.toArgb()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    window.isNavigationBarContrastEnforced = true
                }
                windowController.isAppearanceLightStatusBars = !darkTheme
                windowController.isAppearanceLightNavigationBars = !darkTheme
                window.decorView.setBackgroundColor(colorScheme.background.toArgb())
                WindowCompat.setDecorFitsSystemWindows(window,false)
                windowController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                if (appViewModel.enableFullWindow) {
                    windowController.hide(WindowInsetsCompat.Type.navigationBars())
                } else {
                    windowController.show(WindowInsetsCompat.Type.navigationBars())
                }
            }
        }
    }
}