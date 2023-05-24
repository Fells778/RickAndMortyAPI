package com.example.rickandmortyapi.repository

import com.example.rickandmortyapi.data.remote.service.ServiceHome
import com.example.rickandmortyapi.domain.Character
import retrofit2.Response

class CharactersRepository(private var serviceHome: ServiceHome) {
    suspend fun getCharacter(): Response<Character> {
        return serviceHome.getCharacter()
    }

}