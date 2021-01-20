package com.naltynbekkz.tanda.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naltynbekkz.auth.Token
import com.naltynbekkz.auth.TokenDao

/**
 * Database class
 */

@Database(
    entities = [
        Token::class
    ],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tokensDao(): TokenDao
//    abstract fun accessDao(): AccessDao
//    abstract fun authDao(): AuthDao

}
