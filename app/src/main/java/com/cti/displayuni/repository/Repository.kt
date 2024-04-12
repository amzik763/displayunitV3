package com.cti.displayuni.repository

import android.util.Log
import com.cti.displayuni.networks.RetrofitBuilder
import com.cti.displayuni.R
import com.cti.displayuni.utility.Actual_Param
import com.cti.displayuni.utility.CHECKSHEET
import com.cti.displayuni.utility.GETTASK
import com.cti.displayuni.utility.Setting_Param
import com.cti.displayuni.utility.chart_parameter
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.myComponents.authAPI
import com.cti.displayuni.utility.myComponents.mUiViewModel
import com.cti.displayuni.utility.myComponents.mainViewModel
import com.cti.displayuni.utility.myComponents.otherAPIs
import com.cti.displayuni.utility.responses.checkSheetResponse
import com.cti.displayuni.utility.responses.loginResponse
import com.cti.displayuni.utility.responses.taskResponse
import com.cti.displayuni.utility.showLogs

class Repository () {

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

            Log.d("abcbc: ", station_id)
            taskResponse = otherAPIs.getTask(station_id)

            if (taskResponse.code() == 200) {
                //move to checksheet page
                mainViewModel.mChecksheetData.value = taskResponse.let { it.body()?.check_sheet_datas }
                mainViewModel.mChecksheetData.value?.forEach {
                    mainViewModel.checkSheetList.add("status")
                }
                taskResponse.body()?.work_operator_data?.toString()?.let { showLogs("WORK OPERATOR Size", it) }

                mainViewModel.ficID = taskResponse.body()?.emoloyee_id_floor_incharge.toString()
                mainViewModel.pass = taskResponse.body()?.work_operator_data?.passed ?: -1

                val dataListtemp = taskResponse.body()?.process_params_info
                showLogs("PARAMS", dataListtemp?.size.toString())

                dataListtemp?.forEach{
                    if (it.FPA_status){
                        mainViewModel.dataListSetting.add(Setting_Param(it.parameter_name, "", it.unit ?: "", it.min, it.max))
                    }else{
                        mainViewModel.dataListActual.add(Actual_Param(it.parameter_name, "",  it.unit ?: "", it.min, it.max))
                    }
                }


                taskResponse.body()?.process_params_info?.forEach{
                    if (it.readings_is_available){
                        mainViewModel.dataListChart.add(chart_parameter(it.parameter_name, it.parameter_no))
                    }

                }

                //SHOULD BE SHIFTED TO OTHER API
                val p1 =  mainViewModel.dataListSetting.joinToString(separator = ",") { setting ->
                    "${setting.param_name} ::: ${setting.param_value}"
                }
                showLogs("TASK P1: ",p1)
                val p2 =  mainViewModel.dataListActual.joinToString(separator = ",") { actual ->
                    "${actual.param_name} ::: ${actual.param_value}"
                }
                showLogs("TASK P2: ",p2)


                showLogs("SHIFT TIME", "mainViewModel.startShiftTime")

                mainViewModel.startShiftTime = taskResponse.body()?.work_operator_data?.start_shift_time .toString()
                showLogs("START SHIFT TIME", mainViewModel.startShiftTime)

                mainViewModel.endShiftTime = taskResponse.body()?.work_operator_data?.end_shift_time.toString()
                showLogs("END SHIFT TIME", mainViewModel.endShiftTime)


                calculateShiftData()


                myComponents.navController.popBackStack()
                myComponents.navController.navigate(CHECKSHEET)

            }

            if (taskResponse.code() == 404) {
                mUiViewModel.setDialogDetails("Task Not Found", "Ask floor-in-charge to provide task", "", R.drawable.ic_notest)
                mUiViewModel.showMessageDialog()
            }
                } catch (e: Exception) {
            e.printStackTrace()
        }
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

        // Outputting the duration
        showLogs("Shift Duration", "$strHours:$strMinutes:$strSeconds")

    }

    suspend fun checkSheetStatus(
        employeeId: String,
        ficID: String,
        stationValue: String,
        fillChecksheet: String
    ) {
        try {
            checkSheetResponse =
                otherAPIs.checkSheetData(employeeId, ficID, fillChecksheet, stationValue)

            if (checkSheetResponse.code() == 200) {
                //move to last page page
                showLogs("CHECKSHEEEEEEt:","ADDED")
//                myComponents.navController.popBackStack()
//                myComponents.navController.navigate(CHECKSHEET)
            }else{
                showLogs("CHECKSHEEEEEEt:","NOT ADDED")

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

suspend fun notify(stationValue: String, csp_id: String, floor_no: String) {
        try {
            val notifyResponse= otherAPIs.operatorNotify(stationValue,csp_id,floor_no)
            if (notifyResponse.code() == 200) {
                showLogs("NOTIFICATION:","Notification sent")
            }else{
                showLogs("NOTIFICATION:","Notification not sent")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
}

    fun fillChecksheet(): String {
        var checkSheetStatus = ""

        try {
            for ((index, checksheet) in mainViewModel.mChecksheetData.value!!.withIndex()) {
                checkSheetStatus += "${checksheet.csp_id} ${mainViewModel.checkSheetList[index]},"
            }
            val new_checkSheetStatus = checkSheetStatus.dropLast(1)

            showLogs("CHECKSHEEEET", new_checkSheetStatus.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return checkSheetStatus
    }
}