package com.tobery.libnetwork

import android.util.Log
import com.tobery.libnetwork.entity.LoginPayload
import com.tobery.libnetwork.entity.RecentSongsPayload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext


private const val BASE_URL = "https://netease-cloud-music-api-nine-umber.vercel.app/"
private const val SCAN_TIME_OUT = 15L
class KTApiNetworkClient(): KTApiClient() {

    private fun initRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(KTCommonCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initOkHttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder()
            .readTimeout(SCAN_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(SCAN_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(SCAN_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor) //添加打印拦截器
            .build()
    }

    private fun makeApi(
        enableCache:Boolean = false,
        cacheTag: String? = null,
        ): KTApi {
        return initRetrofit(initOkHttp()).create(KTApi::class.java)
    }

    private suspend fun <T> requestCall(
        context: CoroutineContext = Dispatchers.IO,
        call: suspend () -> KTCommonResponse<T>?
    ):KTCommonResponse<T>? = withContext(context) {
        val response: KTCommonResponse<T>? = try {
            call.invoke()
        } catch (e: Exception) {
            KTCommonResponse(
                payload = null,
                error = KTCommonError(
                    errorCode = "",
                    errorMessage = e.message ?:""
                )
            )
        }
        return@withContext response
    }

    override suspend fun requestLoginWithPhone(
        phone: String,
        password: String
    ): KTCommonResponse<LoginPayload>? =
       requestCall {
           makeApi().requestLoginWithPhone(phone, password)
       }

    override suspend fun requestRecentSongs(limit: Int): KTCommonResponse<RecentSongsPayload>? =
        requestCall { 
            makeApi().requestRecentSongs(limit)
        }
}