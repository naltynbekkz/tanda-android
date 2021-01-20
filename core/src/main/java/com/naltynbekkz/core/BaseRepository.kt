package com.naltynbekkz.core

import androidx.lifecycle.*
import com.naltynbekkz.core.network.Result
import com.naltynbekkz.core.preferences.connectivity.Connectivity
import kotlinx.coroutines.delay
import retrofit2.Response
import javax.inject.Inject


abstract class BaseRepository {

    @Inject
    lateinit var connectivity: Connectivity


    fun <T> provideFakeData(t: T, autoReload: Boolean = false) =
        if (autoReload) {
            connectionLiveData {
                delay(1000L)
                emit(Result.Success(t))
            }
        } else {
            liveData {
                if (connectivity.get()) {
                    delay(1000L)
                    emit(Result.Success(t))
                } else {
                    emit(Result.Error(Exception()))
                }
            }
        }


    private fun <T> connectionLiveData(
        block: suspend LiveDataScope<Result<T>>.() -> Unit,
    ): LiveData<Result<T>> = MediatorLiveData<Result<T>>().apply {
        val source = connectivity.getLiveData().switchMap {
            if (it) {
                liveData {
                    block()
                }
            } else {
                MutableLiveData(Result.Error(Exception("network")))
            }
        }
        addSource(source) {
            value = it
            if (it is Result.Success) {
                removeSource(source)
            }
        }
    }

    protected fun <T> networkRequest(
        autoReload: Boolean = false,
        networkCall: suspend () -> Response<T>,
    ): LiveData<Result<T>> =
        if (autoReload) {
            connectionLiveData {
                emit(getResult(networkCall))
            }
        } else {
            liveData {
                emit(Result.Loading())
                emit(getResult(networkCall))
            }
        }

    // TODO: rename
    protected fun <T> fullStackRequest(
        networkCall: suspend () -> Response<T>,
        persist: suspend (T) -> Unit
    ): LiveData<Result<T>> = liveData {
        emit(Result.Loading())
        val result = getResult(networkCall)
        if (result is Result.Success) {
            persist(result.data)
        }
        emit(result)
    }

/*
    fun <T : Any> getPagingSource(
        call: suspend (Int) -> Response<Envelope<Page<T>>>,
    ) = object : PagingSource<Int, T>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
            val page = params.key ?: 1
            val result = getResult { call(page) }

            return if (result is Result.Success) {
                LoadResult.Page(
                    result.data!!.result,
                    if (page == 1) null else page - 1,
                    if (page == result.data.allPages) null else page + 1
                )
            } else {
                LoadResult.Error(Exception(result.message))
            }
        }
    }
    */

    suspend

    fun <T> getResult(call: suspend () -> Response<T>): Result<T> =
        if (connectivity.get()) {
            try {
                val response = call()
                if (response.isSuccessful && response.body() != null) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        } else {
            Result.Error(Exception("network"))
        }

}