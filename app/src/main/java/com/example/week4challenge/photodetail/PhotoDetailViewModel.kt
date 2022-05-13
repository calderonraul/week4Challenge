package com.example.week4challenge.photodetail

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
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
class PhotoDetailViewModel @Inject constructor(private val useCase: GetAllPhotosUseCase) :
    ViewModel() {

    private val showLoading = ObservableBoolean()
    var photoRX: BehaviorSubject<List<PhotoDomain>>? = BehaviorSubject.create()
    private val showError = SingleLiveEvent<String?>()

    fun getAllPhotos() {
        showLoading.set(true)
        viewModelScope.launch {
            val result = useCase.invoke()
            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    photoRX?.onNext(result.successData)
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.exception.message

            }
        }
    }
}