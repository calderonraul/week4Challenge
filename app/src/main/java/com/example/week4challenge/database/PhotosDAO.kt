package com.example.week4challenge.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.week4challenge.model.Photo


@Dao
interface PhotosDAO {
    @Query("SELECT * FROM  photos_table")
    fun findAll():List<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(photo:List<Photo>)
}