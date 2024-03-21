package com.cti.displayuni.networks

import com.cti.displayuni.response.login_response
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OtherAPIs {

    @FormUrlEncoded
    @POST("/operator/auth")
    suspend fun check(

    ): Response<String>

}