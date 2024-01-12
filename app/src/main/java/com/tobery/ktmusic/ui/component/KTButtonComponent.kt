package com.tobery.ktmusic.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun KTNormalButton(
    modifier: Modifier = Modifier,
    enableButton: Boolean = true,
    color: Color = MaterialTheme.colorScheme.surfaceContainerHighest,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    button: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onClick: () -> Unit,
) {
    Button(
        onClick = singleClickable {
            onClick.invoke()
        },
        enabled = enableButton,
        shape = RoundedCornerShape(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            disabledContainerColor = color.copy(
                alpha = 0.1f
            ),
        ),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leadingIcon?.invoke()
            Text(
                text = button,
                style = MaterialTheme.typography.labelMedium,
                color = if (enableButton) textColor else MaterialTheme.colorScheme.onSecondaryContainer.copy(
                    alpha = 0.5f
                )
            )
            trailingIcon?.invoke()
        }

    }
}

fun Modifier.ktButtonSize(): Modifier = this
    .fillMaxWidth()
    .height(48.dp)

fun Modifier.bottomPadding(): Modifier = this
    .padding(bottom = 20.dp)