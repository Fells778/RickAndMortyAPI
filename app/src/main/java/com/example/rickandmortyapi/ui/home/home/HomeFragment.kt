package com.example.rickandmortyapi.ui.home.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rickandmortyapi.databinding.FragmentHomeAllAPIBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeAllAPIBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeAllAPIBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    private fun initViews() {
        TODO("Not yet implemented")
    }

}