package com.najed.najed.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.najed.najed.APIFragment
import com.najed.najed.api.Shows
import com.najed.najed.databinding.ShowButtonItemBinding
import com.najed.najed.db.Show
import java.lang.Exception

class SimpleAdapter(private val fragment: APIFragment): RecyclerView.Adapter<SimpleAdapter.ItemViewHolder>() {

    private var showsList = Shows()

    class ItemViewHolder(val binding: ShowButtonItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ShowButtonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val show = showsList[position].show
        holder.binding.apply {
            showBtn.text = show.name
            showBtn.setOnClickListener {
                var showConverted = try {
                    Show(0, show.name, show.language, show.summary, show.image.medium)
                } catch (e: Exception) {
                    Show(0, show.name, show.language, "no summary found", "no image found")
                }
                fragment.storeShow(showConverted)
                Toast.makeText(fragment.context, "${show.name} added to the local database", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount() = showsList.size

    fun update(shows: Shows) {
        showsList = shows
        notifyDataSetChanged()
    }

}