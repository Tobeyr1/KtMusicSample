package com.tobery.libnetwork

import com.tobery.libnetwork.entity.LoginPayload
import com.tobery.libnetwork.entity.RecentSongsPayload

abstract class KTApiClient {

    abstract suspend fun requestLoginWithPhone(phone: String,password:String): KTCommonResponse<LoginPayload>?

    abstract suspend fun requestRecentSongs(limit: Int) : KTCommonResponse<RecentSongsPayload>?
}