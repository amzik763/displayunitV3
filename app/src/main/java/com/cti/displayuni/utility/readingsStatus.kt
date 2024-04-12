package com.cti.displayuni.utility


enum class readingStatus {
    notAvailable,
    available,
    completed
}
data class readingsStatus(

    val readingTime:Int,
    val readingParts:Int,
    val readingStatus: readingStatus

)

