package com.deviceactivitytracker.di

import com.deviceactivitytracker.data.network.TrackerApiClient
import com.deviceactivitytracker.data.repository.TrackerRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { TrackerApiClient() }
    single { TrackerRepository(get()) }
}
