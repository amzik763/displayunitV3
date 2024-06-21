package com.cti.displayuni.repository

import android.util.Log
import androidx.compose.runtime.Composable
import com.cti.displayuni.networks.RetrofitBuilder
import com.cti.displayuni.R
import com.cti.displayuni.networks.OtherAPIs
import com.cti.displayuni.response.FpaData_res
import com.cti.displayuni.response.reading_Response
import com.cti.displayuni.utility.Actual_Param
import com.cti.displayuni.utility.CHECKSHEET
import com.cti.displayuni.utility.FILL_PARAMETERS
import com.cti.displayuni.utility.GETTASK
import com.cti.displayuni.utility.Setting_Param
import com.cti.displayuni.utility.chart_parameter
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.myComponents.authAPI
import com.cti.displayuni.utility.myComponents.mUiViewModel
import com.cti.displayuni.utility.myComponents.mainViewModel
import com.cti.displayuni.utility.myComponents.otherAPIs
import com.cti.displayuni.utility.readingStatusEnum
import com.cti.displayuni.utility.readingsStatusItems
import com.cti.displayuni.utility.responses.checkSheetResponse
import com.cti.displayuni.utility.responses.loginResponse
import com.cti.displayuni.utility.responses.taskResponse
import com.cti.displayuni.utility.showLogs
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class Repository() {

    init {
        Log.d("Repository:", "Created")
    }

    suspend fun loginUser(username: String, password: String) {
        try {
            loginResponse = authAPI.login(username, password)
            if (loginResponse.isSuccessful) {
                showLogs("AUTH: ", "Login Successful")
                myComponents.navController.popBackStack()
                myComponents.navController.navigate(GETTASK)
                mainViewModel.saveToken(loginResponse.body()?.token.toString())
                mainViewModel.name = loginResponse.body()?.fName.toString() + " " + loginResponse.body()?.lName.toString()
                mainViewModel.employeeId = loginResponse.body()?.employee_id.toString()
                mainViewModel.dob = loginResponse.body()?.dob.toString()
                mainViewModel.password = loginResponse.body()?.password.toString()
                mainViewModel.email = loginResponse.body()?.email.toString()
                mainViewModel.skill = loginResponse.body()?.skill.toString()
                mainViewModel.mobileNum = loginResponse.body()?.mobile.toString()
                mainViewModel.deviceId = mainViewModel.getStationValue()
                otherAPIs = RetrofitBuilder.createApiServiceWithToken()

            } else {
                showLogs("AUTH: ", "Login Unsuccessful")
            }
            if (loginResponse.code() == 401) {
                mUiViewModel.setDialogDetails(
                    "Incorrect Password",
                    "Please enter correct password!",
                    "Ask your Floor-In_Charge(Administrator) to provide you a new password",
                    R.drawable.ic_incorrect
                )
                mUiViewModel.showMessageDialog()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getTask(station_id: String) {
        try {
            mainViewModel.floorNum =
                mainViewModel.getStationValue().split(" ").take(2).joinToString(" ")

            Log.d("abcbc: ", station_id)
            taskResponse = otherAPIs.getTask(station_id)

            if (taskResponse.code() == 200) {
                mainViewModel.mChecksheetData.value =
                    taskResponse.let { it.body()?.check_sheet_datas }
                mainViewModel.checkSheetList.clear()
                mainViewModel.mChecksheetData.value?.forEach {
                    mainViewModel.checkSheetList.add("status")
                    mainViewModel.myChecksheetNotificationMap[it.csp_id] = "0"
                }
                taskResponse.body()?.work_operator_data?.toString()
                    ?.let { showLogs("WORK OPERATOR Size", it) }

                mainViewModel.ficID =
                    taskResponse.body()?.work_operator_data?.flrInchr_employee_id.toString()
                mainViewModel.pass.intValue = taskResponse.body()?.work_operator_data?.passed ?: -1
                showLogs("PASS", mainViewModel.pass.intValue.toString())
//                showLogs("FLR ID",taskResponse.body()?.work_operator_data.emoloyee_id_floor_incharge.toString() )
                showLogs(
                    "FLR ID",
                    taskResponse.body()?.work_operator_data?.flrInchr_employee_id.toString()
                )

                mainViewModel.fail.intValue = taskResponse.body()?.work_operator_data?.failed ?: -1
                showLogs("FAIL", mainViewModel.fail.intValue.toString())

                mainViewModel.shift.value = taskResponse.body()?.work_operator_data?.shift ?: ""
                showLogs("Shift", mainViewModel.shift.value)

                val dataListtemp = taskResponse.body()?.process_params_info
                mainViewModel.dataListSetting.clear()
                mainViewModel.dataListActual.clear()
                showLogs("PARAMS", dataListtemp?.size.toString())
                dataListtemp?.forEach {
                    if (it.FPA_status) {
                        mainViewModel.dataListSetting.add(
                            Setting_Param(
                                it.parameter_name,
                                it.parameter_no,
                                "",
                                it.unit ?: "",
                                it.min,
                                it.max
                            )
                        )
                    } else {
                        mainViewModel.dataListActual.add(
                            Actual_Param(
                                it.parameter_name,
                                it.parameter_no,
                                "",
                                it.unit ?: "",
                                it.min,
                                it.max
                            )
                        )
                    }
                }

                val chartParameters = mutableListOf<chart_parameter>()
                taskResponse.body()?.process_params_info?.forEach {
                    if (it.readings_is_available) {
                        val parameterName = it.parameter_name
                        val parameterNo = it.parameter_no
                        val min = it.min
                        val max = it.max
                        val values =
                            MutableList(5) { " " } // Initialize values list with default values
                        val chartParam =
                            chart_parameter(parameterName, parameterNo, min, max, values)
                        chartParameters.clear()
                        chartParameters.add(chartParam)
                        showLogs(
                            "DATA LIST ADDING",
                            it.readings_is_available.toString() + " " + it.parameter_name
                        )
                    }
                }
                mainViewModel.dataListChart.value = chartParameters
                showLogs("DATA LIST VALUES", mainViewModel.dataListChart.value.toString())


                //SHOULD BE SHIFTED TO OTHER API
                val p1 = mainViewModel.dataListSetting.joinToString(separator = ",") { setting ->
                    "${setting.param_no} ::: ${setting.param_value}"
                }
                showLogs("TASK P1: ", p1)
                val p2 = mainViewModel.dataListActual.joinToString(separator = ",") { actual ->
                    "${actual.param_no} ::: ${actual.param_value}"
                }
                showLogs("TASK P2: ", p2)
                showLogs("SHIFT TIME", "mainViewModel.startShiftTime")
                mainViewModel.startShiftTime =
                    taskResponse.body()?.work_operator_data?.start_shift_time.toString()
                showLogs("START SHIFT TIME", mainViewModel.startShiftTime)
                mainViewModel.endShiftTime =
                    taskResponse.body()?.work_operator_data?.end_shift_time.toString()
                showLogs("END SHIFT TIME", mainViewModel.endShiftTime)
                calculateShiftData()
                showLogs("PART NUM", taskResponse.body()?.work_operator_data?.part_no.toString())
                showLogs(
                    "PROCESS NUM",
                    taskResponse.body()?.work_operator_data?.process_no.toString()
                )
                mainViewModel.mProcessName =
                    taskResponse.body()?.work_operator_data?.process_no.toString()
                mainViewModel.mPartName =
                    taskResponse.body()?.work_operator_data?.part_no.toString()
                mainViewModel.totalAssigned.intValue =
                    taskResponse.body()?.work_operator_data?.total_assigned_task ?: 0
                mainViewModel.precedency_no.value =
                    taskResponse.body()?.work_operator_data?.station_precidency.toString()
                mainViewModel.temp_task_id.value =
                    taskResponse.body()?.work_operator_data?.temp_task_id.toString()

                showLogs("PRECEDENCY", mainViewModel.precedency_no.value)
                showLogs("TEMP TASK ID", mainViewModel.temp_task_id.value)

                if (mainViewModel.isShiftOver(mainViewModel.endShiftTime)) {
                    mUiViewModel.showThanksDialog()
                    return
                }

                if (mainViewModel.pass.intValue + mainViewModel.fail.intValue >= mainViewModel.totalAssigned.intValue) {
                    mUiViewModel.showThanksDialog()
                    return
                }

                try {
                    mainViewModel.fpa1.value =
                        taskResponse.body()?.station_fpa_data?.get(0)?.start_shift_1_parameters_values
                    mainViewModel.fpa2.value =
                        taskResponse.body()?.station_fpa_data?.get(0)?.start_shift_2_parameters_values
                    mainViewModel.fpa3.value =
                        taskResponse.body()?.station_fpa_data?.get(0)?.end_shift_1_parameters_values
                    mainViewModel.fpa4.value =
                        taskResponse.body()?.station_fpa_data?.get(0)?.end_shift_2_parameters_values
                } catch (e: Exception) {
                    showLogs("ERROR", "error while assigning FPA")
                }

                showLogs("FPA1", mainViewModel.fpa1.toString())
                showLogs("FPA2", mainViewModel.fpa2.toString())
                showLogs("FPA3", mainViewModel.fpa3.toString())
                showLogs("FPA4", mainViewModel.fpa4.toString())

                showLogs("RDATA", taskResponse.body()?.station_reading_data?.reading_1.toString())
                showLogs("RDATA", taskResponse.body()?.station_reading_data?.reading_2.toString())
                showLogs("RDATA", taskResponse.body()?.station_reading_data?.reading_3.toString())
                showLogs("RDATA", taskResponse.body()?.station_reading_data?.reading_4.toString())
                showLogs("RDATA", taskResponse.body()?.station_reading_data?.reading_5.toString())


                if (taskResponse.body()?.station_reading_data?.reading_1.toString() == "null") {
                    showLogs("RDATA2", "1 is null")

                } else {
                    mainViewModel.readingStatusList[0].readingStatusE = readingStatusEnum.completed
                    mainViewModel.isCompleted1[0] = true
                    mainViewModel.isCompleted2[0] = true
                    mainViewModel.isCompleted3[0] = true
                }

                if (taskResponse.body()?.station_reading_data?.reading_2.toString() == "null") {
                    showLogs("RDATA2", "2 is null")

                } else {
                    mainViewModel.readingStatusList[1].readingStatusE = readingStatusEnum.completed
                    mainViewModel.isCompleted1[1] = true
                    mainViewModel.isCompleted2[1] = true
                    mainViewModel.isCompleted3[1] = true
                }

                if (taskResponse.body()?.station_reading_data?.reading_3.toString() == "null") {
                    showLogs("RDATA2", "3 is null")

                } else {
                    mainViewModel.readingStatusList[2].readingStatusE = readingStatusEnum.completed
                    mainViewModel.isCompleted1[2] = true
                    mainViewModel.isCompleted2[2] = true
                    mainViewModel.isCompleted3[2] = true
                }

                if (taskResponse.body()?.station_reading_data?.reading_4.toString() == "null") {
                    showLogs("RDATA2", "4 is null")

                } else {
                    mainViewModel.readingStatusList[3].readingStatusE = readingStatusEnum.completed
                    mainViewModel.isCompleted1[3] = true
                    mainViewModel.isCompleted2[3] = true
                    mainViewModel.isCompleted3[3] = true
                }

                if (taskResponse.body()?.station_reading_data?.reading_5.toString() == "null") {
                    showLogs("RDATA2", "5 is null")
                } else {
                    mainViewModel.readingStatusList[4].readingStatusE = readingStatusEnum.completed
                    mainViewModel.isCompleted1[4] = true
                    mainViewModel.isCompleted2[4] = true
                    mainViewModel.isCompleted3[4] = true
                }

                if (mainViewModel.fpa4.value.isNullOrEmpty()) {
                    mainViewModel.FPACounter = 4
                    showLogs("FPA44", mainViewModel.fpa4.toString())

                }
                if (mainViewModel.fpa3.value.isNullOrEmpty()) {
                    mainViewModel.FPACounter = 3
                    showLogs("FPA33", mainViewModel.fpa3.toString())

                }
                if (mainViewModel.fpa2.value.isNullOrEmpty()) {
                    mainViewModel.FPACounter = 2
                    showLogs("FPA22", mainViewModel.fpa2.toString())

                }
                if (mainViewModel.fpa1.value.isNullOrEmpty()) {
                    mainViewModel.FPACounter = 1
                    showLogs("FPA11", mainViewModel.fpa1.toString())

                }

                mainViewModel.imageUrl =
                    taskResponse.body()?.urls.toString().split(",").map { it.trim() }
                        .toMutableList()

                showLogs("IMAGE URL", mainViewModel.imageUrl.toString())

                showLogs("FPA VAL: ", mainViewModel.FPACounter.toString())
                myComponents.navController.popBackStack()

                if (taskResponse.body()?.check_sheet_fill_status == true)
                    myComponents.navController.navigate(FILL_PARAMETERS)
                else
                    myComponents.navController.navigate(CHECKSHEET)
            } else if (taskResponse.code() == 404) {
                showLogs("TASK NOT FOUND", "404")
                mUiViewModel.hideMessageDialog()
                mUiViewModel.setDialogDetails(
                    "Task Not Found",
                    "Ask floor-in-charge to provide task",
                    "",
                    R.drawable.ic_notest
                )
                mUiViewModel.showMessageDialog()
            } else {
                mUiViewModel.hideMessageDialog()
                mUiViewModel.setDialogDetails("Try again!", "", "hold on...", R.drawable.ic_notest)
                mUiViewModel.showMessageDialog()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setReadingStatus(minutes: Int) {
//        val currentTime = getCurrentTime()
        var eachTime = minutes / 5
        var eachPart: Int? = taskResponse.body()?.work_operator_data?.total_assigned_task?.div(5)
        showLogs("TIME SET: ", eachTime.toString() + " out of " + minutes)
//      val item:readingsStatusItems = readingsStatusItems(eachTime, eachPart, readingStatusEnum.notAvailable)
//      mainViewModel.readingStatusList.add(item)
        mainViewModel.readingStatusList.clear()
        mainViewModel.readingStatusList.add(
            readingsStatusItems(
                eachTime,
                eachPart,
                readingStatusEnum.notAvailable
            )
        )
        mainViewModel.readingStatusList.add(
            readingsStatusItems(
                eachTime * 2,
                eachPart?.times(2),
                readingStatusEnum.notAvailable
            )
        )
        mainViewModel.readingStatusList.add(
            readingsStatusItems(
                eachTime * 3,
                eachPart?.times(3),
                readingStatusEnum.notAvailable
            )
        )
        mainViewModel.readingStatusList.add(
            readingsStatusItems(
                eachTime * 4,
                eachPart?.times(4),
                readingStatusEnum.notAvailable
            )
        )
        mainViewModel.readingStatusList.add(
            readingsStatusItems(
                eachTime * 5 - 5,
                eachPart?.times(5)?.minus(5),
                readingStatusEnum.notAvailable
            )
        )

        /*
        mainViewModel.readingStatusList.add(eachTime*2,eachPart?.times(2),readingStatusEnum.notAvailable)
        mainViewModel.readingStatusList.add(eachTime*3,eachPart?.times(3),readingStatusEnum.notAvailable)
        mainViewModel.readingStatusList.add(eachTime*4,eachPart?.times(4),readingStatusEnum.notAvailable)
        mainViewModel.readingStatusList.add(eachTime*5,eachPart?.times(5),readingStatusEnum.notAvailable)
*/
    }

    private fun calculateShiftData() {
        // Assuming startShiftTime and endShiftTime are in string format in the format "HH:MM:SS"
        val startParts = mainViewModel.startShiftTime.split(":")
        val endParts = mainViewModel.endShiftTime.split(":")
        // Extracting hours, minutes, and seconds
        val startHours = startParts[0].toInt()
        val startMinutes = startParts[1].toInt()
        val startSeconds = startParts[2].toInt()
        val endHours = endParts[0].toInt()
        val endMinutes = endParts[1].toInt()
        val endSeconds = endParts[2].toInt()

        // Calculating the duration
        val totalStartSeconds = startHours * 3600 + startMinutes * 60 + startSeconds
        val totalEndSeconds = endHours * 3600 + endMinutes * 60 + endSeconds
        val shiftDurationSeconds = totalEndSeconds - totalStartSeconds
        val shiftDurationMinutes = shiftDurationSeconds / 60

        mainViewModel.timeDiffer = shiftDurationMinutes.toString()
        showLogs("Shift Duration (minutes)", mainViewModel.timeDiffer)

        // Converting seconds to hours, minutes, and seconds
        val hours = shiftDurationSeconds / 3600
        val minutes = (shiftDurationSeconds % 3600) / 60
        val seconds = shiftDurationSeconds % 60

        val strHours = hours.toString()
        val strMinutes = minutes.toString()
        val strSeconds = seconds.toString()
        setReadingStatus(Integer.parseInt(mainViewModel.timeDiffer))
        // Outputting the duration
        showLogs("Shift Duration", "$strHours:$strMinutes:$strSeconds")

    }

    suspend fun checkSheetStatus(
        employeeId: String, ficID: String, stationValue: String, fillChecksheet: String
    ) {
        mUiViewModel.setDialogDetails(
            "Submitting...",
            "checksheet is being submitted",
            "hold on...",
            R.drawable.thanks
        )
        mUiViewModel.showMessageDialog()
        try {
            showLogs("CHECKSHEET DATAA:", employeeId)
            showLogs("CHECKSHEET DATAA:", ficID)
            showLogs("CHECKSHEET DATAA:", fillChecksheet)
            showLogs("CHECKSHEET DATAA:", stationValue)

            checkSheetResponse =
                otherAPIs.checkSheetData(employeeId, ficID, fillChecksheet, stationValue)

            if (checkSheetResponse.code() == 200) {
                mUiViewModel.hideMessageDialog()
                //move to last page page
                showLogs("CHECKSHEEEEEEt:", "ADDED")
                myComponents.navController.popBackStack()
//                myComponents.navController.navigate(CHECKSHEET)
                myComponents.navController.navigate(FILL_PARAMETERS)

            } else {
                showLogs("CHECKSHEEEEEEt:", "NOT ADDED")
                mUiViewModel.hideMessageDialog()
                mUiViewModel.setDialogDetails("Try again!", "", "hold on...", R.drawable.ic_notest)
                mUiViewModel.showMessageDialog()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mUiViewModel.hideMessageDialog()
            mUiViewModel.setDialogDetails("Try again!", "", "hold on...", R.drawable.ic_notest)
            mUiViewModel.showMessageDialog()
        }
    }

    suspend fun notify(stationValue: String, csp_id: String, floor_no: String): Result<String> {
        mUiViewModel.setDialogDetails("Notifying...", "", "hold on...", R.drawable.thanks)
        mUiViewModel.showMessageDialog()
        return try {
            showLogs("FLN", floor_no)
            val notifyResponse = otherAPIs.operatorNotify(stationValue, csp_id, floor_no)
            if (notifyResponse.code() == 200) {
                showLogs(
                    "NOTIFICATION:",
                    "Notification sent: ${notifyResponse.body()?.Notification_id}"
                )
                mUiViewModel.hideMessageDialog()
                val notificationId = notifyResponse.body()?.Notification_id ?: ""
                Result.success(notificationId)
            } else {
                mUiViewModel.hideMessageDialog()
                mUiViewModel.setDialogDetails("Try again!", "", "hold on...", R.drawable.ic_notest)
                mUiViewModel.showMessageDialog()
                showLogs("NOTIFICATION:", "Notification not sent")
                Result.failure(Exception("Notification not sent"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mUiViewModel.hideMessageDialog()
            mUiViewModel.setDialogDetails("Try again!", "", "hold on...", R.drawable.ic_notest)
            mUiViewModel.showMessageDialog()

            Result.failure(Exception("Notification not sent"))
        }
    }

    suspend fun addData(failed: String, passed: String, station_id: String, i: Int): Boolean {

        showLogs(
            "CHECK CHECK: ",
            "${mainViewModel.shouldCheckTemporaryFPA}   ${mainViewModel.dontAddData}"
        )

        if (mainViewModel.shouldCheckTemporaryFPA) {

            showLogs("NEWFPA: ","INSIDE addDATA : temporary FPA CHECK")
            checkFPA(
                mainViewModel.precedency_no.value,
                mainViewModel.mPartName,
                mainViewModel.temp_task_id.value
            )

            if (mainViewModel.dontAddData){

                if(mainViewModel.overrideDontAddData){

                }else {


                    mUiViewModel.setDialogDetails(
                        "Waiting....",
                        "",
                        "Waiting for FPA to complete on other stations",
                        R.drawable.thanks
                    )
                    mUiViewModel.showOverrideDialog()
                    showLogs("NEWFPA: ", "INSIDE addDATA : Don't add data ")
                    return false
                }
            }
            if (mainViewModel.FPACounter == 2) {

            }

            if (mainViewModel.FPACounter == 4) {

            }
        }

        mUiViewModel.setDialogDetails("Submitting...", "", "hold on...", R.drawable.thanks)
        mUiViewModel.showMessageDialog()
        var p = mainViewModel.pass.intValue;
        var f = mainViewModel.fail.intValue
        if (i == 1)
            ++p
        else if (i == 0)
            ++f
        try {
            val addDataResponse = otherAPIs.addData(f.toString(), p.toString(), station_id)
            if (addDataResponse.code() == 200) {
                if (i == 1) mainViewModel.pass.intValue++
                else if (i == 0) mainViewModel.fail.intValue++
                showLogs(
                    "ADDWITHPARAM",
                    mainViewModel.pass.intValue.toString() + " " + mainViewModel.fail.intValue.toString()
                )
                showLogs("ADDWITHPARAM", p.toString() + " " + f.toString())
                showLogs("ADD DATA:", "Data Added Successfully")
                mUiViewModel.hideMessageDialog()
                mUiViewModel.clearFields.intValue++
//                mainViewModel.addDataNow = false
                mainViewModel.shouldCheckTemporaryFPA = false
                mainViewModel.dontAddData = true
                return true
            } else {
                mUiViewModel.hideMessageDialog()
                mUiViewModel.setDialogDetails(
                    "Try again!",
                    "Error in adding data",
                    "",
                    R.drawable.ic_notest
                )
                mUiViewModel.showMessageDialog()
                showLogs("ADD DATA:", "Data Not Added")
                return false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mUiViewModel.hideMessageDialog()
            mUiViewModel.setDialogDetails(
                "Try again!",
                "Error in adding data",
                "",
                R.drawable.ic_notest
            )
            mUiViewModel.showMessageDialog()
            return false
        }
    }

    fun fillChecksheet(): String {
        var checkSheetStatus = ""

        try {
            for ((index, checksheet) in mainViewModel.mChecksheetData.value!!.withIndex()) {
                checkSheetStatus += "${checksheet.csp_id} ${mainViewModel.checkSheetList[index]},"
            }
            val new_checkSheetStatus = checkSheetStatus.dropLast(1)

            showLogs("CHECKSHEEEET", new_checkSheetStatus)

        } catch (e: Exception) {
            e.printStackTrace()
            mUiViewModel.hideMessageDialog()
            mUiViewModel.setDialogDetails("Try again!", "", "hold on...", R.drawable.ic_notest)
            mUiViewModel.showMessageDialog()
        }
        return checkSheetStatus
    }

    suspend fun addDataWithParams(i: Int) {
        mUiViewModel.setDialogDetails("SUBMITTING FPA", "", "Hold on.....", R.drawable.thanks)
        mUiViewModel.showMessageDialog()
        var p = mainViewModel.pass.intValue
        var f = mainViewModel.fail.intValue
        if (i == 1)
            ++p
        else if (i == 0)
            ++f
        //SHOULD BE SHIFTED TO OTHER API

        val p1 = mainViewModel.dataListSetting.joinToString(separator = ",") { setting ->
            "${setting.param_no} ::: ${setting.param_value}"
        }
        showLogs("TASK P1: ", p1)
        val p2 = mainViewModel.dataListActual.joinToString(separator = ",") { actual ->
            "${actual.param_no} ::: ${actual.param_value}"
        }

        showLogs("TASK P2: ", p2)
        val p1p2 = "$p1, $p2".trim(',')

        showLogs("Combined String: ", p1p2)
        showLogs("Station ID: ", mainViewModel.getStationValue())

        lateinit var dataResponseWithParam: Response<FpaData_res>

        try {
            showLogs("FCENTER", mainViewModel.FPACounter.toString())
            showLogs("PASS FAIL", p.toString() + " " + f.toString())
            showLogs(
                "PASS FAIL Viewmodel",
                "${mainViewModel.pass.intValue} " + " " + mainViewModel.fail.intValue
            )
            when (mainViewModel.FPACounter) {
                1 -> {
                    dataResponseWithParam = otherAPIs.fpaData(
                        f.toString(),
                        p.toString(),
                        p1p2,
                        mainViewModel.getStationValue()
                    )
                }

                2 -> {
                    dataResponseWithParam = otherAPIs.fpaData2(
                        f.toString(),
                        p.toString(),
                        p1p2,
                        mainViewModel.getStationValue()
                    )
                }

                3 -> {
                    dataResponseWithParam = otherAPIs.fpaData3(
                        f.toString(),
                        p.toString(),
                        p1p2,
                        mainViewModel.getStationValue()
                    )
                }

                4 -> {
                    dataResponseWithParam = otherAPIs.fpaData4(
                        f.toString(),
                        p.toString(),
                        p1p2,
                        mainViewModel.getStationValue()
                    )
                }
            }

            if (dataResponseWithParam.isSuccessful) {

                //updateCirclesColor
                when (mainViewModel.FPACounter) {
                    1 -> {
                        mainViewModel.fpa1.value = p1p2

                    }

                    2 -> {
                        mainViewModel.fpa2.value = p1p2
                        mainViewModel.shouldCheckTemporaryFPA = true
                    }

                    3 -> {
                        mainViewModel.fpa3.value = p1p2

                    }

                    4 -> {
                        mainViewModel.fpa4.value = p1p2
                        mainViewModel.shouldCheckTemporaryFPA = true

                    }
                }


                mainViewModel.isFPATime = false
                mUiViewModel.hideMessageDialog()
                mainViewModel.FPACounter++

                if (i == 1)
                    ++mainViewModel.pass.intValue
                else if (i == 0)
                    ++mainViewModel.fail.intValue

                showLogs(
                    "ADDWITHPARAM",
                    mainViewModel.pass.intValue.toString() + " " + mainViewModel.fail.intValue.toString()
                )

                //show toast successful
                showLogs("ADDWITHPARAM", "successfull")
                showLogs("ADDWITHPARAM FPA Counter", "${mainViewModel.FPACounter}")
                mUiViewModel.clearFields.intValue++


            } else {
                mainViewModel.isFPATime = false
                mUiViewModel.hideMessageDialog()
                mUiViewModel.setDialogDetails(
                    "Try again!",
                    "Error in adding FPA data",
                    "",
                    R.drawable.ic_notest
                )
                mUiViewModel.showMessageDialog()
                showLogs("ADDWITHPARAM", "un-successfull")
                showLogs("ADDWITHPARAMFAIL", dataResponseWithParam.message())
                showLogs("ADDWITHPARAMFAIL", dataResponseWithParam.errorBody().toString())

                //show retry or failed dialog box

            }
        } catch (e: Exception) {
            mainViewModel.isFPATime = false
            mUiViewModel.hideMessageDialog()
            mUiViewModel.setDialogDetails(
                "Try again!",
                "Error in adding FPA data",
                "",
                R.drawable.ic_notest
            )
            mUiViewModel.showMessageDialog()
            showLogs("ADDWITHPARAM Error", e.printStackTrace().toString())
        }

    }

    suspend fun runReadingAPI(readingIndex: Int, reading1: String, index: Int) {
        mUiViewModel.setDialogDetails(
            "SUBMITTING",
            "Adding reading",
            "hold on...",
            R.drawable.thanks
        )
        mUiViewModel.showMessageDialog()
        try {
            showLogs("READING API DATA", mainViewModel.getStationValue())
            mainViewModel.dataListChart.value?.get(readingIndex)?.parameter_no?.let {
                showLogs(
                    "READING API DATA",
                    it
                )
            }
            showLogs("READING API DATA", reading1)

            mainViewModel.mState.value = true
            lateinit var myReadingResonse: Response<reading_Response>
            when (index) {
                0 -> {
                    showLogs(
                        "READING API RUN 1",
                        mainViewModel.getStationValue() + " " +
                                mainViewModel.dataListChart.value?.get(readingIndex)?.parameter_no.toString() + " " +
                                reading1
                    )
                    myReadingResonse = otherAPIs.readingOne(
                        mainViewModel.getStationValue(),
                        mainViewModel.dataListChart.value?.get(readingIndex)?.parameter_no.toString(),
                        reading1
                    )
                }

                1 -> {
                    showLogs(
                        "READING API RUN 2",
                        mainViewModel.getStationValue() + " " +
                                mainViewModel.dataListChart.value?.get(readingIndex)?.parameter_no.toString() + " " +
                                reading1
                    )
                    myReadingResonse = otherAPIs.readingTwo(
                        mainViewModel.getStationValue(),
                        mainViewModel.dataListChart.value?.get(readingIndex)?.parameter_no.toString(),
                        reading1
                    )
                }

                2 -> {
                    showLogs(
                        "READING API RUN 3",
                        mainViewModel.getStationValue() + " " +
                                mainViewModel.dataListChart.value?.get(readingIndex)?.parameter_no.toString() + " " +
                                reading1
                    )

                    myReadingResonse = otherAPIs.readingThree(
                        mainViewModel.getStationValue(),
                        mainViewModel.dataListChart.value?.get(readingIndex)?.parameter_no.toString(),
                        reading1
                    )
                }

                3 -> {
                    showLogs(
                        "READING API RUN 4",
                        mainViewModel.getStationValue() + " " +
                                mainViewModel.dataListChart.value?.get(readingIndex)?.parameter_no.toString() + " " +
                                reading1
                    )
                    myReadingResonse = otherAPIs.readingFour(
                        mainViewModel.getStationValue(),
                        mainViewModel.dataListChart.value?.get(readingIndex)?.parameter_no.toString(),
                        reading1
                    )
                }

                4 -> {
                    showLogs(
                        "READING API RUN 5",
                        mainViewModel.getStationValue() + " " +
                                mainViewModel.dataListChart.value?.get(readingIndex)?.parameter_no.toString() + " " +
                                reading1
                    )

                    myReadingResonse = otherAPIs.readingFive(
                        mainViewModel.getStationValue(),
                        mainViewModel.dataListChart.value?.get(readingIndex)?.parameter_no.toString(),
                        reading1
                    )
                }
            }
            if (myReadingResonse.isSuccessful) {
                mUiViewModel.hideMessageDialog()

                when (readingIndex) {
                    0 -> {
                        mainViewModel.isCompleted1[index] = true
                    }

                    1 -> {
                        mainViewModel.isCompleted2[index] = true
                    }

                    else -> {
                        mainViewModel.isCompleted3[index] = true
                    }
                }
                //can be improved to check all three param or two params if present, to do later

                if (mainViewModel.isCompleted1[index]) {
                    mainViewModel.readingStatusList[index].readingStatusE =
                        readingStatusEnum.completed

                }

                showLogs("READING API", "value added")
                mainViewModel.isCompleted1.forEach {
                    showLogs("ISCOMPLETED 1: ", it.toString())
                }
                mainViewModel.isCompleted2.forEach {
                    showLogs("ISCOMPLETED 2: ", it.toString())
                }
                mainViewModel.isCompleted3.forEach {
                    showLogs("ISCOMPLETED 3: ", it.toString())
                }
                showLogs("COMPLETED VALUES", "")
                mainViewModel.mState.value = false
                mUiViewModel.hideMessageDialog()
            } else {
                //showToastMessageToTryAgain
                mUiViewModel.hideMessageDialog()
                mUiViewModel.setDialogDetails("Try again!", "", "hold on...", R.drawable.ic_notest)
                mUiViewModel.showMessageDialog()
                showLogs("READING API", "got error")
                mainViewModel.mState.value = false

            }
        } catch (e: Exception) {
            mainViewModel.mState.value = false
            mUiViewModel.hideMessageDialog()
            mUiViewModel.setDialogDetails("Try again!", "", "hold on...", R.drawable.ic_notest)
            mUiViewModel.showMessageDialog()
            //show toast or dialog that could not add
        }
    }

    suspend fun addFailedData() {
        val p = mainViewModel.pass.intValue
        val f = mainViewModel.fail.intValue + 1
        mUiViewModel.setDialogDetails(
            "SUBMITTING",
            "Adding failed part",
            "hold on...",
            R.drawable.thanks
        )
        mUiViewModel.showMessageDialog()
        try {
            val myFailResponse = otherAPIs.addFailedData(
                f.toString(),
                p.toString(),
                mainViewModel.partID,
                mainViewModel.mPartName,
                mainViewModel.mSelectedReason,
                mainViewModel.getStationValue(),
                mainViewModel.mark
            )

            if (myFailResponse.isSuccessful) {
                showLogs("PROCESS FAILED INFO", "Success")
                ++mainViewModel.fail.intValue
                mainViewModel.mark = ""
                mUiViewModel.hideMessageDialog()
                mUiViewModel.hideRejectReasonDialog()
                mainViewModel.partID = ""
                mainViewModel.mSelectedReason = ""

            } else {
                showLogs("PROCESS FAILED INFO", "Failed")
                mUiViewModel.hideMessageDialog()
                mUiViewModel.setDialogDetails(
                    "Try again!",
                    "",
                    "1. Check if Part ID entered is empty or duplicate",
                    R.drawable.ic_notest
                )
                mUiViewModel.showMessageDialog()
                mainViewModel.mark = ""
                mainViewModel.partID = ""
                mainViewModel.mSelectedReason = ""

            }
        } catch (e: HttpException) {
            showLogs("PROCESS FAILED INFO", "Error http")
            mUiViewModel.hideMessageDialog()
            mUiViewModel.setDialogDetails("Try again!", "", "Network error", R.drawable.ic_notest)
            mUiViewModel.showMessageDialog()
            mainViewModel.mark = ""
            mainViewModel.partID = ""
            mainViewModel.mSelectedReason = ""
            e.printStackTrace()

        } catch (e: Exception) {
            showLogs("PROCESS FAILED INFO", "Error")
            mUiViewModel.hideMessageDialog()
            mUiViewModel.setDialogDetails(
                "Try again!",
                "",
                "Submission failed. please try again...\n1. Check if Part ID entered is empty or duplicate",
                R.drawable.ic_notest
            )
            mUiViewModel.showMessageDialog()
            e.printStackTrace()
        }
    }
    suspend fun getReasonData() {
        try {
            showLogs("REASON FLOOR", mainViewModel.mProcessName)
            val myReasonResponse = otherAPIs.getReasons(mainViewModel.mProcessName)
            if (myReasonResponse.isSuccessful) {
                mUiViewModel.showRejectReasonDialog()
                myReasonResponse.body()?.reasons?.forEach {
                    showLogs("REASON IN LIST", it.reason + " " + it.reason_id)
                }
                mainViewModel.mReasonList.value = myReasonResponse.body()
                mainViewModel.isReasonRetrieved = true
                mainViewModel.mReasonList.value?.reasons?.forEach {
                    showLogs("REASON IN LIST", it.reason + " " + it.reason_id)
                }
            } else {
                showLogs("REASON RESPONSE: ", "Failed, try again")
            }
        } catch (e: Exception) {
            showLogs("REASON RESPONSE: ", "error")
            e.printStackTrace()
            mUiViewModel.hideMessageDialog()
            mUiViewModel.setDialogDetails("Try again!", "", "hold on...", R.drawable.ic_notest)
            mUiViewModel.showMessageDialog()
        }
    }
    suspend fun checkFPA(precedency_no: String, part_no: String, temp_task_id: String) {

        try {

            showLogs("Precedency", precedency_no)
            showLogs("Part No.", part_no)
            showLogs("TempTaskId", temp_task_id)

            var fpa_check_count = 0

            when (mainViewModel.FPACounter) {
                1 -> {
                    fpa_check_count = 0
                }

                2 -> {
                    fpa_check_count = 1
                }

                3 -> {
                    fpa_check_count = 0
                }

                4 -> {
                    fpa_check_count = 1
                }
            }


            if (mainViewModel.shouldCheckTemporaryFPA) {
                showLogs("NEWFPA: ","INSIDE checkFPA : FPA COUNT IS 2 ")

                fpa_check_count = 2
            }

            showLogs("FPA CHECK COUNT", fpa_check_count.toString())

            val fpaCheck_Res =
                otherAPIs.checkFPA(precedency_no, part_no, temp_task_id, fpa_check_count.toString())


            if(fpaCheck_Res.code() == 444){
                mUiViewModel.setDialogDetails(
                    "FPA DETAILS",
                    "",
                    "FPA Failed or not done",
                    R.drawable.ic_notest
                )
                mUiViewModel.showMessageDialog()
                showLogs("NEWFPA: ","INSIDE checkFPA : Response is 444 ")

                return
            }
            showLogs("NEWFPA: ","INSIDE checkFPA : Response code is ${fpaCheck_Res.code()}")

            if (fpaCheck_Res.code() == 200 || fpaCheck_Res.code() == 210) {

                showLogs("CHECK FPA STATUS: ", "FPA Successful")
                showLogs("FPA CHECK STATUS RESPONSE", fpaCheck_Res.body().toString())
                showLogs(
                    "FPA RESPONSE 1",
                    fpaCheck_Res.body()?.before_station_fpa_status?.start_shift_1_parameters_values.toString()
                )
                showLogs(
                    "FPA RESPONSE 2",
                    fpaCheck_Res.body()?.before_station_fpa_status?.start_shift_2_parameters_values.toString()
                )
                showLogs(
                    "FPA RESPONSE 3",
                    fpaCheck_Res.body()?.before_station_fpa_status?.end_shift_1_parameters_values.toString()
                )
                showLogs(
                    "FPA RESPONSE 4",
                    fpaCheck_Res.body()?.before_station_fpa_status?.end_shift_2_parameters_values.toString()
                )
                mainViewModel.otherfpa1.value =
                    fpaCheck_Res.body()?.before_station_fpa_status?.start_shift_1_parameters_values.toString()
                mainViewModel.otherfpa2.value =
                    fpaCheck_Res.body()?.before_station_fpa_status?.start_shift_2_parameters_values.toString()
                mainViewModel.otherfpa3.value =
                    fpaCheck_Res.body()?.before_station_fpa_status?.end_shift_1_parameters_values.toString()
                mainViewModel.otherfpa4.value =
                    fpaCheck_Res.body()?.before_station_fpa_status?.end_shift_2_parameters_values.toString()
                showLogs(
                    "FPA RESPONSE OTHERS 1",
                    mainViewModel.otherfpa1.value.toString()
                )
                showLogs(
                    "FPA RESPONSE OTHERS 2",
                    mainViewModel.otherfpa2.value.toString()
                )
                showLogs(
                    "FPA RESPONSE OTHERS 3",
                    mainViewModel.otherfpa3.value.toString()
                )
                showLogs(
                    "FPA RESPONSE OTHERS 4",
                    mainViewModel.otherfpa4.value.toString()
                )
            }

            showLogs("NEWFPA: ","INSIDE checkFPA : FPA COUNTER ${mainViewModel.FPACounter} ")
            showLogs("NEWFPA: ","INSIDE checkFPA : FPA CHECK COUNT ${fpa_check_count} ")
            showLogs("NEWFPA: ","INSIDE checkFPA : SHOULD TEMP FPA ${mainViewModel.shouldCheckTemporaryFPA} ")
            showLogs("NEWFPA: ","INSIDE checkFPA : NOT ADD DATA ${mainViewModel.dontAddData} ")

            if(mainViewModel.precedency_no.value == "1") {
                showLogs("NEWFPA: ","INSIDE checkFPA : Precdency 1")

                if (fpaCheck_Res.code() == 210  && mainViewModel.FPACounter == 1 && fpa_check_count == 0) {
                    mainViewModel.submitPartInfoWithParams(1)
                }
            else if (fpaCheck_Res.code() == 200 && mainViewModel.FPACounter == 2 && fpa_check_count == 1) {
                    if (mainViewModel.otherfpa1.value.toString() == "null"){
                        mUiViewModel.setDialogDetails("FPA DETAILS","","FPA Failed or not done",R.drawable.ic_notest)
                        mUiViewModel.showMessageDialog()
                        return
                    }else{
                        mainViewModel.submitPartInfoWithParams(1)
                    }
                }
            else if(fpaCheck_Res.code() == 200 && mainViewModel.FPACounter == 3 && fpa_check_count ==2){
                        if(mainViewModel.otherfpa2.value.toString() == "null"){
                            mUiViewModel.setDialogDetails("FPA DETAILS","","FPA Failed or not done",R.drawable.ic_notest)
                            mUiViewModel.showMessageDialog()
                            return
                        }else{
                            mainViewModel.dontAddData = false
                        }
                    }
            else if(fpaCheck_Res.code() == 210 && mainViewModel.FPACounter == 3 && fpa_check_count == 0){
                                mainViewModel.submitPartInfoWithParams(1)
                        }
            else if(fpaCheck_Res.code() == 200 && mainViewModel.FPACounter == 4 && fpa_check_count == 1){
                                if(mainViewModel.otherfpa3.value.toString() == "null"){
                                    mUiViewModel.setDialogDetails("FPA DETAILS","","FPA Failed or not done",R.drawable.ic_notest)
                                    mUiViewModel.showMessageDialog()
                                    return
                                }else{
                                    mainViewModel.submitPartInfoWithParams(1)
                                }
                            }
            else if(fpaCheck_Res.code() == 200 && mainViewModel.FPACounter == 5 && fpa_check_count == 2){
                                    if(mainViewModel.otherfpa4.value.toString() == "null"){
                                        mUiViewModel.setDialogDetails("FPA DETAILS","","FPA Failed or not done",R.drawable.ic_notest)
                                        mUiViewModel.showMessageDialog()
                                        return
                                    }else{
                                        mainViewModel.dontAddData = false
                                    }
                                }
            }else{
                showLogs("NEWFPA: ","INSIDE checkFPA : Precdency is greater than 1")

                if (fpaCheck_Res.code() == 200  && mainViewModel.FPACounter == 1 && fpa_check_count == 0) {
                    if (mainViewModel.otherfpa1.value.toString() == "null"){
                        mUiViewModel.setDialogDetails("FPA DETAILS","","FPA Failed or not done",R.drawable.ic_notest)
                        mUiViewModel.showMessageDialog()
                        return
                    }else{
                        mainViewModel.submitPartInfoWithParams(1)
                    }
                }
                else if (fpaCheck_Res.code() == 200 && mainViewModel.FPACounter == 2 && fpa_check_count == 1) {
                    if (mainViewModel.otherfpa2.value.toString() == "null"){
                        mUiViewModel.setDialogDetails("FPA DETAILS","","FPA Failed or not done",R.drawable.ic_notest)
                        mUiViewModel.showMessageDialog()
                        return
                    }else{
                        mainViewModel.submitPartInfoWithParams(1)
                    }
                }
                else if(fpaCheck_Res.code() == 200 && mainViewModel.FPACounter == 3 && fpa_check_count ==2){
                    if(mainViewModel.otherfpa2.value.toString() == "null"){
                        mUiViewModel.setDialogDetails("FPA DETAILS","","FPA Failed or not done",R.drawable.ic_notest)
                        mUiViewModel.showMessageDialog()
                        return
                    }else{
                        mainViewModel.dontAddData = false
                    }
                }
                else if(fpaCheck_Res.code() == 200 && mainViewModel.FPACounter == 3 && fpa_check_count == 0){
                    if (mainViewModel.otherfpa3.value.toString() == "null"){
                        mUiViewModel.setDialogDetails("FPA DETAILS","","FPA Failed or not done",R.drawable.ic_notest)
                        mUiViewModel.showMessageDialog()
                        return
                    }else{
                        mainViewModel.submitPartInfoWithParams(1)
                    }
                }
                else if(fpaCheck_Res.code() == 200 && mainViewModel.FPACounter == 4 && fpa_check_count == 1){
                    if(mainViewModel.otherfpa4.value.toString() == "null"){
                        mUiViewModel.setDialogDetails("FPA DETAILS","","FPA Failed or not done",R.drawable.ic_notest)
                        mUiViewModel.showMessageDialog()
                        return
                    }else{
                        mainViewModel.submitPartInfoWithParams(1)
                    }
                }
                else if(fpaCheck_Res.code() == 200 && mainViewModel.FPACounter == 5 && fpa_check_count == 2){
                    if(mainViewModel.otherfpa4.value.toString() == "null"){
                        mUiViewModel.setDialogDetails("FPA DETAILS","","FPA Failed or not done",R.drawable.ic_notest)
                        mUiViewModel.showMessageDialog()
                        return
                    }else{
                        mainViewModel.dontAddData = false
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    suspend fun checkFPA2(precedency_no: String, part_no: String, temp_task_id: String) {

        try {

            showLogs("Precedency", precedency_no)
            showLogs("Part No.", part_no)
            showLogs("TempTaskId", temp_task_id)

            var fpa_check_count = 0

            when (mainViewModel.FPACounter) {
                1 -> {
                    fpa_check_count = 0
                }

                2 -> {
                    fpa_check_count = 1
                }

                3 -> {
                    fpa_check_count = 0
                }

                4 -> {
                    fpa_check_count = 1
                }
            }


            if (mainViewModel.shouldCheckTemporaryFPA) {
                fpa_check_count = 2
            }

            showLogs("FPA CHECK COUNT", fpa_check_count.toString())

            val fpaCheck_Res =
                otherAPIs.checkFPA(precedency_no, part_no, temp_task_id, fpa_check_count.toString())


            if (fpaCheck_Res.code() == 200 || fpaCheck_Res.code() == 210) {

                showLogs("CHECK FPA STATUS: ", "FPA Successful")
                showLogs("FPA CHECK STATUS RESPONSE", fpaCheck_Res.body().toString())
                showLogs(
                    "FPA RESPONSE",
                    fpaCheck_Res.body()?.before_station_fpa_status?.start_shift_1_parameters_values.toString()
                )
                showLogs(
                    "FPA RESPONSE",
                    fpaCheck_Res.body()?.before_station_fpa_status?.start_shift_2_parameters_values.toString()
                )
                showLogs(
                    "FPA RESPONSE",
                    fpaCheck_Res.body()?.before_station_fpa_status?.end_shift_1_parameters_values.toString()
                )
                showLogs(
                    "FPA RESPONSE",
                    fpaCheck_Res.body()?.before_station_fpa_status?.end_shift_2_parameters_values.toString()
                )
                mainViewModel.otherfpa1.value =
                    fpaCheck_Res.body()?.before_station_fpa_status?.start_shift_1_parameters_values.toString()
                mainViewModel.otherfpa2.value =
                    fpaCheck_Res.body()?.before_station_fpa_status?.start_shift_2_parameters_values.toString()
                mainViewModel.otherfpa3.value =
                    fpaCheck_Res.body()?.before_station_fpa_status?.end_shift_1_parameters_values.toString()
                mainViewModel.otherfpa4.value =
                    fpaCheck_Res.body()?.before_station_fpa_status?.end_shift_2_parameters_values.toString()

            }

            if (fpaCheck_Res.code() == 200) {

                if (mainViewModel.FPACounter == 1 && fpaCheck_Res.body()?.before_station_fpa_status?.start_shift_1_parameters_values.toString() == "null") {
                    mUiViewModel.setDialogDetails(
                        "",
                        "Please wait for previous station to complete FPA",
                        "",
                        R.drawable.ic_notest
                    )
                    mUiViewModel.showMessageDialog()
                    showLogs("CHECK FPA STATUS: /", "FPA 1 return")
                    return
                }

                if (mainViewModel.FPACounter == 2) {
                    if (fpaCheck_Res.body()?.before_station_fpa_status?.start_shift_2_parameters_values.toString() == "null") {
                        mUiViewModel.setDialogDetails(
                            "",
                            "Please wait for other stations to complete FPA",
                            "",
                            R.drawable.ic_notest
                        )
                        mUiViewModel.showMessageDialog()
                        showLogs("CHECK FPA STATUS: ", "FPA 2 return")
                        return
                    } else {
                        mainViewModel.shouldCheckTemporaryFPA = true
//                        mainViewModel.addDataNow = true

                    }

                }
//                    mainViewModel.checkTempFPA = true

                if (mainViewModel.FPACounter == 3 && fpaCheck_Res.body()?.before_station_fpa_status?.end_shift_1_parameters_values.toString() == "null") {
                    mUiViewModel.setDialogDetails(
                        "",
                        "Please wait for previous station to complete FPA",
                        "",
                        R.drawable.ic_notest
                    )
                    mUiViewModel.showMessageDialog()
                    mainViewModel.otherfpa3.value =
                        fpaCheck_Res.body()?.before_station_fpa_status?.end_shift_1_parameters_values.toString()

                    showLogs("CHECK FPA STATUS: ", "FPA 3 return")
                    return
                }

                if (mainViewModel.FPACounter == 4 && fpaCheck_Res.body()?.before_station_fpa_status?.end_shift_1_parameters_values.toString() == "null") {
                    mUiViewModel.setDialogDetails(
                        "",
                        "Please wait for previous station to complete FPA",
                        "",
                        R.drawable.ic_notest
                    )
                    mUiViewModel.showMessageDialog()

                    if (mainViewModel.shouldCheckTemporaryFPA) {
                        mainViewModel.shouldCheckTemporaryFPA = false
//                        mainViewModel.addDataNow = false
                    } else {
                        mainViewModel.shouldCheckTemporaryFPA = true
//                        mainViewModel.addDataNow = true
                    }

                    showLogs("CHECK FPA STATUS: ", "FPA 4 return")
                    return
                }
                if (!mainViewModel.shouldCheckTemporaryFPA) {
                    mainViewModel.submitPartInfoWithParams(1)
//                    mainViewModel.addDataNow = false

                } else {
//                    mainViewModel.addDataNow = true
                }
//
//                showLogs(
//                    "CHECK CHECK TEMP: ",
//                    "${mainViewModel.shouldCheckTemporaryFPA}   ${mainViewModel.addDataNow}"
//                )


            }

            if (fpaCheck_Res.code() == 210) {
                mainViewModel.submitPartInfoWithParams(1)
//                showLogs(
//                    "CHECK CHECK TEMP: ",
//                    "${mainViewModel.shouldCheckTemporaryFPA}   ${mainViewModel.addDataNow}"
//                )
            }



            if (fpaCheck_Res.code() == 444) {
                mUiViewModel.setDialogDetails(
                    "FPA DETAILS",
                    "",
                    "FPA Failed or not done",
                    R.drawable.ic_notest
                )
                mUiViewModel.showMessageDialog()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    suspend fun getCheckSheetStatusBack(s: String?): Result<String> {
        return try {
            if (s != null) {
                val response = otherAPIs.checkSheetStatusBack(s)
                if (response.isSuccessful) {
                    showLogs("NOTPI", "gn ng + ${response.body()?.approvedStatus}")

                    response.body()?.let {
                        if (it.approvedStatus == "true" || it.approvedStatus == "True") {
                            showLogs("NOTPI", "g + ${it.approvedStatus}")

                            Result.success("true")
                        } else {
                            showLogs("NOTPI", "n + ${it.approvedStatus}")

                            Result.success("fail")
                        }
                    } ?: Result.failure(IOException("Response body is null"))
                } else {
                    Result.failure(IOException("Error: ${response.message()}"))
                }
            } else {
                Result.failure(IOException("Invalid input"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

        suspend fun FailedFpa() {
            try {

                val FPA_Shift = when(mainViewModel.FPACounter) {

                    1 ->  { "start_shift_1" }

                    2 -> { "start_shift_2" }

                    3 -> { "start_shift_3" }

                    4 -> { "start_shift_3" }

                    else -> {""}
                }

                val fpaFailedResponse = otherAPIs.fpaFailed(mainViewModel.itemId, mainViewModel.getStationValue(), "1", FPA_Shift, mainViewModel.shift.value)

                if (fpaFailedResponse.code() == 201){

                    showLogs("Failed FPA ADD: " , "Add Fail FPA successful")

                }
                else{
                    showLogs("Failed FPA ADD: " , "Add Fail FPA unsuccessful")

                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

}


