package com.example.week4challenge.networking

import com.example.week4challenge.model.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {
    @GET("/photos")
    suspend fun getAllPhotos(@Query("_limit")limit:Int?):Response<List<Photo>>
}