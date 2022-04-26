package com.example.week4challenge.networking

import com.example.week4challenge.model.Photo
import retrofit2.Response
import retrofit2.http.GET

interface PhotosApi {
    @GET("/photos")
    suspend fun getAllPhotos():Response<List<Photo>>
}