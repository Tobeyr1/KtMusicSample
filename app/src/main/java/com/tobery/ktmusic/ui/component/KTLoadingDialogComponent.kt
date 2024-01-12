package com.tobery.ktmusic.ui.component

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.tobery.ktmusic.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun KTProgressDialog(
    show: Boolean,
    allowBackToDismiss: Boolean = true,
    allowBackground: Boolean = false
) {
    var showState by remember {
        mutableStateOf(show)
    }

    LaunchedEffect(show) {
        showState = show
    }

    if (showState) {
        BackHandler(true) {
            if (allowBackToDismiss) {
                showState = false
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInteropFilter {
                    // Consume touch event
                    true
                }
                .background(Color.Black.copy(alpha = if (allowBackground) 0.15f else 0.0f))
                .wrapContentSize(align = Alignment.Center)
        ) {
            KTLoading(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(40.dp)
            )
        }
    }
}

@Composable
fun KTLoading(modifier: Modifier = Modifier) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()
    Image(
        modifier = modifier,
        painter = rememberAsyncImagePainter(R.drawable.loading_progress,imageLoader),
        contentDescription = null)
}