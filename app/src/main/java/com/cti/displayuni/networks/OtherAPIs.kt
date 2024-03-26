package com.cti.displayuni.networks

import com.cti.displayuni.response.allDataV2
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface OtherAPIs {

    @FormUrlEncoded
    @POST("/operator/get_task")
    suspend fun getTask(
    @Field("station_id") station_id : String
//    @Field("shift") shift : String
    ): Response<allDataV2>


}