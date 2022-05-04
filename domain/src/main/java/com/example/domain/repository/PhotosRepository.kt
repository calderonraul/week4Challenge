package com.example.domain.repository


import com.example.domain.entity.PhotoDomain
import com.example.utils.util.AppResult


interface PhotosRepository {
    suspend fun getAllPhotos(): AppResult<List<PhotoDomain>>
}