package com.cti.displayuni.response

data class login_response(
    val Response: String,
    val dob: String,
    val email: String,
    val employee_id: String,
    val fName: String,
    val lName: String,
    val mobile: String,
    val password: String,
    val skill: String,
    val token: String
)