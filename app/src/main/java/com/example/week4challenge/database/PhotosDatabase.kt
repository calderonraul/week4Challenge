package com.example.week4challenge.database

import androidx.room.Database
import androidx.room.RoomDatabase

import androidx.room.TypeConverters
import com.example.week4challenge.model.Photo

@Database(
    entities = [Photo::class], version = 3, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PhotosDatabase : RoomDatabase() {
    abstract val photosDao: PhotosDAO
}
