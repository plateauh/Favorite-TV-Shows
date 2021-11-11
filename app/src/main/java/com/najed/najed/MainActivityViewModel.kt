package com.najed.najed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.najed.najed.db.Show
import com.najed.najed.db.ShowsDatabase
import com.najed.najed.db.ShowsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivityViewModel(app: Application): AndroidViewModel(app) {

    private val repository: ShowsRepository
    private val showsData: LiveData<List<Show>>

    init {
        val showsDAO = ShowsDatabase.getInstance(app).ShowDAO()
        repository = ShowsRepository(showsDAO)
        showsData = repository.getShows
    }

    fun getShows(): LiveData<List<Show>> {
        return showsData
    }


    fun addShow(show: Show) {
        CoroutineScope(IO).launch {
            repository.addShow(show)
        }
    }

    fun deleteShow(show: Show){
        CoroutineScope(IO).launch {
            repository.deleteShow(show)
        }
    }

}