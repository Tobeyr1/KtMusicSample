package com.tobery.ktmusic.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
inline fun singleClickable(
    debounceDuration: Long = 400L,
    crossinline onClick: () -> Unit
): () -> Unit {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    val skipFirstTime = debounceDuration == Long.MAX_VALUE && lastClickTime == 0L
    return {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - debounceDuration >= lastClickTime || skipFirstTime) {
            onClick()
            lastClickTime = currentTimeMillis
        }
    }
}