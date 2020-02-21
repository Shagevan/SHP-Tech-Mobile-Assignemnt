package com.sphtech.mobile.repository

import com.sphtech.mobile.api.MobileDataApi
import com.sphtech.mobile.api.SafeApiRequest

class MobileDataRepository(private val api : MobileDataApi) : SafeApiRequest() {

    suspend fun getMobileData(resourceId:String) = apiRequest{api.getMobileData(resourceId)}

}