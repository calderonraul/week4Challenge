package com.example.week4challenge.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.data.api.PhotosApi
import com.example.data.model.PhotoMapper
import com.example.domain.repository.PhotosRepository
import com.example.domain.useCases.GetAllPhotosUseCase
import com.example.week4challenge.BuildConfig.DEBUG
import com.example.week4challenge.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideCountriesApi(retrofit: Retrofit): PhotosApi {
        return retrofit.create(PhotosApi::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val NAME="photos"
    @Provides
    @Singleton
    fun provideDatabase(application: Application): com.example.data.database.PhotosDatabase {
        return Room.databaseBuilder(
            application,
            com.example.data.database.PhotosDatabase::class.java, NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePhotosDao(database: com.example.data.database.PhotosDatabase): com.example.data.database.PhotosDAO {
        return database.photosDao
    }

}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val connectTimeout: Long = 40// 20s
    private const val readTimeout: Long = 40 // 20s
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
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

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

    }

}


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePhotosRepository(
        api: PhotosApi,
        @ApplicationContext context: Context,
        dao: com.example.data.database.PhotosDAO,
        mapper: PhotoMapper
    ): PhotosRepository {
        return com.example.data.PhotosRepositoryImpl(api, context, dao, mapper)
    }

}


@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideMapper(): PhotoMapper {
        return PhotoMapper()
    }

}

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideUseCase(photosRepository: PhotosRepository): GetAllPhotosUseCase {
        return GetAllPhotosUseCase(photosRepository)
    }

}

