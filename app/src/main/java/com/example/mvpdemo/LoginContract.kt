package com.example.mvpdemo

interface LoginContract {
    interface View: BaseView<Presenter> {
        fun showProgress(show: Boolean)
        fun showPostLoginPage(userName: String)
        fun showLoginFailed()
        val isActive : Boolean
    }
    interface Presenter: BasePresenter {
        fun performLogin(userName: String, password: String)
    }
}