@file:OptIn(SavedStateHandleSaveableApi::class)
package com.tobery.ktmusic.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable

class KTAppViewModel(
    application: Application,
    state: SavedStateHandle,
) : KTBaseViewModel(application, state) {

    var enableFullWindow: Boolean by state.saveable {
        mutableStateOf(false)
    }

    var enableDynamicColor: Boolean by state.saveable {
        mutableStateOf(false)
    } // Dynamic color is available on Android 12+

    var isDarkMode: Boolean by state.saveable {
        mutableStateOf(false)
    }
}