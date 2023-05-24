package com.example.rickandmortyapi.ui.home.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapi.data.remote.service.ServiceHome
import com.example.rickandmortyapi.databinding.FragmentHomeBinding
import com.example.rickandmortyapi.domain.Character
import com.example.rickandmortyapi.helpers.ServiceBuilder
import com.example.rickandmortyapi.model.ViewModelCharacters
import com.example.rickandmortyapi.repository.CharactersRepository

class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModelHome: ViewModelCharacters
    private lateinit var adapterHome: AdapterCharacteres
    private var count = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val factory = ViewModelCharacters.ViewModelHomeFactory(
            CharactersRepository(
                ServiceBuilder.buildService(ServiceHome::class.java)
            )
        )
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModelHome = ViewModelProvider(this, factory)[ViewModelCharacters::class.java]

        initViews()
        initRecyclerView()

        return binding.root
    }

    private fun initViews() {
        getCharacter()
        getCharacterPage()
    }

    private fun shimmerEffectCharacters() {
        binding.apply {
            recyclerViewHome.isVisible = true
            shimmerLayoutCharacters.isVisible = false
        }
    }

    private fun getCharacterPage() {
        viewModelHome.apply {
            pagesInfo.observe(viewLifecycleOwner) {
                binding.textViewNextPage.setOnClickListener {
                    count++
                    Toast.makeText(context, "$count", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun messageResponse(page: Character) {
        Toast.makeText(context, "${page.info.pages}", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, "${page.info}", Toast.LENGTH_SHORT).show()
    }

    private fun getCharacter() {
        viewModelHome.apply {
            getCharacter(requireContext())
            characterList.observe(viewLifecycleOwner) { charactersResult ->
                if (charactersResult.isNotEmpty()){
                    shimmerEffectCharacters()
                }
                adapterHome.setData(charactersResult)
            }
        }
    }

    private fun initRecyclerView() {
        adapterHome = AdapterCharacteres(requireContext())
        binding.recyclerViewHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterHome
        }
    }
}