package com.cti.displayuni.response

data class ProcessParamsInfo(
    val FPA_status: Boolean,
    val belongs_to_part: String,
    val max: String,
    val min: String,
    val parameter_name: String,
    val parameter_no: String,
    val process_no: String,
    val readings_is_available: Boolean,
    val unit: String
)