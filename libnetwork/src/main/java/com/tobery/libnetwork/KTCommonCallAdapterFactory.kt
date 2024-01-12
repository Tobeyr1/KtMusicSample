package com.tobery.libnetwork

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class KTCommonCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val rawType = getRawType(returnType)
        if (rawType != Call::class.java) {
            return null
        }
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)

        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }
        if (getRawType(observableType) != KTCommonResponse::class.java) {
            return null
        }
        val bodyType = getParameterUpperBound(0, observableType)
        return KTCommonCallAdapter(bodyType)
    }

}