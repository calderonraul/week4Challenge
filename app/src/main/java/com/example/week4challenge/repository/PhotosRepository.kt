package com.example.week4challenge.repository

import com.example.week4challenge.model.Photo
import com.example.week4challenge.util.AppResult

interface PhotosRepository {
    suspend fun getAllPhotos(): AppResult<List<Photo>>
}