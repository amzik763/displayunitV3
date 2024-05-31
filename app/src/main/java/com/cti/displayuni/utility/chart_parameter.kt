package com.cti.displayuni.utility

data class chart_parameter(
    var parameter_name: String,
    var parameter_no: String,
    var min: String?,
    var max: String?,
    var values: MutableList<String> = MutableList(5) { "" }
)


