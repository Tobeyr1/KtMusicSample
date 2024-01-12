package com.tobery.ktmusic.ui.screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.saveable
import com.tobery.ktmusic.ui.viewmodel.KTBaseViewModel
import com.tobery.ktmusic.util.printLog
import kotlinx.coroutines.launch

class KTLoginViewModel(
    application: Application,
    state: SavedStateHandle,
): KTBaseViewModel(application, state) {

    var phone by state.saveable {
        mutableStateOf("")
    }

    var password by state.saveable {
        mutableStateOf("")
    }

    fun loginWithPhone() {
        viewModelScope.launch {
            showProgress()
            val response = apiClient.requestLoginWithPhone(phone, password)
            dismissProgress()
        }
    }

    fun requestRecentSongs() {
        viewModelScope.launch {
            showProgress()
            val response = apiClient.requestRecentSongs(limit = 2)
            dismissProgress()
        }
    }
}