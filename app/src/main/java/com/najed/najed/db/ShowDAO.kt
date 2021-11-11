package com.najed.najed.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShowDAO {

    @Insert
    fun storeShow(show: Show)

    @Query("SELECT * FROM SHOW ORDER BY show_id ASC")
    fun getAllShows(): LiveData<List<Show>>

    @Delete
    fun deleteShow(show: Show)

}