package com.naltynbekkz.auth

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TokenDao {

    @Query("SELECT * FROM tokens")
    fun getTokens(): LiveData<List<Token>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(token: Token)

    @Delete
    suspend fun delete(token: Token)

}