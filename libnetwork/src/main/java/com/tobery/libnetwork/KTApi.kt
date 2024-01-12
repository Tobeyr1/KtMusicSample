package com.tobery.libnetwork

import com.tobery.libnetwork.entity.LoginPayload
import com.tobery.libnetwork.entity.RecentSongsPayload
import retrofit2.http.GET
import retrofit2.http.Query

interface KTApi {

    @GET("login/cellphone")
    suspend fun requestLoginWithPhone(
        @Query("phone") phone: String,
        @Query("password") password:String): KTCommonResponse<LoginPayload>

    @GET("record/recent/song") //最近播放音乐
    suspend fun requestRecentSongs(@Query("limit") limit: Int) : KTCommonResponse<RecentSongsPayload>

}