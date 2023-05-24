package com.example.rickandmortyapi.model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.example.rickandmortyapi.domain.Character
import com.example.rickandmortyapi.domain.Info
import com.example.rickandmortyapi.domain.Results
import com.example.rickandmortyapi.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelCharacters(private val homeRepository: CharactersRepository) : ViewModel() {
    var characterList = MutableLiveData<List<Results?>>()
    var character = MutableLiveData<List<Character?>>()
    var pagesInfo = MutableLiveData<List<Info>>()

    fun getCharacter(context: Context) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = homeRepository.getCharacter()
                if (response.isSuccessful) {
                    val body = response.body()
                    val results = body?.result?.map {
                        it.copy()
                    }
                    characterList.value = results ?: listOf()
                } else {
                    Toast.makeText(context, response.message().toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (exception: Exception) {
                Toast.makeText(context, "${exception.message}", Toast.LENGTH_SHORT).show()
                println("=== $exception ===")
            }
        }
    }



    class ViewModelHomeFactory(private val homeRepository: CharactersRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(CharactersRepository::class.java).newInstance(homeRepository)
        }
    }
}