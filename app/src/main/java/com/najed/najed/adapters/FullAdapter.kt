package com.najed.najed.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.najed.najed.DBFragment
import com.najed.najed.databinding.ShowFullItemBinding
import com.najed.najed.db.Show

class FullAdapter(private val dbFragment: DBFragment): RecyclerView.Adapter<FullAdapter.ItemViewHolder>() {

    private var showsList = listOf<Show>()

    class ItemViewHolder(val binding: ShowFullItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ShowFullItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val show = showsList[position]
        holder.binding.apply {
            showNameTv.text = show.name
            showLangTv.text = show.language
            Glide.with(dbFragment).load(show.image).into(showIv)

            deleteBtn.setOnClickListener {
                dbFragment.deleteShow(show)
                Toast.makeText(dbFragment.context, "${show.name} deleted successfully", Toast.LENGTH_SHORT).show()
            }

            showLl.setOnClickListener {
                Toast.makeText(dbFragment.context, show.summary, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount() = showsList.size

    fun update(shows: List<Show>) {
        showsList = shows
        notifyDataSetChanged()
    }
}