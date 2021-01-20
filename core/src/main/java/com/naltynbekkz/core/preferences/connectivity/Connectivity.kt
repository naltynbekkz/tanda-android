package com.naltynbekkz.core.preferences.connectivity

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Connectivity provider. can get the connectivity status instantly, or as a liveData
 * The rest of the classes in this package are implementation details, you probably will not have to
 * look at them unless the api changes someday
 */

@Singleton
class Connectivity @Inject constructor(
    private val connectivityProvider: ConnectivityProvider
) {

    fun getLiveData(): ConnectivityLiveData {
        return ConnectivityLiveData(connectivityProvider)
    }

    fun get(): Boolean {
        return connectivityProvider.getNetworkState().hasInternet()
    }

}