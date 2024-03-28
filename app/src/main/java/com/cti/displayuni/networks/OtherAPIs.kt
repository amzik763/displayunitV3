package com.cti.displayuni.networks

import com.cti.displayuni.response.allDataV2
import com.cti.displayuni.response.login_response
import com.cti.displayuni.response.notify_response
import com.cti.displayuni.response.sup_response
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

    @FormUrlEncoded
    @POST("/floorincharge/login")
    suspend fun supLogin(
        @Field("employee_id") employee_id : String,
        @Field("password") password : String
    ): Response<sup_response>

    @FormUrlEncoded
    @POST("/operator/notify")
    suspend fun operatorNotify(
        @Field("station_id") station_id: String,
        @Field("csp_id") csp_id : String,
        @Field("floor_no") floor_no : String
    ): Response<notify_response>
}