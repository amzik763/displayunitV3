package com.cti.displayuni.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cti.displayuni.R
import com.cti.displayuni.response.CheckSheetData
import com.cti.displayuni.utility.DialogModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.coroutines.cancellation.CancellationException

class UiViewModel(context: Context) : ViewModel(){


    var showCustomPopup = MutableLiveData<Boolean>()

    var showFpaDetails = MutableLiveData<Boolean>()

    var showFullImage = MutableLiveData<Boolean>()


    var isActualParamEnables = mutableStateOf(false)
    var isSettingParamEnables = mutableStateOf(true)


    var isRejectReasonDialogShown by mutableStateOf(false)
        private set

    var clearFields = mutableIntStateOf(0)

    var isNetworkDialogShown by mutableStateOf(false)
        private set

    var isMessageDialogShown by mutableStateOf(false)
        private set

    var isOverrideDialogShown by mutableStateOf(false)
        private set

    var dialogText by mutableStateOf("Network error...")
        private set

    var isFailedDialogShown by mutableStateOf(false)
        private set

    var isHindi = mutableStateOf(false)

    fun showNetworkDialog(){
        isNetworkDialogShown = true
    }
    fun hideNetworkDialog(){
        isNetworkDialogShown = false
    }

    fun showOverrideDialog(){
        isNetworkDialogShown = true
    }
    fun hideOverrideDialog(){
        isNetworkDialogShown = false
    }


    fun showRejectReasonDialog() {
        isRejectReasonDialogShown = true
    }

    fun hideRejectReasonDialog() {
        isRejectReasonDialogShown = false
    }

    fun showFailedDialog(){
        isFailedDialogShown = true
    }

    fun hideFailedDialog(){
        isFailedDialogShown = false
    }


    var dialogModel: DialogModel = DialogModel("Please Enter Password","Loading...","", R.drawable.thanks)

    fun setDialogDetails(headerText:String, subHeaderText:String, dialogText:String, imageResource:Int){
        dialogModel.dialogHeaderText = headerText
        dialogModel.dialogSubHeaderText = subHeaderText
        dialogModel.dialogText = dialogText
        dialogModel.imageResource = imageResource
    }
    fun showMessageDialog(){
        isMessageDialogShown = true

    }

    fun hideMessageDialog() {
        viewModelScope.launch {
            try {
                delay(2000)
                isMessageDialogShown = false
            } catch (e: Exception) {
                println("An error occurred: ${e.message}")
            }
        }
    }

    var isThanksDialogShown by mutableStateOf(false)
        private set

    var isTaskNotApprovedShown by mutableStateOf(false)
        private set
    fun showTaskNotApprovedDialog() {
        isTaskNotApprovedShown = true
    }

    fun hideTaskNotApprovedDialog() {
        isTaskNotApprovedShown = false
    }

    fun showThanksDialog() {
        isThanksDialogShown = true
    }

    fun hideThanksDialog() {
        isThanksDialogShown = false
    }


    var isLoginSupShown by mutableStateOf(false)
        private set
    fun showLoginSupDialog() {
        isLoginSupShown = true
    }

    fun hideLoginSupDialog() {
        isLoginSupShown = false
    }


    private var job: Job? = null

    private val _currentDateTime = MutableLiveData<String>()
    val currentDateTime: LiveData<String> get() = _currentDateTime
    private fun updateDateTime() {
        job?.cancel() // Cancel the previous job if any
        job = viewModelScope.launch {
            while (isActive) {
                val currentDateTime = getCurrentDateTime()
                _currentDateTime.postValue(currentDateTime)
                delay(1000) // Update every second
            }
        }
    }

    private fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy : HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }

    init {
        updateDateTime()
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}