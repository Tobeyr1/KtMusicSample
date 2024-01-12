@file:OptIn(SavedStateHandleSaveableApi::class)
package com.tobery.ktmusic.ui.viewmodel

import android.app.Application
import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.tobery.libnetwork.KTApiClient
import com.tobery.libnetwork.KTApiNetworkClient
import kotlinx.coroutines.launch

open class KTBaseViewModel(
    application: Application,
    state: SavedStateHandle,
): AndroidViewModel(application) {

    var showProgress by state.saveable {
        mutableStateOf(false)
    }
        private set

    init {
        viewModelScope.launch {
            showProgress = false
        }
    }

    protected val apiClient : KTApiClient get() {
        return KTApiNetworkClient()
    }

    open fun showProgress() {
        showProgress = true
    }

    open fun dismissProgress() {
        showProgress = false
    }

    fun getString(@StringRes stringId: Int): String =
        getApplication<Application>().getString(stringId)

    fun getString(@StringRes stringId: Int, vararg args: Any): String =
        getApplication<Application>().getString(stringId, args)

    val resources: Resources get() = getApplication<Application>().resources

}