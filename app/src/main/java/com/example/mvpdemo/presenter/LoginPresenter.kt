package com.example.mvpdemo.presenter

import com.example.mvpdemo.*
import com.example.mvpdemo.model.LoginDataSource
import com.example.mvpdemo.model.LoginRepository
import com.example.mvpdemo.model.LoginRequest
import com.example.mvpdemo.model.LoginResponse

class LoginPresenter(
    private val repository: LoginRepository,
    val loginView: LoginContract.View
) : LoginContract.Presenter {
    init {
        loginView.presenter = this
    }

    override fun performLogin(userName: String, password: String) {
        loginView.showProgress(true);
        /** Prepare data format required by model layer.
         * Like here, we are creating LoginRequest object
         * with required data.
         */
        val loginRequest = LoginRequest(userName, password)
        /** Asking model using repository to validate credentials.
         * and expecting result by callback.
         */
        repository.validateLogin(loginRequest, callback)
    }

    private val callback = object :
        LoginDataSource.LoginCallback {
        override fun onCredentialsValidated(loginResponse: LoginResponse) {
            // Login succeed. Notify about it.
            // Check the is able to handle UI updates or not.
            if (!loginView.isActive) {
                return
            }
            // Ask View to update itself with given data for success
            loginView.showProgress(false);
            loginResponse.userInfo?.let { user -> loginView.showPostLoginPage(user.name) }
        }

        override fun onCredentialInvalid() {
            // Check the is able to handle UI updates or not.
            if (!loginView.isActive) {
                return
            }
            // Ask View to update itself with given data for failure.
            loginView.showProgress(false)
            loginView.showLoginFailed()
        }
    }

    override fun start() {
        TODO("Not yet implemented")
    }
}