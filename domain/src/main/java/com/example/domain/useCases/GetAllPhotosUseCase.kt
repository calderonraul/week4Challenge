package com.example.domain.useCases

import com.example.domain.repository.PhotosRepository

class GetAllPhotosUseCase(private val photosRepository: PhotosRepository) {
suspend operator fun invoke() =  photosRepository.getAllPhotos()
}