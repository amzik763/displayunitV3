package com.cti.displayuni.response

data class allDataV2(
    val check_sheet_datas: List<CheckSheetData>,
    val check_sheet_fill_status: Boolean,
//    val emoloyee_id_floor_incharge: String,
    val process_params_info: List<ProcessParamsInfo>,
    val total_check_sheet_params: Int,
    val urls: String,
    val precedency_no: String,
    val temp_task_id: String,
    val work_operator_data: WorkOperatorData,
    val station_reading_data: readingData?,
    val station_fpa_data: List<StationFpaData>
)

