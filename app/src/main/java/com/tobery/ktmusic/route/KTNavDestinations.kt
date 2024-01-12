package com.tobery.ktmusic.route

import androidx.annotation.Keep
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

object KtMusicRoutePath {
    const val LOGIN_PATH = "loginPath"
}

@Keep
sealed class NavScreen(
    open val route: String,
    open val arguments: List<NamedNavArgument> = emptyList(),
    open val deepLinks: List<NavDeepLink> = emptyList(),
)

object LoginFlow {

    data object LoginScreen: NavScreen(
        route = KtMusicRoutePath.LOGIN_PATH,
    )
}