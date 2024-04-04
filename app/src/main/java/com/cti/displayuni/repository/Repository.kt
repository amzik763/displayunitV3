package com.cti.displayuni.repository

import android.util.Log
import com.cti.displayuni.networks.RetrofitBuilder
import com.cti.displayuni.R
import com.cti.displayuni.utility.CHECKSHEET
import com.cti.displayuni.utility.GETTASK
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
                mainViewModel.name =
                    loginResponse.body()?.fName.toString() + " " + loginResponse.body()?.lName.toString()
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
                showLogs("WORK OPERATOR Size", taskResponse.body()?.workOperatorData?.size.toString())

                taskResponse.body()?.workOperatorData?.forEach{ workOperatorData ->
                    showLogs("WORK OPERATOR", workOperatorData.toString())
                }
                mainViewModel.ficID = taskResponse.body()?.emoloyee_id_floor_incharge.toString()

                mainViewModel.pass = taskResponse.body()?.workOperatorData?.get(2)?.passed ?: -1


                myComponents.navController.popBackStack()
                myComponents.navController.navigate(CHECKSHEET)
            }

            if (taskResponse.code() == 401) {
                mUiViewModel.showTaskNotApprovedDialog()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
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