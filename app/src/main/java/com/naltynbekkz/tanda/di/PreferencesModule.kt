package kz.naltynbek.nulife.di

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Build
import com.naltynbekkz.core.preferences.connectivity.ConnectivityProvider
import com.naltynbekkz.core.preferences.connectivity.ConnectivityProviderImpl
import com.naltynbekkz.core.preferences.connectivity.ConnectivityProviderLegacyImpl
import com.naltynbekkz.tanda.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return with(context) {
            getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        }
    }

    @Provides
    fun provideConnectivityProvider(
        @ApplicationContext context: Context,
    ): ConnectivityProvider {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ConnectivityProviderImpl(connectivityManager)
        } else {
            ConnectivityProviderLegacyImpl(context, connectivityManager)
        }
    }

}