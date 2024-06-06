package com.cti.displayuni.response

import com.google.gson.annotations.SerializedName

data class checkSheetStatusBack(
    @SerializedName("Approved_status")
    val approvedStatus: String
)