package com.najed.najed.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Show")
data class Show (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "show_id")
    val id: Int = 0,

    @ColumnInfo(name = "show_name")
    val name: String,

    @ColumnInfo(name = "show_language")
    val language: String,

    @ColumnInfo(name = "show_summary")
    val summary: String? = "no summary found",

    @ColumnInfo(name = "show_image")
    val image: String? = "no image found"

        )