package com.example.week4challenge.core

import android.app.Application
import com.example.week4challenge.di.*
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@HiltAndroidApp
class PhotosApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        /*startKoin {
            androidLogger()
            androidContext(this@PhotosApplication)
            modules(
                apiModule,
                viewModelModule,
                repositoryModule,
                networkModule,
                databaseModule, useCaseModule,
                mapperModule,
                detailViewModel
            )
        }*/
    }
}