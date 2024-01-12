package com.tobery.ktmusic.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalAutofillTree
import androidx.compose.ui.semantics.Role
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 * Created by Daniel on 2023/3/29.
 */

inline fun Modifier.singleClickable(
    debounceDuration: Long = 400L,
    enabled: Boolean = true,//中间这三个是clickable自带的参数
    rippleEnabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    crossinline onClick: () -> Unit
): Modifier = composed {
    var lastClickTime by remember { mutableStateOf(value = 0L) }
    val eventAction: () -> Unit = {
        val currentTimeMillis = System.currentTimeMillis()
        val skipFirstTime = debounceDuration == Long.MAX_VALUE && lastClickTime == 0L
        if (currentTimeMillis - debounceDuration >= lastClickTime || skipFirstTime) {
            onClick()
            lastClickTime = currentTimeMillis
        }
    }
    if (rippleEnabled) {
        clickable(enabled, onClickLabel, role, eventAction)
    } else {
        clickable(
            interactionSource = NoRippleInteractionSource(),
            indication = null,
            enabled = enabled,
            onClickLabel = onClickLabel,
            role = role,
            onClick = eventAction
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.autofill(
    autofillTypes: List<AutofillType>,
    onFill: ((String) -> Unit),
) = composed {
    val autofill = LocalAutofill.current
    val autofillNode = AutofillNode(onFill = onFill, autofillTypes = autofillTypes)
    LocalAutofillTree.current += autofillNode

    this
        .onGloballyPositioned {
            autofillNode.boundingBox = it.boundsInWindow()
        }
        .onFocusChanged { focusState ->
            autofill?.run {
                if (focusState.isFocused) {
                    requestAutofillForNode(autofillNode)
                } else {
                    cancelAutofillForNode(autofillNode)
                }
            }
        }
}

class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true

}