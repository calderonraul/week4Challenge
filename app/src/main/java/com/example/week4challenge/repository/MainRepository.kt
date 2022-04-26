package com.example.week4challenge.repository

import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest

import com.example.week4challenge.model.Photo

class MainRepository(var mRequestQueue: RequestQueue) {

    var photos = MutableLiveData<ArrayList<Photo>>()

    fun getData(): ArrayList<Photo> {
        var list = ArrayList<Photo>()
        //TODO IMPLEMENT ROOM STUFF

        return list

    }

    fun fetchOnlineData(){
        val url = "https://jsonplaceholder.typicode.com/posts"
        var onlinePhotos = ArrayList<Photo>();

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener
            { response -> //retrieved data is stored in this response object
                for(a in 0 until  response.length()){
                    //this for loop iterates through the retrieved arraylist
                    val obj = response.getJSONObject(a)

                    //retrieve specific variables
                    val albumId = obj.getInt("albumId")
                    val id = obj.getInt("id")
                    val title = obj.getString("title")
                    val url = obj.getString("url")
                    val thumbnail=obj.getString("thumbnailUrl")

                    var photo = Photo(albumId,id,title,url,thumbnail)
                    onlinePhotos.add(photo) //adding Post objects to an arraylist

                }
                photos.value = onlinePhotos //updates the mutablelivedata with the retrieved data

            },
            { error ->
                // We handle any errors here
            }
        )
        // Access the RequestQueue through your singleton class.]
        mRequestQueue.add(jsonArrayRequest)
        //we use the request queue specified in the class contructor.
    }
}

