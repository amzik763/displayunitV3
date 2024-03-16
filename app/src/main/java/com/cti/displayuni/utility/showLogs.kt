package com.cti.displayuni.utility

import android.util.Log
fun showLogs(TAG:String, text: String?){
        text?.let { Log.d(TAG, it) }
}