package com.cti.displayuni.response

import com.google.gson.annotations.SerializedName

data class checkSheetStatusBack(
    @SerializedName("Approved status")
    val approvedStatus: String
)