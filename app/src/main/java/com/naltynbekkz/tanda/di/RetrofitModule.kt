package com.naltynbekkz.tanda.di

import com.naltynbekkz.auth.AuthService
import com.naltynbekkz.core.Constants
import com.naltynbekkz.schools.SchoolsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Hilt dependency injection. Provide services that define network requests.
 */

@Module
@InstallIn(ActivityComponent::class)
object RetrofitModule {

    @Provides
    fun provideRetrofitBuilder(
        okhttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .client(okhttpClient)
    }

    @Provides
    @ActivityScoped
    fun provideAuthService(
        builder: Retrofit.Builder
    ): AuthService {
        return builder
            .baseUrl(Constants.BaseUrl.AUTH)
            .build()
            .create(AuthService::class.java)
    }

    @Provides
    @ActivityScoped
    fun provideSchoolsService(
        builder: Retrofit.Builder
    ): SchoolsService {
        return builder
            .baseUrl(Constants.BaseUrl.SCHOOLS)
            .build()
            .create(SchoolsService::class.java)
    }

}