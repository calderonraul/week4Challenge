package com.example.week4challenge.photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.example.week4challenge.model.Photo
import com.example.week4challenge.repository.PhotosRepository
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.viewModelScope
import com.example.week4challenge.util.AppResult
import com.example.week4challenge.util.SingleLiveEvent
import kotlinx.coroutines.launch


class PhotoViewModel(private val repository: PhotosRepository) : ViewModel() {
    // this onservable could be private
    val showLoading = ObservableBoolean()
    val photoList = MutableLiveData<List<Photo>?>()
    val showError = SingleLiveEvent<String?>()

    fun getAllPhotos() {
        showLoading.set(true)
        viewModelScope.launch {
            val result = repository.getAllPhotos()
            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    photoList.value = result.successData
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.exception.message

            }
        }
    }

}