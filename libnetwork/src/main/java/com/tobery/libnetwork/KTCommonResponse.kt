package com.tobery.libnetwork

import androidx.annotation.Keep

@Keep
data class KTCommonResponse<T>(
    val payload: T?,
    val error: KTCommonError? = null,
)

@Keep
data class KTCommonError(
    val errorCode: String,
    val errorMessage: String,
)