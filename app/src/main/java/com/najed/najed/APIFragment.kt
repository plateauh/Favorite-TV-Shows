package com.najed.najed

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.najed.najed.adapters.SimpleAdapter
import com.najed.najed.api.APIClient
import com.najed.najed.api.APIInterface
import com.najed.najed.api.Shows
import com.najed.najed.db.Show
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class APIFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this).get(MainActivityViewModel::class.java) }
    private lateinit var showsRecyclerView: RecyclerView
    private lateinit var showsAdapter: SimpleAdapter
    private lateinit var queryEditText: EditText
    private lateinit var searchButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val apiView = inflater.inflate(R.layout.fragment_a_p_i, container, false)

        showsRecyclerView = apiView.findViewById(R.id.api_shows_rv)
        showsAdapter = SimpleAdapter(this)
        showsRecyclerView.adapter = showsAdapter
        showsRecyclerView.layoutManager = LinearLayoutManager(apiView.context)

        queryEditText = apiView.findViewById(R.id.search_et)
        searchButton = apiView.findViewById(R.id.search_btn)
        searchButton.setOnClickListener {
            if (queryEditText.text.isNotEmpty())
                getDataFromAPI(queryEditText.text.toString())
            else
                Toast.makeText(apiView.context, "Please enter something", Toast.LENGTH_SHORT).show()
        }
        return apiView
    }

    fun storeShow(show: Show) {
        viewModel.addShow(show)
    }

    private fun getDataFromAPI(searchQuery: String) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<Shows?>? = apiInterface!!.getShows(searchQuery)
        call?.enqueue(object: Callback<Shows?> {
            override fun onResponse(call: Call<Shows?>, response: Response<Shows?>) {
                val result = response.body()!!
                showsAdapter.update(result)
                if (result.isEmpty())
                    Toast.makeText(this@APIFragment.context, "No result found", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Shows?>, t: Throwable) {
                call.cancel()
            }
        })
    }
}