package com.example.domain.repository

import com.example.data.model.Photo
import com.example.data.util.AppResult


interface PhotosRepository {
    suspend fun getAllPhotos(): AppResult<List<Photo>>
}