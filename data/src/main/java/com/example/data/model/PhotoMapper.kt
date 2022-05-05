package com.example.data.model

import com.example.data.mappers.EntityMapper
import com.example.domain.entity.PhotoDomain

class PhotoMapper :EntityMapper<Photo,PhotoDomain>{
    override fun mapFromEntity(entity: Photo): PhotoDomain {
        return PhotoDomain(
            albumId = entity.albumId,
            id = entity.id,
            title = entity.title,
            url = entity.url,
            thumbnailUrl = entity.thumbnailUrl
        )
    }

    override fun mapToEntity(domainModel: PhotoDomain): Photo {
        return Photo(

            albumId = domainModel.albumId,
            id = domainModel.id,
            title = domainModel.title,
            url = domainModel.url,
            thumbnailUrl = domainModel.thumbnailUrl
        )
    }
}