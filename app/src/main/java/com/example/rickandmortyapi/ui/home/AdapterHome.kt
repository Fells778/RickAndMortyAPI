package com.example.rickandmortyapi.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.databinding.CustomLayoutHomeBinding
import com.example.rickandmortyapi.domain.Results

class AdapterHome(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listHome = emptyList<Results?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(character: List<Results?>) {
        listHome = character
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = CustomLayoutHomeBinding.inflate(LayoutInflater.from(context), parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder -> {
                listHome[position]?.let { holder.bind(it) }
            }
        }
    }

    override fun getItemCount() = listHome.size

    inner class HomeViewHolder(binding: CustomLayoutHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val textName = binding.textViewNameCustomHome
        private val textStatus = binding.textViewStatusCustomHome
        private val textLastLocation = binding.textViewLastLocationCustomHome
        private val textSeeLast = binding.textViewSeeLastCustomHome
        private val textSpecies = binding.textViewSpeciesCustomHome
        private val imageView = binding.imageViewCustomHome
        private val viewStatus = binding.viewAliveOrDeadCustomHome

        fun bind(results: Results) {
            Glide.with(itemView.context)
                .load(results.image)
                .placeholder(R.drawable.custom_loading)
                .error(R.drawable.image_not_found)
                .into(imageView)

            textName.text = results.name
            textStatus.text = results.status
            textSpecies.text = results.species
            textLastLocation.text = results.location.name
            textSeeLast.text = results.origin.name

            backgroundStatus(results.status, viewStatus)
        }
    }

    private fun backgroundStatus(status: String, view: View) {
        if (status == "Alive") {
            view.setBackgroundResource(R.drawable.custom_circle_green)
        }
        if (status == "Dead") {
            view.setBackgroundResource(R.drawable.custom_circle_red)
        } else if (status == "unknown") {
            view.setBackgroundResource(R.drawable.custom_circle_gray)
        }
    }
}