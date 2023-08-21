package com.mediafocusadmin.di

import android.content.Context
import com.mediafocusadmin.NetwordkObserver.NetworkConnectivityObserver
import com.mediafocusadmin.Room.ExpenseRepo
import com.mediafocusadmin.Room.PayRoomDatabase
import com.mediafocusadmin.Room.PaymentRepo
import com.mediafocusadmin.Room.RoomDatabases
import com.mediafocusadmin.data.Api
import com.mediafocusadmin.data.RepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesNetworkObserver(@ApplicationContext context: Context): NetworkConnectivityObserver {
        return NetworkConnectivityObserver(context)
    }

    @Provides
    @Singleton
    fun providesRepo(api: Api): RepoImpl {
        return RepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RoomDatabases {
        return RoomDatabases.getDatabase(context)
    }

    @Provides
    @Singleton
    fun providePayDatabase(@ApplicationContext context: Context): PayRoomDatabase {
        return PayRoomDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun providesExpRepo(db: RoomDatabases): ExpenseRepo {
        return ExpenseRepo(db)
    }

    @Provides
    @Singleton
    fun providesPayRepo(db: PayRoomDatabase): PaymentRepo {
        return PaymentRepo(db)
    }
}