package com.najed.najed

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.najed.najed.api.APIClient
import com.najed.najed.api.APIInterface
import com.najed.najed.api.Shows
import com.najed.najed.db.Show
import com.najed.najed.db.ShowsDatabase
import com.najed.najed.db.ShowsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivityViewModel(app: Application): AndroidViewModel(app) {

    private val repository: ShowsRepository
    private val showsData: LiveData<List<Show>>
    private val apiData: MutableLiveData<Shows>

    init {
        val showsDAO = ShowsDatabase.getInstance(app).ShowDAO()
        repository = ShowsRepository(showsDAO)
        showsData = repository.getShows
        apiData = MutableLiveData()
    }

    fun getShows(): LiveData<List<Show>> {
        return showsData
    }

    fun getAPIShows(): LiveData<Shows>{
        return apiData
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

    fun setDataFromAPI(searchQuery: String) {
        CoroutineScope(IO).launch {
            val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
            val call: Call<Shows?>? = apiInterface!!.getShows(searchQuery)
            try {
                apiData.postValue(call?.execute()?.body()!!)
            } catch (e: Exception) {
                e.printStackTrace()
                call?.cancel()
            }
        }
    }

}