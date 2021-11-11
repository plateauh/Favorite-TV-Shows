package com.najed.najed.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.najed.najed.R


class HomeFragment : Fragment() {

    lateinit var apiButton: Button
    lateinit var dbButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeView = inflater.inflate(R.layout.fragment_home, container, false)
        apiButton = homeView.findViewById(R.id.browse_api_btn)
        apiButton.setOnClickListener {
            Navigation.findNavController(homeView).navigate(R.id.action_homeFragment_to_APIFragment)
        }

        dbButton = homeView.findViewById(R.id.local_db_btn)
        dbButton.setOnClickListener {
            Navigation.findNavController(homeView).navigate(R.id.action_homeFragment_to_DBFragment)
        }
        return homeView
    }

}