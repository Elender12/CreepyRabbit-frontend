package com.ecirstea.creepyrabbit.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.ecirstea.creepyrabbit.data.LoginRepository
import com.ecirstea.creepyrabbit.R
import com.ecirstea.creepyrabbit.data.model.UserCredentials
import com.ecirstea.creepyrabbit.data.model.JwtResponse

private const val TAG = "LoginViewModel"
class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm
    var userCredentialsListLiveData : LiveData<List<UserCredentials>>?=null
    var authenticateUserLiveData:LiveData<JwtResponse?>?=null

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        //val result = loginRepository.login(username, password)
        val userCredentials = UserCredentials(username, password)
        val result = loginRepository.authenticateUser(userCredentials)
        Log.d(TAG, "login: $result")

        if (result is com.ecirstea.api.model.JwtResponse) {
            _loginResult.value = LoginResult(success = LoggedInUserView(displayName = "elena"))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 1
    }
}