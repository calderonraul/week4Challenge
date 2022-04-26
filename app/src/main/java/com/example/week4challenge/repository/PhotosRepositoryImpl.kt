package com.example.week4challenge.repository

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.example.week4challenge.database.PhotosDAO
import com.example.week4challenge.model.Photo
import com.example.week4challenge.networking.PhotosApi
import com.example.week4challenge.util.AppResult
import com.example.week4challenge.util.NetworkManager.isOnline
import com.example.week4challenge.util.handleApiError
import com.example.week4challenge.util.handleSuccess
import com.example.week4challenge.util.noNetworkConnectivityError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class PhotosRepositoryImpl (private val api: PhotosApi,private val context: Context,private val dao: PhotosDAO):PhotosRepository{
    override suspend fun getAllPhotos(): AppResult<List<Photo>> {
        if (isOnline(context)){
            return try {
                val response=api.getAllPhotos()
                if(response.isSuccessful){
                    response.body()?.let {
                        withContext(Dispatchers.IO){dao.add(it)}
                    }
                    handleSuccess(response)
                }else{
                    handleApiError(response)
                }
            }catch (e:Exception){
                AppResult.Error(e)
            }
        }else{
            val data=getPhotosFromCache()
            return if(data.isNotEmpty()){
                Log.d("XD","from db")
                AppResult.Success(data)
            }else{
                context.noNetworkConnectivityError()
            }
        }
    }

    private suspend fun getPhotosFromCache():List<Photo>{
        return withContext(Dispatchers.IO){
            dao.findAll()
        }
    }

}