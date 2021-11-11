package com.najed.najed.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.najed.najed.MainActivityViewModel
import com.najed.najed.R
import com.najed.najed.adapters.FullAdapter
import com.najed.najed.db.Show

class DBFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this).get(MainActivityViewModel::class.java) }
    private lateinit var storedShowsRecyclerView: RecyclerView
    private lateinit var showsAdapter: FullAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dbView = inflater.inflate(R.layout.fragment_d_b, container, false)

        viewModel.getShows().observe(viewLifecycleOwner, {
                showsList -> showsAdapter.update(showsList)
        })

        storedShowsRecyclerView = dbView.findViewById(R.id.db_shows_rv)
        showsAdapter = FullAdapter(this)
        storedShowsRecyclerView.adapter = showsAdapter
        storedShowsRecyclerView.layoutManager = LinearLayoutManager(dbView.context)

        return dbView
    }

    fun deleteShow(show: Show) {
        viewModel.deleteShow(show)
    }
}