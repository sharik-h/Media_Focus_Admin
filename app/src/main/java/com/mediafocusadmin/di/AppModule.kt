package com.mediafocusadmin.di

import com.mediafocusadmin.data.Api
import com.mediafocusadmin.data.RepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesApi(): Api {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("")
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun providesRepo(api: Api): RepoImpl {
        return RepoImpl(api)
    }
}