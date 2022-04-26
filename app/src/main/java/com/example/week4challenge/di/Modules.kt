package com.example.week4challenge.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.week4challenge.BuildConfig.DEBUG
import com.example.week4challenge.R
import com.example.week4challenge.database.PhotosDAO
import com.example.week4challenge.database.PhotosDatabase
import com.example.week4challenge.networking.PhotosApi
import com.example.week4challenge.photo.PhotoViewModel
import com.example.week4challenge.repository.PhotosRepository
import com.example.week4challenge.repository.PhotosRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val apiModule= module {
    fun provideCountriesApi(retrofit: Retrofit):PhotosApi{
        return retrofit.create(PhotosApi::class.java)
    }
    single { provideCountriesApi(get()) }
}

val databaseModule= module {
    fun provideDatabase(application: Application):PhotosDatabase{
        return Room.databaseBuilder(application,PhotosDatabase::class.java,"photos")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providePhotosDao(database: PhotosDatabase):PhotosDAO{
        return database.photosDao
    }
    single { provideDatabase(androidApplication()) }
    single { providePhotosDao(get()) }

}

val networkModule= module {
    val connectTimeout : Long = 40// 20s
    val readTimeout : Long  = 40 // 20s

    fun provideHttpClient():OkHttpClient{
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }
    fun provideRetrofit(client: OkHttpClient,baseUrl:String):Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

    }
    single { provideHttpClient() }
    single {
        val baseUrl=androidContext().getString(R.string.BASE_URL)
        provideRetrofit(get(),baseUrl)
    }
}

val repositoryModule= module {
    fun providePhotosRepository(api: PhotosApi,context:Context,dao: PhotosDAO):PhotosRepository{
        return PhotosRepositoryImpl(api,context,dao)
    }
    single { providePhotosRepository(get(),androidContext(),get()) }
}

val viewModelModule= module {
    viewModel{
        PhotoViewModel(repository = get())
    }
}