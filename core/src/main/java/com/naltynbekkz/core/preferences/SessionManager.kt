package com.naltynbekkz.core.preferences

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val prefs: SharedPreferences
) {

    val token: String? get() = prefs.getString(TOKEN, null)

    fun saveToken(sessionId: String) {
        prefs.edit()
            .putString(TOKEN, sessionId)
            .apply()
    }

    fun logout() {
        prefs.edit().remove(TOKEN).apply()
    }

    fun tokenLiveData() = LivePreference<String?>(prefs, TOKEN, null)

    companion object {
        private const val TOKEN = "access_token"
    }
}