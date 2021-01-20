package com.naltynbekkz.tanda.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.naltynbekkz.auth.TokenDao
import com.naltynbekkz.core.Constants
import com.naltynbekkz.tanda.data.AppDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabaseInstance(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideTokensDao(database: AppDatabase): TokenDao {
        return database.tokensDao()
    }

}

@Module
@InstallIn(ActivityComponent::class)
abstract class DatabaseModuleBind {

    @Binds
    abstract fun bindRoomDatabase(
        appDatabase: AppDatabase
    ): RoomDatabase

}