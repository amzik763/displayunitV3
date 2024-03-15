package com.cti.displayuni.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UiViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UiViewModel::class.java)) {
                return UiViewModel(context) as T
            }
                       throw IllegalArgumentException("Unknown ViewModel class")


        }
    }
