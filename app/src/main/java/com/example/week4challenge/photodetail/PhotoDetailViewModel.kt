package com.example.week4challenge.photodetail

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PhotoDomain
import com.example.domain.useCases.GetAllPhotosUseCase
import com.example.utils.util.AppResult
import com.example.utils.util.SingleLiveEvent
import kotlinx.coroutines.launch

class PhotoDetailViewModel(private val useCase: GetAllPhotosUseCase) :ViewModel(){

    val showLoading = ObservableBoolean()
    val photoList = MutableLiveData<List<PhotoDomain>?>()
    val showError = SingleLiveEvent<String?>()

    fun getAllPhotos(){
        showLoading.set(true)
        viewModelScope.launch {
            val result = useCase.invoke()
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