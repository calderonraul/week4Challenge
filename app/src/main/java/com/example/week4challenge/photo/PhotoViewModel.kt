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
import io.reactivex.Observable
import kotlinx.coroutines.launch
import rx.subjects.BehaviorSubject


class PhotoViewModel(private val repository: GetAllPhotosUseCase) : ViewModel() {
    private val showLoading = ObservableBoolean()
    //var photoList = MutableLiveData<List<PhotoDomain>?>()
    val showError = SingleLiveEvent<String?>()
    var photoRX: BehaviorSubject<List<PhotoDomain>>? =BehaviorSubject.create()

    fun getAllPhotos() {
        showLoading.set(true)
        viewModelScope.launch {
            val result = repository.invoke()
            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    //photoList.value = result.successData
                    photoRX?.onNext(result.successData)
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.exception.message

            }
        }
    }

}