package com.cti.displayuni.response

data class BeforeStationFpaStatus(
    val end_shift_1_parameters_values: Any,
    val end_shift_2_parameters_values: Any,
    val start_shift_1_parameters_values: Boolean,
    val start_shift_2_parameters_values: Any,
    val station_id: String
)