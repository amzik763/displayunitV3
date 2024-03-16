package com.cti.displayuni.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.cti.displayuni.utility.KEY_TEXT_VALUE
import com.cti.displayuni.utility.PREFERNCES_NAME

class MainViewModel(context: Context) : ViewModel(){

    var mContext = context
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

}