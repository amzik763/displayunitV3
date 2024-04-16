package com.cti.displayuni.utility


enum class readingStatusEnum {
    notAvailable,
    available,
    completed
}

data class readingsStatusItems(

    val readingTime:Int,
    val readingParts:Int?,
    var readingStatusE: readingStatusEnum

)

