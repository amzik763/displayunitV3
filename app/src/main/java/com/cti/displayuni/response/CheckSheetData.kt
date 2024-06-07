package com.cti.displayuni.response

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow

data class CheckSheetData(
    val control_method: String,
    val csp_id: String,
    val csp_name: String,
    val csp_name_hindi: String,
    val frequency: String,
    val specification: String,
)