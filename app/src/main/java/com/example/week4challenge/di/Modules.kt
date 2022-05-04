package com.example.week4challenge.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.domain.repository.PhotosRepository
import com.example.data.api.PhotosApi
import com.example.domain.useCases.GetAllPhotosUseCase
import com.example.week4challenge.BuildConfig.DEBUG
import com.example.week4challenge.R
import com.example.week4challenge.photo.PhotoViewModel
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
    fun provideCountriesApi(retrofit: Retrofit): PhotosApi {
        return retrofit.create(PhotosApi::class.java)
    }
    single { provideCountriesApi(get()) }
}

val databaseModule= module {
    fun provideDatabase(application: Application): com.example.data.database.PhotosDatabase {
        return Room.databaseBuilder(application,
            com.example.data.database.PhotosDatabase::class.java,"photos")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providePhotosDao(database: com.example.data.database.PhotosDatabase): com.example.data.database.PhotosDAO {
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
    fun providePhotosRepository(api: PhotosApi, context:Context, dao: com.example.data.database.PhotosDAO): com.example.domain.repository.PhotosRepository {
        return com.example.data.PhotosRepositoryImpl(api, context, dao)
    }
    single { providePhotosRepository(get(),androidContext(),get()) }
}


val useCaseModule= module {
    fun provideUseCase(photosRepository: PhotosRepository): GetAllPhotosUseCase {
        return GetAllPhotosUseCase(photosRepository)
    }
    single { provideUseCase(get()) }
}

val viewModelModule= module {
    viewModel{
        PhotoViewModel(repository = get())
    }
}