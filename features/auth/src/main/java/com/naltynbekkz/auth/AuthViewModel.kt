package com.naltynbekkz.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.naltynbekkz.core.preferences.SessionManager

class AuthViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager,
    private val repository: AuthRepository,
) : ViewModel() {

    val tokenLiveData = sessionManager.tokenLiveData()

    fun save(token: String) {
        sessionManager.saveToken(token)
    }

    fun login(login: Login) = repository.login(login)

}