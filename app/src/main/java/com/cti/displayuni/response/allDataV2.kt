package com.cti.displayuni.response

data class allDataV2(
    val check_sheet_datas: List<CheckSheetData>,
    val process_params_info: List<ProcessParamsInfo>,
    val workOperatorData: List<work_operator_data>,
    val total_check_sheet_params: Int,
    val emoloyee_id_floor_incharge: String,
    val urls: String
)