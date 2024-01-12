package com.tobery.ktmusic.route.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tobery.ktmusic.route.LoginFlow
import com.tobery.ktmusic.ui.screen.KTLoginScreen

const val LOGIN_FLOW_ROUTE = "loginFlow"

fun NavGraphBuilder.loginFlowGraph(controller: NavController) {
    navigation(startDestination = LoginFlow.LoginScreen.route, route = LOGIN_FLOW_ROUTE) {
        composable(route = LoginFlow.LoginScreen.route) {
            KTLoginScreen()
        }
    }
}