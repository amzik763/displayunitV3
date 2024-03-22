package com.cti.displayuni.repository

import android.util.Log
import com.amzi.displayunit.networks.RetrofitBuilder
import com.cti.displayuni.R
import com.cti.displayuni.utility.CHECKSHEET
import com.cti.displayuni.utility.GETTASK
import com.cti.displayuni.utility.LOGIN
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.myComponents.authAPI
import com.cti.displayuni.utility.myComponents.mUiViewModel
import com.cti.displayuni.utility.myComponents.mainViewModel
import com.cti.displayuni.utility.myComponents.otherAPIs
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
                mainViewModel.name = loginResponse.body()?.fName.toString() +" "+ loginResponse.body()?.lName.toString()
                mainViewModel.employeeId = loginResponse.body()?.employee_id.toString()
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


//send employee id
suspend fun getTask(station_id: String, shift: String){
           taskResponse =  otherAPIs.getTask(station_id, shift)

            if(taskResponse.code() == 200){
                //move to checksheet page
                myComponents.navController.popBackStack()
                myComponents.navController.navigate(CHECKSHEET)
            }

            if (taskResponse.code() == 401){
                myComponents.mUiViewModel.showTaskNotApprovedDialog()
            }

    }

}