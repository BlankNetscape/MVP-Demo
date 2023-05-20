package com.example.mvpdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mvpdemo.databinding.ActivityLoginBinding
import com.example.mvpdemo.model.LoginLocalDataSource
import com.example.mvpdemo.model.LoginRepository
import com.example.mvpdemo.presenter.LoginPresenter

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var binding: ActivityLoginBinding
    override lateinit var presenter: LoginContract.Presenter
    override var isActive: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUp()
    }

    private fun setUp() {
        isActive = true;
        presenter = LoginPresenter(
            LoginRepository(LoginLocalDataSource()),
            this
        )
        binding.btnLogin?.setOnClickListener {
            doLogin();
        }
    }

    override fun showProgress(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    override fun showPostLoginPage(userName: String) {
        // TODO: Show next activity after login success.
        Toast.makeText(this, "Login Succeed!", Toast.LENGTH_SHORT).show()
    }

    override fun showLoginFailed() {
        // TODO: Notify user about login failure.
        Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        isActive = false
    }

    private fun doLogin() {
        Toast.makeText(this, "Login...", Toast.LENGTH_SHORT).show()
        presenter.performLogin(
            binding.edtUserName?.text.toString(),
            binding.edtPassword?.text.toString()
        )
    }
}