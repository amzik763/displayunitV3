package com.cti.displayuni.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.cti.displayuni.R
import com.cti.displayuni.utility.ChecksheetData
import com.cti.displayuni.utility.KEY_TEXT_VALUE
import com.cti.displayuni.utility.KEY_TOKEN
import com.cti.displayuni.utility.PREFERNCES_NAME
import com.cti.displayuni.utility.myComponents.mUiViewModel
import com.cti.displayuni.utility.myComponents.repository
import com.cti.displayuni.utility.showLogs
import kotlinx.coroutines.launch

class MainViewModel(context: Context) : ViewModel(){

    var mContext = context

    var name by mutableStateOf("")
    var deviceId by mutableStateOf("")
    var employeeId by mutableStateOf("")

    val checkSheetList = mutableListOf<String>()
    //VARIABLE FOR NEW CHECKSHEETDATA
    val mChecksheetData = MutableLiveData<List<ChecksheetData>>()

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

    fun getTask(station_id:String, shift : String){

        viewModelScope.launch {
            repository.getTask(station_id, shift)
        }
    }


}
