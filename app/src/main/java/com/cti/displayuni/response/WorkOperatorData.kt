package com.cti.displayuni.response

data class WorkOperatorData(
    val assigned_by_owner: String,
    val employee_id: String,
    val end_shift_time: String,
    val failed: Int,
    val filled: Any,
    val left_for_rework: Int,
    val operator_login_status: Boolean,
    val part_no: String,
    val passed: Int,
    val process_no: String,
    val shift: String,
    val start_shift_time: String,
    val station_id: String,
    val total_assigned_task: Int,
    val flrInchr_employee_id: String,

)