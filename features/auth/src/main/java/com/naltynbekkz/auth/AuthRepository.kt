package com.naltynbekkz.auth

import com.naltynbekkz.core.BaseRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class AuthRepository @Inject constructor(
    private val authService: AuthService,
    private val tokenDao: TokenDao
) : BaseRepository() {

    fun login(login: Login) = fullStackRequest(
        networkCall = { authService.login(login) },
        persist = { tokenDao.insert(it) }
    )

}