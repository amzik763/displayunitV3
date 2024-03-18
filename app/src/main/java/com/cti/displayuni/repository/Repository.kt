package com.cti.displayuni.repository

import android.util.Log
import com.cti.displayuni.R
import com.cti.displayuni.utility.myComponents.authAPI
import com.cti.displayuni.utility.myComponents.mUiViewModel
import com.cti.displayuni.utility.responses.loginResponse
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

            } else {
                showLogs("AUTH: ", "Login Unsuccessful")
            }
            if (loginResponse.code() == 401) {
                showLogs("AUTH: ", loginResponse.code().toString())

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

}