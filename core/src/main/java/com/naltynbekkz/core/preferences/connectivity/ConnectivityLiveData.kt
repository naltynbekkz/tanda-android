package com.naltynbekkz.core.preferences.connectivity

import androidx.lifecycle.LiveData

class ConnectivityLiveData(
    private val connectivityProvider: ConnectivityProvider
) : LiveData<Boolean>(), ConnectivityProvider.ConnectivityStateListener {

    override fun onActive() {
        super.onActive()
        connectivityProvider.addListener(this)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityProvider.removeListener(this)
    }

    override fun onStateChange(state: ConnectivityProvider.NetworkState) {
        val hasInternet = state.hasInternet()
        value = hasInternet
    }

}