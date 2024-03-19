package com.cti.displayuni.response

data class login_response(
    val Response: String,
    val employee_id: String,
    val fName: String,
    val lName: String,
    val skill: String,
    val token: String
)