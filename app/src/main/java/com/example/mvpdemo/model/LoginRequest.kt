package com.example.mvpdemo.model

/** NOTE: Model
 * ..is used to send data to the login API.
 */

data class LoginRequest (
    val userName: String,
    val password: String
)