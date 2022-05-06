package com.example.data

import android.content.Context
import com.example.data.api.PhotosApi
import com.example.data.model.PhotoMapper
import com.example.domain.entity.PhotoDomain
import com.example.utils.util.AppResult
import com.example.utils.util.NetworkManager.isOnline
import com.example.utils.util.handleApiError
import com.example.utils.util.handleSuccess
import com.example.domain.repository.PhotosRepository
import com.example.utils.util.noNetworkConnectivityError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotosRepositoryImpl(
    private val api: PhotosApi,
    private val context: Context,
    private val dao: com.example.data.database.PhotosDAO,
    private val mapper: PhotoMapper
) : PhotosRepository {
    override suspend fun getAllPhotos(): AppResult<List<PhotoDomain>> {
        if (isOnline(context)) {
            return try {
                val response = api.getAllPhotos(100)
                if (response.isSuccessful) {
                    response.body()?.let {
                        withContext(Dispatchers.IO) { dao.add(mapper.toEntityList(it)) }

                    }
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                AppResult.Error(e)
            }
        } else {
            val data = getPhotosFromCache()
            return if (data.isNotEmpty()) {
                AppResult.Success(data)
            } else {
                // TODO: is not good to hve context in repositories since are just business logic, there is a provider to know the network state but in this case, could be better a catch for the exceptions
                context.noNetworkConnectivityError()
            }
        }
    }

    private suspend fun getPhotosFromCache(): List<PhotoDomain> {
        return withContext(Dispatchers.IO) {
            mapper.fromEntityList(dao.findAll())
        }
    }

}