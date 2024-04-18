package com.cti.displayuni.networks

import com.cti.displayuni.response.allDataV2
import com.cti.displayuni.response.checksheet_Status
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
        @Field("station_id") station_id: String
//      @Field("shift") shift : String
    ): Response<allDataV2>

    @FormUrlEncoded
    @POST("/floorincharge/login")
    suspend fun supLogin(
        @Field("employee_id") employee_id: String,
        @Field("password") password: String
    ): Response<sup_response>

    @FormUrlEncoded
    @POST("/operator/notify")
    suspend fun operatorNotify(
        @Field("station_id") station_id: String,
        @Field("csp_id") csp_id: String,
        @Field("floor_no") floor_no: String
    ): Response<notify_response>

    @FormUrlEncoded
    @POST("/operator/add_checksheet_data")
    suspend fun checkSheetData(
        @Field("oprtr_employee_id") oprtr_employee_id: String,
        @Field("flrInchr_employee_id") flrInchr_employee_id: String,
        @Field("status_datas") status_datas: String,
        @Field("station_id") station_id: String
    ): Response<checksheet_Status>


    @FormUrlEncoded
    @POST("/operator/add_and_update/readings")
    suspend fun readingOne(
        @Field("station_id") station_id: String,
        @Field("parameter_no") parameter_no: String,
        @Field("reading_1") reading_1: String,
    ): Response<String>

    @FormUrlEncoded
    @POST("/operator/add_and_update/readings")
    suspend fun readingFive(
        @Field("station_id") station_id: String,
        @Field("parameter_no") parameter_no: String,
        @Field("reading_5") reading_5: String,
    ): Response<String>

    @FormUrlEncoded
    @POST("/operator/add_and_update/readings")
    suspend fun readingTwo(
        @Field("station_id") station_id: String,
        @Field("parameter_no") parameter_no: String,
        @Field("reading_2") reading_2: String,
    ): Response<String>

    @FormUrlEncoded
    @POST("/operator/add_and_update/readings")
    suspend fun readingThree(
        @Field("station_id") station_id: String,
        @Field("parameter_no") parameter_no: String,
        @Field("reading_3") reading_3: String,
    ): Response<String>

    @FormUrlEncoded
    @POST("/operator/add_and_update/readings")
    suspend fun readingFour(
        @Field("station_id") station_id: String,
        @Field("parameter_no") parameter_no: String,
        @Field("reading_4") reading_4: String,
    ): Response<String>

    @FormUrlEncoded
    @POST("/operator/add_and_update/work")
    suspend fun addData(
        @Field("failed") failed: String,
        @Field("passed") passed: String,
        @Field("station_id") station_id: String,
    ): Response<String>


}