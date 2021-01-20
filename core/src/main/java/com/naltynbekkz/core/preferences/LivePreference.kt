package com.naltynbekkz.core.preferences

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData

@Suppress("UNCHECKED_CAST")
class LivePreference<T> constructor(
    private val preferences: SharedPreferences,
    private val key: String,
    private val defaultValue: T
) : MutableLiveData<T>() {

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { preferences, s ->
        if (key == s) {
            postValue((preferences.all[key] as T) ?: defaultValue)
        }
    }

    override fun onActive() {
        super.onActive()
        value = (preferences.all[key] as T) ?: defaultValue
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onInactive() {
        super.onInactive()
        preferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}