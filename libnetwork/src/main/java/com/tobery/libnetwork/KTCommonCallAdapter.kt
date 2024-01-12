package com.tobery.libnetwork

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type
import kotlin.coroutines.suspendCoroutine

class KTCommonCallAdapter(private val responseType: Type) : CallAdapter<Any,Call<Any>> {

    override fun responseType(): Type  = responseType
    override fun adapt(call: Call<Any>): Call<Any> {
        return ApiResultCall(call)
    }


}