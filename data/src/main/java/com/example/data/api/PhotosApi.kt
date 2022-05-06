package com.example.data.api

import com.example.data.model.Photo
import com.example.domain.entity.PhotoDomain
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {
    @GET("/photos")
    suspend fun getAllPhotos(@Query("_limit") limit: Int?): Response<List<PhotoDomain>>
}