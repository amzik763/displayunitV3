package com.cti.displayuni.utility

data class chart_parameter(
    var parameter_name: String,
    var parameter_no: String,
    var values: List<String> = List(5) { "" }
)


