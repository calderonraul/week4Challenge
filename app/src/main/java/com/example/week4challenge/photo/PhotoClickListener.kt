package com.example.week4challenge.photo

import com.example.domain.entity.PhotoDomain

interface PhotoClickListener {
    fun onItemClick(photo: PhotoDomain)
}