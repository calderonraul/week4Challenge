package com.example.week4challenge.photo

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PhotoDomain
import com.example.domain.useCases.GetAllPhotosUseCase
import com.example.utils.util.AppResult
import com.example.utils.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rx.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val repository: GetAllPhotosUseCase) :
    ViewModel() {
    private val showLoading = ObservableBoolean()

    val showError = SingleLiveEvent<String?>()
    var photoRX: BehaviorSubject<List<PhotoDomain>>? = BehaviorSubject.create()

    fun getAllPhotos() {
        showLoading.set(true)
        viewModelScope.launch {
            val result = repository.invoke()
            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    photoRX?.onNext(result.successData)
                    showError.value = null
                }
                is AppResult.Error -> {
                    showError.value = result.exception.message
                }
            }
        }
    }

}