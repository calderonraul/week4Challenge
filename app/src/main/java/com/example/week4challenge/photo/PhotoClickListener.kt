package com.example.week4challenge.photo

import com.example.data.model.Photo

interface PhotoClickListener {
    fun onItemClick(photo: Photo)
}