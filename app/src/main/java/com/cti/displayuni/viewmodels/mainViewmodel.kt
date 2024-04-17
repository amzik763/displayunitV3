package com.cti.displayuni.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cti.displayuni.R
import com.cti.displayuni.response.*
import com.cti.displayuni.utility.Actual_Param
import com.cti.displayuni.utility.KEY_TEXT_VALUE
import com.cti.displayuni.utility.KEY_TOKEN
import com.cti.displayuni.utility.PREFERNCES_NAME
import com.cti.displayuni.utility.Setting_Param
import com.cti.displayuni.utility.chart_parameter
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.myComponents.mUiViewModel
import com.cti.displayuni.utility.myComponents.repository
import com.cti.displayuni.utility.readingStatusEnum
import com.cti.displayuni.utility.readingsStatusItems
import com.cti.displayuni.utility.showLogs
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel(context: Context) : ViewModel(){


    var mContext = context

    var isSupLoginSuccessful = false

    var name by mutableStateOf("")
    var deviceId by mutableStateOf("")
    var employeeId by mutableStateOf("")
    var password by mutableStateOf("")
    var email by mutableStateOf("")
    var dob by mutableStateOf("")
    var skill by mutableStateOf("")
    var mobileNum by mutableStateOf("")

    var startShiftTime by mutableStateOf("")
    var endShiftTime by mutableStateOf("")
    var timeDiffer by mutableStateOf("")

//  var readingStatusList = mutableListOf<readingsStatusItems>()
    val readingStatusList = mutableStateListOf<readingsStatusItems>()

    var dataListSetting = mutableListOf<Setting_Param>()
    var dataListActual = mutableListOf<Actual_Param>()

    var dataListChart = mutableListOf<chart_parameter>()

    var floorNum by mutableStateOf("")

    var errorMsg by mutableStateOf("")

    var pass = 0
    var fail = mutableIntStateOf(0)

    var tempParamID by mutableStateOf("")

    val checkSheetList = mutableListOf<String>()
    //VARIABLE FOR NEW CHECKSHEETDATA
    val mChecksheetData = MutableLiveData<List<CheckSheetData>>()
    var ficID = "none"

    private val sharedPreferences: SharedPreferences
        get() = mContext.getSharedPreferences(PREFERNCES_NAME, Context.MODE_PRIVATE)

    fun saveStationValue(textValue: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_TEXT_VALUE, textValue)
        editor.apply()
    }

    fun getStationValue(): String {
        return sharedPreferences.getString(KEY_TEXT_VALUE, "") ?: ""
    }

    fun saveToken(token: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String {
        return sharedPreferences.getString(KEY_TOKEN, "") ?: ""
    }

    fun loginUser(username: String, password: String){

        if (username.isBlank()){
            mUiViewModel.setDialogDetails("Please Enter Username", "Please ask administrator for your username", "", R.drawable.thanks)
            mUiViewModel.showMessageDialog()
            return
        }
        else if (password.isBlank()){
            mUiViewModel.setDialogDetails("Please Enter Password", "Please ask administrator for your password", "", R.drawable.thanks)
            mUiViewModel.showMessageDialog()
            return
        }
                viewModelScope.launch {
                repository.loginUser(username,password)
        }
    }


    fun getTask(station_id:String){

        viewModelScope.launch {
            repository.getTask(station_id)
        }
    }

    fun notify(stationValue: String, csp_id: String, floor_no: String){

        viewModelScope.launch {
            repository.notify(stationValue, csp_id, floor_no)
        }
    }
    fun addChecksheetData(){
        viewModelScope.launch {
            showLogs("CHECKSHEET API: ",employeeId)
            showLogs("CHECKSHEET API: ",ficID)
            showLogs("CHECKSHEET API: ",getStationValue())
            showLogs("CHECKSHEET API: ",employeeId)
            repository.checkSheetStatus(employeeId,ficID,getStationValue(),repository.fillChecksheet())
        }
    }

fun checkItemsInList() {
        val checkSheetList = myComponents.mainViewModel.checkSheetList
        for (item in checkSheetList) {
            if (item == "SUP_OK") {
                showLogs("DIALOG","SHOW DIALOG")
                mUiViewModel.showLoginSupDialog()
                return
            }
        }
        myComponents.mainViewModel.addChecksheetData()
         showLogs("DIALOG","HIDE DIALOG")

     }

fun itemsInRange():Boolean{
    dataListActual.forEach {
    try {
        if (!it.param_value.isNullOrBlank())
            if (it.param_unit?.length.toString() != "0")
                if (Integer.parseInt(it.param_value.toString()) > Integer.parseInt(it.max.toString()) || Integer.parseInt(
                        it.param_value.toString()
                    ) < Integer.parseInt(it.min.toString())
                ) {
                    myComponents.mUiViewModel.setDialogDetails(
                        "Not Eligible",
                        "",
                        "${it.param_name} value should be between ${it.min} and ${it.max}",
                        R.drawable.ic_notest
                    )
                    mUiViewModel.showMessageDialog()
                    return false
                }
    }catch (_:Exception){

    }
    }

    dataListSetting.forEach {
        try {
            if (it.param_unit?.length.toString() != "0")
                if (Integer.parseInt(it.param_value.toString()) > Integer.parseInt(it.max.toString()) || Integer.parseInt(
                        it.param_value.toString()
                    ) < Integer.parseInt(it.min.toString())
                ) {
                    myComponents.mUiViewModel.setDialogDetails(
                        "Not Eligible",
                        "",
                        "${it.param_name} value should be between ${it.min} and ${it.max}",
                        R.drawable.ic_notest
                    )
                    mUiViewModel.showMessageDialog()
                    return false
                }
        }catch (_:Exception){

        }
    }
    return true
}

    fun getCurrentTime(): String {
        val currentTime = Date()
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return dateFormat.format(currentTime)
    }

    fun submitPartInfo() {
        updateReadingStatus()
        checkReadingTimeAndShowPopup()
        //Other code
    }

    private fun checkReadingTimeAndShowPopup() {

        if(readingStatusList[0].readingStatusE.equals(readingStatusEnum.available)){
            showLogs("readingstatusenum"," available")
            showLogs("readingstatusenum2", readingStatusList[0].readingStatusE.name)
//            readingStatusList[0].readingStatusE = readingStatusEnum.notAvailable

        }else if(readingStatusList[0].readingStatusE.equals(readingStatusEnum.notAvailable)){
           showLogs("readingstatusenum"," not available")
            showLogs("readingstatusenum2", readingStatusList[0].readingStatusE.name)
//            readingStatusList[0].readingStatusE = readingStatusEnum.available


        }

    }

    private fun updateReadingStatus(){

        val timeDifferenceInMinutes = calculateTimeDifferenceInMinutes(startShiftTime, getCurrentTime())
        val timeDifferenceOfShift = calculateTimeDifferenceInMinutes(startShiftTime, endShiftTime)



        // Now you have the time difference in minutes, you can use it as needed
        println("Time difference in minutes: $timeDifferenceInMinutes")
        println("Time difference in minutes: $timeDifferenceOfShift")

        if(timeDifferenceInMinutes>=readingStatusList[0].readingTime && readingStatusList[0].readingStatusE != readingStatusEnum.completed){
            readingStatusList[0].readingStatusE = readingStatusEnum.available
        }else
        if(timeDifferenceInMinutes>=readingStatusList[1].readingTime && readingStatusList[1].readingStatusE != readingStatusEnum.completed){
            readingStatusList[1].readingStatusE = readingStatusEnum.available
        }else
        if(timeDifferenceInMinutes>=readingStatusList[2].readingTime && readingStatusList[2].readingStatusE != readingStatusEnum.completed){
            readingStatusList[2].readingStatusE = readingStatusEnum.available
        }else
        if(timeDifferenceInMinutes>=readingStatusList[3].readingTime && readingStatusList[3].readingStatusE != readingStatusEnum.completed){
            readingStatusList[3].readingStatusE = readingStatusEnum.available
        }else
        if(timeDifferenceInMinutes>=readingStatusList[4].readingTime && readingStatusList[4].readingStatusE != readingStatusEnum.completed){
            readingStatusList[4].readingStatusE = readingStatusEnum.available
        }


    }

    fun calculateTimeDifferenceInMinutes(time1: String, time2: String): Long {
        val format = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        try {
            val date1 = format.parse(time1)
            val date2 = format.parse(time2)

            val differenceInMillis = date2.time - date1.time
            return differenceInMillis / (1000 * 60)
        } catch (e: Exception) {
            e.printStackTrace()
            return -1 // Return a negative value to indicate error
        }
    }
}
