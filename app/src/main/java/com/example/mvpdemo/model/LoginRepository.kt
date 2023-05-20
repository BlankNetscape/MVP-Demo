package com.example.mvpdemo.model

/** NOTE: Model - data-source calls
 * ..it decides to look for data at local or remote.
 */

class LoginRepository(
    private val dataSource: LoginDataSource
) : LoginDataSource {
    override fun validateLogin(loginRequest: LoginRequest, callback: LoginDataSource.LoginCallback) {
        dataSource.validateLogin(
            loginRequest,
            object : LoginDataSource.LoginCallback {
                override fun onCredentialsValidated(response: LoginResponse) {
                    callback.onCredentialsValidated(response)
                }

                override fun onCredentialInvalid() {
                    callback.onCredentialInvalid()
                }
            }
        )
    }
}