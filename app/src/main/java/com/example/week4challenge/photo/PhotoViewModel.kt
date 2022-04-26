package com.example.week4challenge.photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.example.week4challenge.model.Photo
import com.example.week4challenge.repository.MainRepository

class PhotoViewModel (var mRequestQueue: RequestQueue): ViewModel(){

    var localposts =  MutableLiveData<ArrayList<Photo>>() //this mutable will store local data
    var onlineposts =  MutableLiveData<ArrayList<Photo>>() //stores data retrieved from server
    var mainRepository = MainRepository(mRequestQueue) //initializing the MainRepository

    init{
        localposts.value = mainRepository.getData()  //fetching local data
        mainRepository.fetchOnlineData() //fetching online data
        onlineposts = mainRepository.photos //updating the onlineposts array with new data
    }


}