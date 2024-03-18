package com.cti.displayuni.utility

import androidx.annotation.DrawableRes

data class DialogModel (
    var dialogHeaderText: String,
    var dialogSubHeaderText: String,
    var dialogText: String,
    @DrawableRes var imageResource:Int
)

