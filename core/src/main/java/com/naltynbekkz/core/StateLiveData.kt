package com.naltynbekkz.core

import androidx.lifecycle.LiveData
import com.naltynbekkz.core.network.Result
import androidx.lifecycle.MediatorLiveData

class StateLiveData<S, T>(private val liveData: (S) -> LiveData<Result<T>>) :
    MediatorLiveData<Result<T>>() {

    private var source: LiveData<Result<T>>? = null

    fun query(s: S) {
        source?.let { removeSource(it) }
        source = liveData(s)
        addSource(source!!) {
            value = it
        }
    }

}