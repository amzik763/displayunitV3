package com.cti.displayuni.repository

import android.util.Log
import com.cti.displayuni.networks.RetrofitBuilder
import com.cti.displayuni.R
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
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
                mainViewModel.mChecksheetData.value = taskResponse.let { it.body()?.check_sheet_datas }
                mainViewModel.mChecksheetData.value?.forEach {
                    mainViewModel.checkSheetList.add("status")
                }
                taskResponse.body()?.work_operator_data?.toString()?.let { showLogs("WORK OPERATOR Size", it) }

                mainViewModel.ficID = taskResponse.body()?.work_operator_data?.flrInchr_employee_id.toString()
                mainViewModel.pass.intValue = taskResponse.body()?.work_operator_data?.passed ?: -1
                showLogs("PASS", mainViewModel.pass.intValue.toString())
//                showLogs("FLR ID",taskResponse.body()?.work_operator_data.emoloyee_id_floor_incharge.toString() )
                showLogs("FLR ID",taskResponse.body()?.work_operator_data?.flrInchr_employee_id.toString() )

                mainViewModel.fail.intValue = taskResponse.body()?.work_operator_data?.failed ?: -1
                showLogs("FAIL", mainViewModel.fail.intValue.toString())

                val dataListtemp = taskResponse.body()?.process_params_info
                showLogs("PARAMS", dataListtemp?.size.toString())

                dataListtemp?.forEach{
                    if (it.FPA_status){
                        mainViewModel.dataListSetting.add(Setting_Param(it.parameter_name, "", it.unit ?: "", it.min, it.max))
                    }else{
                        mainViewModel.dataListActual.add(Actual_Param(it.parameter_name, "",  it.unit ?: "", it.min, it.max))
                    }
                }

                val chartParameters = mutableListOf<chart_parameter>()
                taskResponse.body()?.process_params_info?.forEach{
                    if (it.readings_is_available){
                        val parameterName = it.parameter_name
                        val parameterNo = it.parameter_no
                        val values = MutableList(5) { " " } // Initialize values list with default values
                        val chartParam = chart_parameter(parameterName, parameterNo, values)
                        chartParameters.add(chartParam)
                        showLogs("DATA LIST ADDING", it.readings_is_available.toString() + " " + it.parameter_name)

                    }

                }
                mainViewModel.dataListChart.value = chartParameters
                showLogs("DATA LIST VALUES", mainViewModel.dataListChart.value.toString())


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

                if(taskResponse.body()?.check_sheet_fill_status == true)
                    myComponents.navController.navigate(FILL_PARAMETERS)
                else
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

    fun getCurrentTime(): String {
        val currentTime = Date()
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return dateFormat.format(currentTime)
    }

    private fun setReadingStatus(minutes: Int) {
//        val currentTime = getCurrentTime()
        var eachTime = minutes/5
        var eachPart:Int? = taskResponse.body()?.work_operator_data?.total_assigned_task?.div(5)
        showLogs("TIME SET: ",eachTime.toString() + " out of " + minutes)
//      val item:readingsStatusItems = readingsStatusItems(eachTime, eachPart, readingStatusEnum.notAvailable)
//      mainViewModel.readingStatusList.add(item)

        mainViewModel.readingStatusList.add(readingsStatusItems(eachTime, eachPart, readingStatusEnum.notAvailable))
        mainViewModel.readingStatusList.add(readingsStatusItems(eachTime*2, eachPart?.times(2), readingStatusEnum.notAvailable))
        mainViewModel.readingStatusList.add(readingsStatusItems(eachTime*3, eachPart?.times(3), readingStatusEnum.notAvailable))
        mainViewModel.readingStatusList.add(readingsStatusItems(eachTime*4, eachPart?.times(4), readingStatusEnum.notAvailable))
        mainViewModel.readingStatusList.add(readingsStatusItems(eachTime*5, eachPart?.times(5), readingStatusEnum.notAvailable))

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
        employeeId: String,
        ficID: String,
        stationValue: String,
        fillChecksheet: String
    ) {
        try {
            showLogs("CHECKSHEET DATAA:",employeeId)
            showLogs("CHECKSHEET DATAA:",ficID)
            showLogs("CHECKSHEET DATAA:",fillChecksheet)
            showLogs("CHECKSHEET DATAA:",stationValue)

            checkSheetResponse =
                otherAPIs.checkSheetData(employeeId, ficID, fillChecksheet, stationValue)

            if (checkSheetResponse.code() == 200) {
                //move to last page page
                showLogs("CHECKSHEEEEEEt:","ADDED")
                myComponents.navController.popBackStack()
//                myComponents.navController.navigate(CHECKSHEET)
                myComponents.navController.navigate(FILL_PARAMETERS)

            }else{
                showLogs("CHECKSHEEEEEEt:","NOT ADDED")

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun notify(stationValue: String, csp_id: String, floor_no: String) {
        try {
            showLogs("FLN",floor_no)
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

    suspend fun addData(failed: String, passed: String, station_id: String,i:Int):Boolean {
        var p=mainViewModel.pass.intValue;var f=mainViewModel.fail.intValue
        if(i==1)
            ++p
        else if(i==0)
            ++f
        try {
            val addDataResponse= otherAPIs.addData(p.toString(),f.toString(),station_id)
            if (addDataResponse.code() == 200) {
               if(i==1) mainViewModel.pass.intValue++
                else if(i==0) mainViewModel.fail.intValue++
                showLogs("ADDWITHPARAM", mainViewModel.pass.intValue.toString() + " " + mainViewModel.fail.intValue.toString())
                showLogs("ADDWITHPARAM", p.toString() + " " + f.toString())
                showLogs("ADD DATA:","Data Added Successfully")
                return true
            }else{
                showLogs("ADD DATA:","Data Not Added")
                return false
            }
        } catch (e: Exception) {
            e.printStackTrace()
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
        }
        return checkSheetStatus
    }

    suspend fun addDataWithParams(i:Int) {

        var p=mainViewModel.pass.intValue;var f=mainViewModel.fail.intValue
        if(i==1)
            ++p
        else if(i==0)
            ++f
        //SHOULD BE SHIFTED TO OTHER API
        val p1 =  mainViewModel.dataListSetting.joinToString(separator = ",") { setting ->
            "${setting.param_name} ::: ${setting.param_value}"
        }
        showLogs("TASK P1: ",p1)
        val p2 =  mainViewModel.dataListActual.joinToString(separator = ",") { actual ->
            "${actual.param_name} ::: ${actual.param_value}"
        }
        showLogs("TASK P2: ",p2)

        val p1p2 = "$p1, $p2".trim(',')

        showLogs("Combined String: ", p1p2)
        showLogs("Station ID: ", mainViewModel.getStationValue())
//        showLogs("PASS: ", mainViewModel.pass.intValue.toString())
//        showLogs("FAIL: ",  mainViewModel.fail.intValue.toString())
        lateinit var dataResponseWithParam:Response<FpaData_res>
        try{
            when(mainViewModel.FPACounter){
                1 -> {
                    dataResponseWithParam = otherAPIs.fpaData(f.toString(), p.toString(),p1p2 ,mainViewModel.getStationValue())
                }
                2 -> {
                    dataResponseWithParam = otherAPIs.fpaData2(f.toString(), p.toString(),p1p2 ,mainViewModel.getStationValue())
                }
                3 -> {
                    dataResponseWithParam = otherAPIs.fpaData3(f.toString(),p.toString(),p1p2 ,mainViewModel.getStationValue())
                }
                4 -> {
                    dataResponseWithParam = otherAPIs.fpaData4(f.toString(),p.toString(),p1p2 ,mainViewModel.getStationValue())
                }
            }

            if(dataResponseWithParam.isSuccessful){
                mainViewModel.FPACounter++
                if(i==1)
                    ++mainViewModel.pass.intValue
                else if(i==0)
                    ++mainViewModel.fail.intValue

                showLogs("ADDWITHPARAM", mainViewModel.pass.intValue.toString() + " " + mainViewModel.fail.intValue.toString())

                //show toast successful
                showLogs("ADDWITHPARAM","successfull")
                showLogs("ADDWITHPARAM","${mainViewModel.FPACounter}")
            }else{
                showLogs("ADDWITHPARAM","un-successfull")
                showLogs("ADDWITHPARAMFAIL",dataResponseWithParam.message())
                showLogs("ADDWITHPARAMFAIL",dataResponseWithParam.errorBody().toString())

                //show retry or failed dialog box
            }
        }
        catch (e:Exception){
            showLogs("ADDWITHPARAM Error",e.printStackTrace().toString())
        }


    }

    suspend fun runReadingAPI(readingIndex: Int, reading1: String, index: Int) {

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

                if (readingIndex == 0) {
                    mainViewModel.isCompleted1[index] = true
                } else if (readingIndex == 1) {
                    mainViewModel.isCompleted2[index] = true
                } else {
                    mainViewModel.isCompleted3[index] = true
                }

                if (mainViewModel.isCompleted1[index] && mainViewModel.isCompleted2[index] && mainViewModel.isCompleted3[index])
                    mainViewModel.readingStatusList[index].readingStatusE =
                        readingStatusEnum.completed

                showLogs("READING API", "value added")
                mainViewModel.isCompleted1.forEach {
                    showLogs("ISCOMPLETED 1: ", it.toString())
                }
                mainViewModel.isCompleted2.forEach {
                    showLogs("ISCOMPLETED 1: ", it.toString())
                }
                mainViewModel.isCompleted3.forEach {
                    showLogs("ISCOMPLETED 1: ", it.toString())
                }
                showLogs("COMPLETED VALUES", "")
                mainViewModel.mState.value = false

            } else {
                //showToastMessageToTryAgain
                showLogs("READING API", "got error")
                mainViewModel.mState.value = false

            }
        }catch (e:Exception){
            mainViewModel.mState.value = false
            //show toast or dialog that could not add
        }
    }



    fun addFailedData(i: Int) {

    }


}

