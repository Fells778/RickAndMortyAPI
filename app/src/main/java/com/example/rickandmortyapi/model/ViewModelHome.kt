package com.example.rickandmortyapi.model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.domain.Results
import com.example.rickandmortyapi.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelHome(private val homeRepository: HomeRepository) : ViewModel() {
    var characterList = MutableLiveData<List<Results?>>()

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
                    Toast.makeText(context, "Erro na API", Toast.LENGTH_SHORT).show()
                }
            } catch (exception: Exception) {
                Toast.makeText(context, "${exception.message}", Toast.LENGTH_SHORT).show()
                println("=== $exception ===")
            }
        }
    }

    class ViewModelHomeFactory(private val homeRepository: HomeRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(HomeRepository::class.java).newInstance(homeRepository)
        }
    }
}