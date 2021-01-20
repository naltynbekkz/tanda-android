package com.naltynbekkz.auth

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "tokens")
data class Token(
    @PrimaryKey
    val id: String,
    val email: String,
    val userId: Long
)