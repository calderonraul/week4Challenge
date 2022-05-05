package com.example.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PhotoDomain (
    val albumId: Int,

    val id: Int,

    val title: String,

    val url: String,

    val thumbnailUrl: String?) : Parcelable


