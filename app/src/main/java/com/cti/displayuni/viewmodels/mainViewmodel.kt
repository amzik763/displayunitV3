package com.cti.displayuni.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cti.displayuni.R
import com.cti.displayuni.response.*
import com.cti.displayuni.screens.SocketManager
import com.cti.displayuni.utility.Actual_Param
import com.cti.displayuni.utility.KEY_TEXT_VALUE
import com.cti.displayuni.utility.KEY_TOKEN
import com.cti.displayuni.utility.PREFERNCES_NAME
import com.cti.displayuni.utility.Setting_Param
import com.cti.displayuni.utility.chart_parameter
import com.cti.displayuni.utility.myComponents.mUiViewModel
import com.cti.displayuni.utility.myComponents.mainViewModel
import com.cti.displayuni.utility.myComponents.repository
import com.cti.displayuni.utility.readingStatusEnum
import com.cti.displayuni.utility.readingsStatusItems
import com.cti.displayuni.utility.showLogs
import io.socket.emitter.Emitter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.json.JSONObject
import java.util.Calendar

class MainViewModel(context: Context) : ViewModel() {

    private lateinit var onUpdateCspNotificationStatus: Emitter.Listener

    private val _dataStatus = mutableStateOf<StationCspDataStatus?>(null)
    val dataStatus: State<StationCspDataStatus?> = _dataStatus

    private val sharedPreferences: SharedPreferences
        get() = mContext.getSharedPreferences(PREFERNCES_NAME, Context.MODE_PRIVATE)

    fun initializeSocket(){
        SocketManager.initSocket()
        SocketManager.connect()
        // Listen for socket events
        //  SocketManager.on("update_work_for_operator", onUpdateWorkForOperator)
        //  SocketManager.on("filter_floor_csp_notification", onFilterFloorCspNotification)
        showLogs("WEBSOCKET:"," initialized")

        onUpdateCspNotificationStatus = Emitter.Listener { args ->
            viewModelScope.launch(Dispatchers.Main) {
                try {
                    val data = args[0] as JSONObject
                    showLogs("WEBSOCKET:", "listening")
                    showLogs("WEBSOCKET:", "${data}")
                    val dataStatus = parseStationCspDataStatus(data)
                    _dataStatus.value = dataStatus
                    showLogs("SOCKET KEY",getValueForKey(dataStatus,"216").toString())
//                    val dataStatus = parseStationCspDataStatus(data)
                } catch (e: Exception) {
                    showLogs("WEBSOCKET:", "Error parsing JSON: ${e.message}")
                }
            }
        }

        val stationData = getStationValue()
        checkStationCspStatus(stationData)
        SocketManager.on("update_csp_notification_status", onUpdateCspNotificationStatus)
//        checkStationCspStatus()
    }

    fun checkStationCspStatus(data: String) {
//        SocketManager.emit("check_station_csp_status", data)
        SocketManager.emit("check_station_csp_status", data)
        showLogs("WEBSOCKET:"," check emit")
    }

    override fun onCleared() {
        super.onCleared()
        SocketManager.disconnect()
    }

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
    var profilePic by mutableStateOf("")

    var startShiftTime by mutableStateOf("")
    var endShiftTime by mutableStateOf("")
    var timeDiffer by mutableStateOf("")
    var timeDifferMid by mutableStateOf("")

    var mProcessName by mutableStateOf("")
    var mPartName by mutableStateOf("")

    val isReplacementChecked = mutableStateOf(false)

    //  var readingStatusList = mutableListOf<readingsStatusItems>()
    val readingStatusList = mutableStateListOf<readingsStatusItems>()
    val isCompleted1 = mutableStateListOf<Boolean>(false, false, false, false, false)
    val isCompleted2 = mutableStateListOf<Boolean>(false, false, false, false, false)
    val isCompleted3 = mutableStateListOf<Boolean>(false, false, false, false, false)
    val readingSize = mutableStateOf(0)

    var dataListSetting = mutableListOf<Setting_Param>()
    var dataListActual = mutableListOf<Actual_Param>()

    var imageUrl = mutableListOf<String>()
    val dataListChart = MutableLiveData<MutableList<chart_parameter>>()

    //  var dataListChart = mutableListOf<chart_parameter>()
    val mState = MutableLiveData<Boolean>()

    var floorNum by mutableStateOf("")
    var lineNum by mutableStateOf("")
    var errorMsg by mutableStateOf("")

    var pass = mutableIntStateOf(0)
    var fail = mutableIntStateOf(0)
    var totalAssigned = mutableIntStateOf(0)

    var temp_task_id = mutableStateOf("")
    var precedency_no = mutableStateOf("")

    var showZoomableImage by mutableStateOf(true)
    var tempParamID by mutableStateOf("")

    val checkSheetList = mutableListOf<String>()

    //VARIABLE FOR NEW CHECKSHEETDATA
    val mChecksheetData = MutableLiveData<List<CheckSheetData>>()

    //    lateinit var mChecksheetNotification: MutableLiveData<MutableMap<String, String>>
    val myChecksheetNotificationMap = mutableStateMapOf<String, String>()
    val apiCheckSheetStatusBack = mutableStateOf(checkSheetStatusBack("none"))
    var ficID = "none"

    var chartData = mutableStateOf("""
        {
            "result": {
                "2024-08-06": {
                    "G01 F02 L01 S01": {
                        "A": ["0", "0", "0", "0", "0"]
                    }
                },
                "2024-08-08": {
                    "G01 F02 L01 S01": {
                        "A": ["0", "0", "0", "0", "0"]
                    }
                },
                "2024-08-10": {
                    "G01 F02 L01 S01": {
                        "A": ["0", "0", "0", "0", "0"]
                    }
                },
                "2024-08-16": {
                    "G01 F02 L01 S01": {
                        "A": ["0", "0", "0", "0", "0"]
                    }
                },
                "2024-08-21": {
                    "G01 F02 L01 S01": {
                        "A": ["0", "0", "0", "0", "0"]
                    }
                }
            }
        }
    """.trimIndent())

    var FPACounter = 5;
    var isFPATime = false
    var shouldCheckTemporaryFPA = false
    var dontAddData = true
    var overrideDontAddData = false

    var shift = mutableStateOf("")

    var mSelectedReason = ""
    val mReasonList = MutableLiveData<myReasons>()
    var isReasonRetrieved = false
    var mark = ""

    var partID = ""
    var itemId = ""

    /*var fpa1:String? = null
    var fpa2:String? = null
    var fpa3:String? = null
    var fpa4:String? = null*/

    var fpa1 = mutableStateOf<String?>(null)
    var fpa2 = mutableStateOf<String?>(null)
    var fpa3 = mutableStateOf<String?>(null)
    var fpa4 = mutableStateOf<String?>(null)

    var otherfpa1 = mutableStateOf<String?>(null)
    var otherfpa2 = mutableStateOf<String?>(null)
    var otherfpa3 = mutableStateOf<String?>(null)
    var otherfpa4 = mutableStateOf<String?>(null)


    fun saveStationValue(textValue: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_TEXT_VALUE, textValue)
        editor.apply()
    }

    fun getStationValue(): String {
        return sharedPreferences.getString(KEY_TEXT_VALUE, "") ?: ""
    }

    fun extractPattern(input: String): String {
        try {
            // Define the regular expression pattern
            val pattern = """(\w+\d+ F\d+ L\d+)""".toRegex()

            // Find the match in the input string
            val matchResult = pattern.find(input)

            // Return the matched group or an empty string if no match found
            return matchResult?.value ?: ""
        } catch (e: Exception) {
            return ""
        }
    }

    fun saveToken(token: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String {
        return sharedPreferences.getString(KEY_TOKEN, "") ?: ""
    }

    fun loginUser(username: String, password: String) {
        if (username.isBlank()) {
            mUiViewModel.setDialogDetails(
                "Please Enter Username",
                "Please ask administrator for your username",
                "",
                R.drawable.thanks
            )
            mUiViewModel.showMessageDialog()
            return
        } else if (password.isBlank()) {
            mUiViewModel.setDialogDetails(
                "Please Enter Password",
                "Please ask administrator for your password",
                "",
                R.drawable.thanks
            )
            mUiViewModel.showMessageDialog()
            return
        }

        viewModelScope.launch {
            mainViewModel.lineNum = extractPattern(getStationValue())
            repository.loginUser(username, password)
        }
    }

    fun getCurrentTimeInHHMMSS(): String {
        val currentTime = Date()
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return dateFormat.format(currentTime)
    }

    fun isCurrentTimeExceedsMidTime(time1: String, time2: String): Boolean {
        val currentTime = getCurrentTime()
        // Convert time1, time2, and currentTime to milliseconds for comparison
        val time1Millis = timeStringToMillis(time1)
        val time2Millis = timeStringToMillis(time2)
        val currentTimeMillis = timeStringToMillis(currentTime)
        // Calculate mid-time in milliseconds
        val midTimeMillis = (time1Millis + time2Millis) / 2
        // Check if currentTime is after mid-time
        return currentTimeMillis > midTimeMillis
    }

    fun isShiftOver(time1: String): Boolean {
        val currentTime = getCurrentTime()
        // Convert time1, time2, and currentTime to milliseconds for comparison
        val time1Millis = timeStringToMillis(time1)
        val currentTimeMillis = timeStringToMillis(currentTime)
        // Calculate mid-time in milliseconds
        // Check if currentTime is after mid-time
        return currentTimeMillis > time1Millis
    }

    fun timeStringToMillis(timeString: String): Long {
        val parts = timeString.split(":").map { it.toInt() }
        val currentTime = Date()
        val calendar = Calendar.getInstance().apply {
            time = currentTime
            set(Calendar.HOUR_OF_DAY, parts[0])
            set(Calendar.MINUTE, parts[1])
            set(Calendar.SECOND, parts[2])
        }
        return calendar.timeInMillis
    }

    fun getTask(station_id: String) {
        viewModelScope.launch {
            repository.getTask(station_id, employeeId)
        }
    }

    fun notify(
        stationValue: String,
        csp_id: String,
        floor_no: String,
        onResult: (Result<String>) -> Unit
    ) {
        viewModelScope.launch {
            val result = repository.notify(stationValue, csp_id, floor_no)
            onResult(result)
        }
    }

    fun addChecksheetData() {
        viewModelScope.launch {
            showLogs("CHECKSHEET API: ", employeeId)
            showLogs("CHECKSHEET API: ", ficID)
            showLogs("CHECKSHEET API: ", getStationValue())
            showLogs("CHECKSHEET API: ", employeeId)
            repository.checkSheetStatus(
                employeeId,
                ficID,
                getStationValue(),
                repository.fillChecksheet(),
                shift.value
            )
        }
    }

    fun getCheckSheetStatusBack(s: String?, onResult: (Result<String>) -> Unit) {
        viewModelScope.launch {
            val result = repository.getCheckSheetStatusBack(s)
            onResult(result)

        }
    }

    fun checkItemsInList() {
        val checkSheetList = mainViewModel.checkSheetList
        for (item in checkSheetList) {

//uncomment in production
         /*   if (item == "status") {
                mUiViewModel.setDialogDetails(
                    "EMPTY STATUS",
                    "Please fill all the value..",
                    "",
                    R.drawable.ic_notest
                )
                mUiViewModel.showMessageDialog()
                return
            }*/

//            if (item == "SUP_OK") {
//                showLogs("DIALOG","SHOW DIALOG")
//                mUiViewModel.showLoginSupDialog()
//                return
//            }

            if (item == "NG") {
                showLogs("DIALOG", "SHOW DIALOG")
                mUiViewModel.setDialogDetails(
                    "Not Eligible",
                    "Please coordinate with floor-in-charge..",
                    "",
                    R.drawable.ic_notest
                )
                mUiViewModel.showMessageDialog()
                return
            }
        }
        mainViewModel.addChecksheetData()
        showLogs("DIALOG", "HIDE DIALOG")
    }


    fun readingInRange(min: String, max: String, param_value: String): Boolean {
        try {
            if (Integer.parseInt(param_value) > Integer.parseInt(max) || Integer.parseInt(
                    param_value
                ) < Integer.parseInt(min)
            ) {
                mUiViewModel.setDialogDetails(
                    "Not Eligible",
                    "",
                    "Value should be between ${min} and ${max}",
                    R.drawable.ic_notest
                )
                mUiViewModel.showMessageDialog()

                showLogs("Reading", "Reading in range")
                return false

            }
        } catch (e: NumberFormatException) {

            try {
                val paramValue = param_value.toFloat()
                val maxValue = max.toFloat()
                val minValue = min.toFloat()

                if (paramValue > maxValue || paramValue < minValue
                ) {
                    mUiViewModel.setDialogDetails(
                        "Not Eligible",
                        "",
                        "Value should be between ${min} and ${max}",
                        R.drawable.ic_notest
                    )
                    mUiViewModel.showMessageDialog()
                    showLogs("Reading", "Reading in range")
                    return false
                }
            } catch (e: Exception) {
                showLogs("Reading exception", "Reading is not in range")
            }
        } catch (e: Exception) {
            println(e.printStackTrace())
            showLogs("Reading exc", "Reading is not in range")
            return false
        }
        showLogs("Reading not", "Reading is not in range")
        return true
    }

    fun itemsInRange(): Boolean {
        showLogs("ITEMS RANGE: ","IN DATA RANGE")

        dataListActual.forEach {
            try {
                if (!it.param_value.isNullOrBlank())
                    if (it.param_unit?.length.toString() != "0") {
                        if (Integer.parseInt(it.param_value.toString()) > Integer.parseInt(it.max.toString()) || Integer.parseInt(
                                it.param_value.toString()
                            ) < Integer.parseInt(it.min.toString())
                        ) {
                            mUiViewModel.setDialogDetails(
                                "Not Eligible",
                                "",
                                "${it.param_name} value should be between ${it.min} and ${it.max}",
                                R.drawable.ic_notest
                            )
//                            mUiViewModel.showMessageDialog()
                            mUiViewModel.showFpaEligibleDialog()
                            return false
                        }
                    }else{
                        if(it.param_value.toString().uppercase() !=it.min.toString().uppercase() && it.param_value.toString().uppercase()!=it.max.toString().uppercase()){

                            showLogs("VAL VAL VAL:", it.param_value.toString().uppercase())
                            showLogs("VAL VAL VAL:", it.min.toString().uppercase())
                            showLogs("VAL VAL VAL:", it.max.toString().uppercase())
                            mUiViewModel.setDialogDetails(
                                "Not Eligible",
                                "",
                                "${it.param_name} value should be either ${it.min} or ${it.max}",
                                R.drawable.ic_notest)
//                            mUiViewModel.showMessageDialog()
                            mUiViewModel.showFpaEligibleDialog()

                            return false
                        }
                    }
            } catch (e: NumberFormatException) {
                showLogs("INTEGER MINOR: ",e.toString())

                try {
                    val paramValue = it.param_value?.toFloat()
                    val maxValue = it.max?.toFloat()
                    val minValue = it.min?.toFloat()

                    if (paramValue != null && maxValue != null && minValue != null)
                        if ((paramValue > maxValue) || (paramValue < minValue)) {
                            mUiViewModel.setDialogDetails(
                                "Not Eligible",
                                "",
                                "Value should be between ${minValue} and ${maxValue}",
                                R.drawable.ic_notest
                            )
//                            mUiViewModel.showMessageDialog()
                            mUiViewModel.showFpaEligibleDialog()

                            showLogs("Reading", "Reading in range")
                            return false
                        }
                } catch (e: NumberFormatException) {
                    showLogs("FLOAT MINOR: ",e.toString())
                    return false
                }catch (_: Exception) {
                    showLogs("FLOAT MAJOR: ",e.toString())
                    return false
                }
            } catch (e: Exception) {
                showLogs("INTEGER MAJOR: ",e.toString())
                return false
            }
        }


        dataListSetting.forEach {
            try {
                if (!it.param_value.isNullOrBlank())
                    if (it.param_unit?.length.toString() != "0") {
                        if (Integer.parseInt(it.param_value.toString()) > Integer.parseInt(it.max.toString()) || Integer.parseInt(
                                it.param_value.toString()
                            ) < Integer.parseInt(it.min.toString())
                        ) {
                            mUiViewModel.setDialogDetails(
                                "Not Eligible",
                                "",
                                "${it.param_name} value should be between ${it.min} and ${it.max}",
                                R.drawable.ic_notest
                            )
//                            mUiViewModel.showMessageDialog()
                            mUiViewModel.showFpaEligibleDialog()

                            return false
                        }
                    }else{
                        if(it.param_value.toString().uppercase() !=it.min.toString().uppercase() && it.param_value.toString().uppercase()!=it.max.toString().uppercase()){

                            showLogs("VAL VAL VAL:", it.param_value.toString().uppercase())
                            showLogs("VAL VAL VAL:", it.min.toString().uppercase())
                            showLogs("VAL VAL VAL:", it.max.toString().uppercase())
                            mUiViewModel.setDialogDetails(
                                "Not Eligible",
                                "",
                                "${it.param_name} value should be either ${it.min} or ${it.max}",
                                R.drawable.ic_notest)
//                            mUiViewModel.showMessageDialog()
                            mUiViewModel.showFpaEligibleDialog()

                            return false
                        }
                    }
            } catch (e: NumberFormatException) {
                showLogs("INTEGER MINOR: ",e.toString())

                try {
                    val paramValue = it.param_value?.toFloat()
                    val maxValue = it.max?.toFloat()
                    val minValue = it.min?.toFloat()

                    if (paramValue != null && maxValue != null && minValue != null)
                        if ((paramValue > maxValue) || (paramValue < minValue)) {
                            mUiViewModel.setDialogDetails(
                                "Not Eligible",
                                "",
                                "Value should be between ${minValue} and ${maxValue}",
                                R.drawable.ic_notest
                            )
//                            mUiViewModel.showMessageDialog()
                            mUiViewModel.showFpaEligibleDialog()

                            showLogs("Reading", "Reading in range")
                            return false
                        }
                } catch (e: NumberFormatException) {
                    showLogs("FLOAT MINOR: ",e.toString())
                    return false
                }catch (_: Exception) {
                    showLogs("FLOAT MAJOR: ",e.toString())
                    return false
                }
            } catch (e: Exception) {
                showLogs("INTEGER MAJOR: ",e.toString())
                return false
            }
        }

        /*dataListSetting.forEach {
            try {
                if (it.param_unit?.length.toString() != "0")
                    if (Integer.parseInt(it.param_value.toString()) > Integer.parseInt(it.max.toString()) || Integer.parseInt(
                            it.param_value.toString()
                        ) < Integer.parseInt(it.min.toString())
                    ) {
                        mUiViewModel.setDialogDetails(
                            "Not Eligible",
                            "",
                            "${it.param_name} value should be between ${it.min} and ${it.max}",
                            R.drawable.ic_notest
                        )
                        mUiViewModel.showMessageDialog()
                        return false
                    }

            } catch (e: NumberFormatException) {
                try {
                    val paramValue = it.param_value?.toFloat()
                    val maxValue = it.max?.toFloat()
                    val minValue = it.min?.toFloat()

                    if (paramValue != null && maxValue != null && minValue != null)
                        if ((paramValue > maxValue) || (paramValue < minValue)) {
                            mUiViewModel.setDialogDetails(
                                "Not Eligible",
                                "",
                                "Value should be between ${minValue} and ${maxValue}",
                                R.drawable.ic_notest
                            )
                            mUiViewModel.showMessageDialog()

                            showLogs("Reading", "Reading in range")
                            return false


                        }
                } catch (e: Exception) {

                }

            } catch (_: Exception) {
            }
        }*/




        return true
    }

    fun getCurrentTime(): String {
        val currentTime = Date()
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return dateFormat.format(currentTime)
    }

    suspend fun submitPartInfo(i: Int) {

        //If FPA DONE FOR LAST STATION


        val addData = mainViewModel.addData(
            mainViewModel.fail.intValue.toString(),
            mainViewModel.pass.intValue.toString(),
            station_id = mainViewModel.getStationValue(),
            i = i
        )


        if (addData) {
            //set pass fail and checking part values
            if (dataListChart.value?.size != 0) {
                updateReadingStatus()
                checkReadingTimeAndShowPopup()
            }
            showLogs("API RESP", " API SUCCESSFUll")
        } else {
            showLogs("API RESP", " API UN-SUCCESSFUll")
        }
    }

    private fun checkReadingTimeAndShowPopup() {
        viewModelScope.launch {
            if (readingStatusList[0].readingStatusE.equals(readingStatusEnum.available)) {
                showLogs("readingstatusenum2", readingStatusList[0].readingStatusE.name)
//            readingStatusList[0].readingStatusE = readingStatusEnum.notAvailable
                mUiViewModel.showCustomPopup.value = true


            } else if (readingStatusList[1].readingStatusE.equals(readingStatusEnum.available)) {
                showLogs("readingstatusenum2", readingStatusList[1].readingStatusE.name)
//            readingStatusList[0].readingStatusE = readingStatusEnum.available
                mUiViewModel.showCustomPopup.value = true
            } else if (readingStatusList[2].readingStatusE.equals(readingStatusEnum.available)) {
                showLogs("readingstatusenum2", readingStatusList[2].readingStatusE.name)
//            readingStatusList[0].readingStatusE = readingStatusEnum.available
                mUiViewModel.showCustomPopup.value = true
            } else if (readingStatusList[3].readingStatusE.equals(readingStatusEnum.available)) {
                showLogs("readingstatusenum2", readingStatusList[3].readingStatusE.name)
//            readingStatusList[0].readingStatusE = readingStatusEnum.available
                mUiViewModel.showCustomPopup.value = true
            } else if (readingStatusList[4].readingStatusE.equals(readingStatusEnum.available)) {
                showLogs("readingstatusenum2", readingStatusList[4].readingStatusE.name)
//            readingStatusList[0].readingStatusE = readingStatusEnum.available
                mUiViewModel.showCustomPopup.value = true
            }
        }
    }


    private fun updateReadingStatus() {
        val timeDifferenceInMinutes =
            calculateTimeDifferenceInMinutes(startShiftTime, getCurrentTime())
        val timeDifferenceOfShift = calculateTimeDifferenceInMinutes(startShiftTime, endShiftTime)

        val currentPart = pass.intValue + fail.intValue

        // Now you have the time difference in minutes, you can use it as needed
        println("Time difference in minutes: $timeDifferenceInMinutes")
        println("Time difference in minutes: $timeDifferenceOfShift")
        timeDifferMid = timeDifferenceOfShift.toString()
        if ((timeDifferenceInMinutes >= readingStatusList[0].readingTime && readingStatusList[0].readingStatusE != readingStatusEnum.completed)
            || (currentPart >= readingStatusList[0].readingParts!! && readingStatusList[0].readingStatusE != readingStatusEnum.completed)
        ) {
            readingStatusList[0].readingStatusE = readingStatusEnum.available
            showLogs("READING STATUS 1", readingStatusList[0].readingStatusE.name)
        } else
            if ((timeDifferenceInMinutes >= readingStatusList[1].readingTime && readingStatusList[1].readingStatusE != readingStatusEnum.completed)
                || (currentPart >= readingStatusList[1].readingParts!! && readingStatusList[1].readingStatusE != readingStatusEnum.completed)
            ) {
                readingStatusList[1].readingStatusE = readingStatusEnum.available
                showLogs("READING STATUS 2", readingStatusList[1].readingStatusE.name)

            } else
                if ((timeDifferenceInMinutes >= readingStatusList[2].readingTime && readingStatusList[2].readingStatusE != readingStatusEnum.completed)
                    || (currentPart >= readingStatusList[2].readingParts!! && readingStatusList[2].readingStatusE != readingStatusEnum.completed)
                ) {
                    readingStatusList[2].readingStatusE = readingStatusEnum.available
                    showLogs("READING STATUS 3", readingStatusList[2].readingStatusE.name)

                } else
                    if ((timeDifferenceInMinutes >= readingStatusList[3].readingTime && readingStatusList[3].readingStatusE != readingStatusEnum.completed)
                        || (currentPart >= readingStatusList[3].readingParts!! && readingStatusList[3].readingStatusE != readingStatusEnum.completed)
                    ) {
                        readingStatusList[3].readingStatusE = readingStatusEnum.available
                        showLogs("READING STATUS 4", readingStatusList[3].readingStatusE.name)
                    } else
                        if ((timeDifferenceInMinutes >= readingStatusList[4].readingTime && readingStatusList[4].readingStatusE != readingStatusEnum.completed)
                            || (currentPart >= readingStatusList[4].readingParts!! && readingStatusList[4].readingStatusE != readingStatusEnum.completed)
                        ) {
                            readingStatusList[4].readingStatusE = readingStatusEnum.available
                            showLogs("READING STATUS 5", readingStatusList[4].readingStatusE.name)
                        }

        showLogs(
            "READING STATUS ALL",
            readingStatusList[0].readingStatusE.name + " " + readingStatusList[0].readingTime
        )
        showLogs(
            "READING STATUS ALL",
            readingStatusList[1].readingStatusE.name + " " + readingStatusList[1].readingTime
        )
        showLogs(
            "READING STATUS ALL",
            readingStatusList[2].readingStatusE.name + " " + readingStatusList[2].readingTime
        )
        showLogs(
            "READING STATUS ALL",
            readingStatusList[3].readingStatusE.name + " " + readingStatusList[3].readingTime
        )
        showLogs(
            "READING STATUS ALL",
            readingStatusList[4].readingStatusE.name + " " + readingStatusList[4].readingTime
        )

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
            return -1
        }
    }

    suspend fun addData(failed: String, passed: String, station_id: String, i: Int): Boolean {
        return coroutineScope {
            val result = async {
                repository.addData(failed, passed, station_id, i)
            }
            result.await()
        }
    }

    fun areActualParamsFilled(dataListActual: MutableList<Actual_Param>): Boolean {
        for (item in dataListActual) {
            if (item.param_value?.isBlank() == true) {
                return false
            }
        }
        return true
    }

    fun areSettingParamsFilled(dataListSetting: MutableList<Setting_Param>): Boolean {
        for (item in dataListSetting) {
            if (item.param_value?.isBlank() == true) {
                return false // Return false if any param_value is blank
            }
        }
        return true // Return true if all param_values are filled
    }

    suspend fun submitPartInfoWithParams(i: Int) {
        repository.addDataWithParams(i)
    }

    fun runReadingAPI(readingIndex: Int, reading1: String, index: Int) {
        viewModelScope.launch {
            repository.runReadingAPI(readingIndex, reading1, index)
        }
    }

    fun submitFailedPartInfo() {
        viewModelScope.launch {
            repository.addFailedData()
        }
    }

    fun getReasonData() {
        viewModelScope.launch {
            repository.getReasonData()
        }
    }

    fun checkFPA(precedency_no: String, part_no: String, temp_task_id: String) {
        viewModelScope.launch {
            repository.checkFPA(precedency_no, part_no, temp_task_id)
        }
    }

    fun FailedFPA() {
        viewModelScope.launch {
            repository.FailedFpa()
        }
    }

    fun readingChart(parameter_no: String) {
        viewModelScope.launch {
            repository.readingChart(parameter_no)
        }
    }

}