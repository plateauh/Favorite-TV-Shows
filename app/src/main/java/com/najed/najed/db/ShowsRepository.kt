package com.najed.najed.db

import androidx.lifecycle.LiveData

class ShowsRepository(private val showDAO: ShowDAO) {

    val getShows: LiveData<List<Show>> = showDAO.getAllShows()

    suspend fun addShow(show: Show){
        showDAO.storeShow(show)
    }

    suspend fun deleteShow(show: Show){
        showDAO.deleteShow(show)
    }

}