package com.naltynbekkz.tanda.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.naltynbekkz.core.Constants
import com.naltynbekkz.core.preferences.SessionManager
import kotlinx.coroutines.delay

class SplashViewModel @ViewModelInject constructor(
    sessionManager: SessionManager
) : ViewModel() {

    val delayedRefreshTokenLiveData = liveData {
        delay(Constants.SPLASH_SCREEN_DURATION)
        emitSource(sessionManager.tokenLiveData())
    }

}