package com.tobery.ktmusic.ui.component

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
inline fun <reified VM : ViewModel> viewModelOf(keepAlive: Boolean = false): VM {
    val vmStore = LocalView.current.findViewTreeViewModelStoreOwner()
    val activity = LocalView.current.context as ComponentActivity
    val factory = SavedStateViewModelFactory((LocalView.current.context.applicationContext as Application), activity,null)
    return if (vmStore != null) viewModel(
        viewModelStoreOwner = if (keepAlive) vmStore else LocalViewModelStoreOwner.current
            ?: vmStore,
        factory = factory,
    ) else viewModel(factory = factory)
}