package com.naltynbekkz.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * live data that you can refresh.
 * example:
 *      // in the viewModel file:
 *
 *      val a = RefreshLiveData {
 *          repository.getData()
 *      }
 *
 *      // in an activity/fragment file:
 *
 *      a.observe(...) // here you are already observing the data
 *      refresh.setOnClickListener{
 *          a.refresh() // optionally you can refresh the data
 *      }
 */
class RefreshLiveData<T>(
    private val source: () -> LiveData<T>
) : MediatorLiveData<T>() {

    private var sourceLiveData = source().also { liveData ->
        addSource(liveData) {
            value = it
        }
    }

    fun refresh() {
        removeSource(sourceLiveData)
        sourceLiveData = source()
        addSource(sourceLiveData) { value = it }
    }
}