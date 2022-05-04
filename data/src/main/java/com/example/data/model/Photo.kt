package com.example.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entity.PhotoDomain
import kotlinx.parcelize.Parcelize



@Entity(tableName = "photos_table")
data class Photo(
    @ColumnInfo(name = "album_id")
    override val albumId: Int,
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    @ColumnInfo(name = "title")
    override val title: String,
    @ColumnInfo(name = "url")
    override val url: String,
    @ColumnInfo(name = "thumbnailUrl")
    override val thumbnailUrl: String?
) : PhotoDomain
