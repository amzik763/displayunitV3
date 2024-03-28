package com.cti.displayuni.response

import com.google.gson.annotations.SerializedName

data class sup_response(
    @SerializedName("Response:")
    val Response: String,
    val building_no: String,
    val employee_id: String,
    val fName: String,
    val floor_no: String,
    val lName: String,
    val token: String
)