package com.mediafocusadmin.NetwordkObserver

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Lost, Losing, UnAvailable
    }
}