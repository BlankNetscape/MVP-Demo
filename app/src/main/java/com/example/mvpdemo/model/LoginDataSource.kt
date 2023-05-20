package com.example.mvpdemo.model

import android.os.Handler
import android.os.Looper

/** NOTE: Model?
 * ..Not a part of model cuz View has access to it.
 * ..abstraction for the local and remote data source.
 * ..helps to implement behavior for local and remote
 * and it also helps us to switch between local and
 * remote data sources.
 */
/**
 * LoginDataSource is a contract for local and remote data sources.
 * LoginLocalDataSource and LoginRemoteDataSource will implement
 * these methods and write implementation fo validateLogin(...)
 */

interface LoginDataSource {
    /**
     * Contract for presenter.
     * Presenter will get notified by these callbacks.
     */
    interface LoginCallback {
        fun onCredentialsValidated(response: LoginResponse)
        fun onCredentialInvalid()
    }
    fun validateLogin(loginRequest: LoginRequest, callback: LoginCallback)
}

class LoginLocalDataSource : LoginDataSource {
    override fun validateLogin(loginRequest: LoginRequest, callback: LoginDataSource.LoginCallback) {
        /**
         * A dummy wait to showcase how long running tasks
         * in data-sources perform the given task and
         * passes result back to repository.
         */
        /**
         * The Android doesn't allow other threads to communicate
         * directly with the UI thread, so one of the ways is to
         * use Handler. A Handler allows communication back with
         * UI thread from other background thread.
         *
         * There are methods like post(Runnable runnable) and
         * postDelayed(Runnable runnable, long delayMillis) can
         * be used.
         *
         * Where post(Runnable runnable) run the runnable on the
         * thread to which the Handler is attached.
         * postDelayed(Runnable runnable, long delayMillis) does
         * the same work as the post... but it causes runnable to
         * be run after the specified amount of time elapsed.
         */
        Handler(Looper.getMainLooper()).postDelayed({
            // Dummy validation to showcase the example only.
            val nameLength = loginRequest.userName.length
            val pwdLength = loginRequest.password.length
            if (nameLength != pwdLength) {
                callback.onCredentialInvalid()
            } else {
                val response = LoginResponse(
                    success = true,
                    userInfo = UserInfo(loginRequest.userName),
                    error = null
                )
                callback.onCredentialsValidated(response)
            }
        }, 3000)
    }

}