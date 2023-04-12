package com.example.rickandmortyapi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapi.databinding.FragmentHomeBinding
import com.example.rickandmortyapi.helpers.ServiceBuilder
import com.example.rickandmortyapi.model.ViewModelHome
import com.example.rickandmortyapi.repository.HomeRepository
import com.example.rickandmortyapi.data.remote.service.ServiceHome

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModelHome: ViewModelHome
    private lateinit var adapterHome: AdapterHome

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val factory = ViewModelHome.ViewModelHomeFactory(
            HomeRepository(
                ServiceBuilder.buildService(ServiceHome::class.java)
            )
        )
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModelHome = ViewModelProvider(this, factory)[ViewModelHome::class.java]

        initViews()
        initRecyclerView()

        return binding.root
    }

    private fun initViews() {
        getCharacter()
    }

    private fun getCharacter() {
        viewModelHome.apply {
            getCharacter(requireContext())
            characterList.observe(viewLifecycleOwner) {
                adapterHome.setData(it)
            }
        }
    }

    private fun initRecyclerView() {
        adapterHome = AdapterHome(requireContext())
        binding.recyclerViewHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterHome
        }
    }
}