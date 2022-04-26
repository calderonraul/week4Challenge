package com.example.week4challenge.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.RequestQueue

/*
class PhotoViewModelFactory (var mRequestQueue: RequestQueue): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoViewModel::class.java)){
            return PhotoViewModel(mRequestQueue) as T
        }
        throw IllegalArgumentException("Unknown class")

    }
}*/