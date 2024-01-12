package com.tobery.ktmusic.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tobery.ktmusic.route.graph.LOGIN_FLOW_ROUTE
import com.tobery.ktmusic.route.graph.loginFlowGraph


@Composable
fun KTNavHost() {
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = LOGIN_FLOW_ROUTE) {
        loginFlowGraph(controller)
    }
}