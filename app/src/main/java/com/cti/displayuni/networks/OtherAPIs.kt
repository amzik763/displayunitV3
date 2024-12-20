package com.cti.displayuni.networks

import com.cti.displayuni.response.FPAres
import com.cti.displayuni.response.FpaData_res
import com.cti.displayuni.response.addData_Response
import com.cti.displayuni.response.allDataV2
import com.cti.displayuni.response.checkSheetStatusBack
import com.cti.displayuni.response.checksheetNotificationResponse
import com.cti.displayuni.response.checksheet_Status
import com.cti.displayuni.response.fpa_failed
import com.cti.displayuni.response.myReasons
import com.cti.displayuni.response.reading_Response
import com.cti.displayuni.response.sup_response
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OtherAPIs {

    @FormUrlEncoded
    @POST("/operator/get_task")
    suspend fun getTask(
        @Field("station_id") station_id: String,
      @Field("employee_id") employee_id : String
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
    ): Response<checksheetNotificationResponse>

    @FormUrlEncoded
    @POST("/operator/add_checksheet_data")
    suspend fun checkSheetData(
        @Field("oprtr_employee_id") oprtr_employee_id: String,
        @Field("flrInchr_employee_id") flrInchr_employee_id: String,
        @Field("status_datas") status_datas: String,
        @Field("station_id") station_id: String,
        @Field("shift") shift: String
    ): Response<checksheet_Status>
    
    @FormUrlEncoded
    @POST("/operator/add_and_update/readings")
    suspend fun readingOne(
        @Field("station_id") station_id: String,
        @Field("parameter_no") parameter_no: String,
        @Field("reading_1") reading_1: String,
    ): Response<reading_Response>

    @FormUrlEncoded
    @POST("/operator/add_and_update/readings")
    suspend fun readingFive(
        @Field("station_id") station_id: String,
        @Field("parameter_no") parameter_no: String,
        @Field("reading_5") reading_5: String,
    ): Response<reading_Response>



    @FormUrlEncoded
    @POST("/operator/add_and_update/readings")
    suspend fun readingTwo(
        @Field("station_id") station_id: String,
        @Field("parameter_no") parameter_no: String,
        @Field("reading_2") reading_2: String,
    ): Response<reading_Response>

    @FormUrlEncoded
    @POST("/operator/add_and_update/readings")
    suspend fun readingThree(
        @Field("station_id") station_id: String,
        @Field("parameter_no") parameter_no: String,
        @Field("reading_3") reading_3: String,
    ): Response<reading_Response>



    @FormUrlEncoded
    @POST("/operator/add_and_update/readings")
    suspend fun readingFour(
        @Field("station_id") station_id: String,
        @Field("parameter_no") parameter_no: String,
        @Field("reading_4") reading_4: String,
    ): Response<reading_Response>

    @FormUrlEncoded
    @POST("/operator/add_and_update/work")
    suspend fun addData(
        @Field("failed") failed: String,
        @Field("passed") passed: String,
        @Field("station_id") station_id: String
    ): Response<addData_Response>


    @FormUrlEncoded
    @POST("/operator/add_failed_items")
    suspend fun addFailedData(
        @Field("failed") failed: String,
        @Field("passed") passed: String,
        @Field("item_id") item_id: String,
        @Field("part_no") part_no: String,
        @Field("reason_id") reason_id: String,
        @Field("station_id") station_id: String,
        @Field("remark") remark: String
    ): Response<addData_Response>


    @FormUrlEncoded
    @POST("/operator/get_reasons_for_items")
    suspend fun getReasons(
        @Field("process_no") process_no: String
    ): Response<myReasons>

    @FormUrlEncoded
    @POST("/operator/add_fpa_data")
    suspend fun fpaData(
        @Field("failed") failed: String,
        @Field("passed") passed: String,
        @Field("start_shift_1_parameters_values") start_shift_1_parameters_values: String,
        @Field("station_id") station_id: String
    ): Response<FpaData_res>


    @FormUrlEncoded
    @POST("/operator/add_fpa_data")
    suspend fun fpaData2(
        @Field("failed") failed: String,
        @Field("passed") passed: String,
        @Field("start_shift_2_parameters_values") start_shift_2_parameters_values: String,
        @Field("station_id") station_id: String
    ): Response<FpaData_res>


    @FormUrlEncoded
    @POST("/operator/add_fpa_data")
    suspend fun fpaData3(
        @Field("failed") failed: String,
        @Field("passed") passed: String,
        @Field("end_shift_1_parameters_values") end_shift_1_parameters_values: String,
        @Field("station_id") station_id: String
    ): Response<FpaData_res>

    @FormUrlEncoded
    @POST("/operator/add_fpa_data")
    suspend fun fpaData4(
        @Field("failed") failed: String,
        @Field("passed") passed: String,
        @Field("end_shift_2_parameters_values") end_shift_2_parameters_values: String,
        @Field("station_id") station_id: String
    ): Response<FpaData_res>

    @FormUrlEncoded
    @POST("/operator/check_fpa_status")
    suspend fun checkFPA(
        @Field("precedency_no") precedency_no: String,
        @Field("part_no") part_no: String,
        @Field("temp_task_id") temp_task_id: String,
        @Field("fpa_check_count") fpa_check_count: String
        ): Response<FPAres>

    @FormUrlEncoded
    @POST("/operator/get_csp_status")
    suspend fun checkSheetStatusBack(
        @Field("notification_id") notification_id: String,
    ): Response<checkSheetStatusBack>

    @FormUrlEncoded
    @POST("/operator/add_fpa_failed")
    suspend fun fpaFailed(
        @Field("item_id") item_id: String,
        @Field("station_id") station_id: String,
        @Field("line_no") line_no: String,
        @Field("fpa_failed_count") fpa_failed_count: String,
        @Field("fpa_shift") fpa_shift: String,
        @Field("shift") shift: String,
    ): Response<fpa_failed>


    @FormUrlEncoded
    @POST("/operator/get_readings_for_chart")
    suspend fun readingChart(
        @Field("parameter_no") parameter_no: String,
        @Field("shift") shift: String,
        @Field("station_id") station_id: String
    ) : Response<JsonObject>
}