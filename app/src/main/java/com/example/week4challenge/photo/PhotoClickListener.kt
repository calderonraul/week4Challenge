package com.example.week4challenge.photo

import com.example.week4challenge.model.Photo

interface PhotoClickListener {
    fun onItemClick(photo: Photo)
}