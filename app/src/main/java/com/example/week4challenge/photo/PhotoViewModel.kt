package com.example.week4challenge.photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.model.Photo

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PhotoDomain
import com.example.domain.repository.PhotosRepository
import com.example.domain.useCases.GetAllPhotosUseCase
import com.example.utils.util.AppResult

import com.example.utils.util.SingleLiveEvent
import kotlinx.coroutines.launch


class PhotoViewModel(private val repository: GetAllPhotosUseCase) : ViewModel() {
    // this onservable could be private
    val showLoading = ObservableBoolean()
    val photoList = MutableLiveData<List<PhotoDomain>?>()
    val showError = SingleLiveEvent<String?>()

    fun getAllPhotos() {
        showLoading.set(true)
        viewModelScope.launch {
            val result = repository.invoke()
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