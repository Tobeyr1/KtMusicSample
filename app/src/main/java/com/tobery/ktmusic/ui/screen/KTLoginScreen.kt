package com.tobery.ktmusic.ui.screen

import android.view.autofill.AutofillManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tobery.ktmusic.R
import com.tobery.ktmusic.ui.component.KTNormalButton
import com.tobery.ktmusic.ui.component.KTProgressDialog
import com.tobery.ktmusic.ui.component.bottomPadding
import com.tobery.ktmusic.ui.component.ktButtonSize
import com.tobery.ktmusic.ui.component.viewModelOf

@Composable
fun KTLoginScreen(
    viewModel: KTLoginViewModel = viewModelOf(),
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        ) {
        val context = LocalContext.current
        val auto: AutofillManager = context.getSystemService(AutofillManager::class.java)
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            Text(
                text = stringResource(id = R.string.login_lab_title),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Start,
            )
            Spacer(modifier = Modifier.height(24.dp))
            //phone
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .requiredHeightIn(min = 56.dp),
                value =  viewModel.phone,
                onValueChange = { viewModel.phone = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number,
                ),
                )
            //password
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .requiredHeightIn(min = 56.dp),
                value =  viewModel.password,
                onValueChange = { viewModel.password = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password,
                ),
                visualTransformation = PasswordVisualTransformation(),
            )
            Spacer(modifier = Modifier.height(24.dp))
            KTNormalButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .ktButtonSize(),
                button = "test",
            ) {
                viewModel.requestRecentSongs()
            }
            Spacer(modifier = Modifier.weight(1f))
            KTNormalButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .bottomPadding()
                    .ktButtonSize(),
                button = stringResource(id = R.string.login_lab_title),
                ) {
                viewModel.loginWithPhone()
            }
        }
    }

    KTProgressDialog(show = viewModel.showProgress)
}