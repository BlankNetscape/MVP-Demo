package com.example.mvpdemo.model

/** NOTE: Model
 * ..is used to send data to the login API.
 */

data class LoginResponse(
    val success: Boolean,
    val userInfo: UserInfo?,
    val error: String?
)

data class UserInfo (
    val name: String
)