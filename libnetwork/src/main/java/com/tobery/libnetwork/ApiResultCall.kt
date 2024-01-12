package com.tobery.libnetwork

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiResultCall(private val delegate: Call<Any>): Call<Any> {


    override fun clone(): Call<Any> {
        return delegate.clone()
    }

    override fun execute(): Response<Any> {
        return delegate.execute()
    }

    override fun isExecuted(): Boolean {
        return delegate.isExecuted
    }

    override fun cancel() {
        return delegate.cancel()
    }

    override fun isCanceled(): Boolean {
        return delegate.isCanceled
    }

    override fun request(): Request {
        return delegate.request()
    }

    override fun timeout(): Timeout {
        return delegate.timeout()
    }

    override fun enqueue(callback: Callback<Any>) {
        delegate.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                callback.onResponse(this@ApiResultCall,Response.success(
                    KTCommonResponse(response.body(), null)
                ))
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                callback.onResponse(this@ApiResultCall, Response.success(
                    KTCommonResponse(null,
                        KTCommonError(errorCode = "", errorMessage = t.message ?: "error")
                    )
                ))
            }
        })
    }

}