package com.sphtech.mobile.api

import com.sphtech.mobile.data.MobileData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MobileDataApi {

    @GET("datastore_search")
     suspend fun getMobileData(@Query("resource_id") resourceId : String) : Response<MobileData>

    companion object{
        operator fun invoke() : MobileDataApi {

             return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://data.gov.sg/api/action/")
                .build()
                .create(MobileDataApi::class.java)

        }
    }
}