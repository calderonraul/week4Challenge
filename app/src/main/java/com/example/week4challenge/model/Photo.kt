package com.example.week4challenge.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity (tableName = "photos_table")
data class Photo(
    @ColumnInfo(name = "album_id")
    val albumId:Int,
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "url")
    val url:String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail:String
):Parcelable{}
