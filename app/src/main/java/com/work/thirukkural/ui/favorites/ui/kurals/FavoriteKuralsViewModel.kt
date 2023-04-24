package com.work.thirukkural.ui.favorites.ui.kurals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.thirukkural.data.entities.Kural
import com.work.thirukkural.repository.KuralsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteKuralsViewModel  @Inject constructor(val kuralsRepository: KuralsRepository): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private val _favoriteKurals: MutableLiveData<List<Kural>> = MutableLiveData()

    val favoriteKurals = _favoriteKurals

    fun fetchFavoriteKurals() {
        viewModelScope.launch {
            kuralsRepository.getFavoriteKurals().collect {
                _favoriteKurals.value = it
            }
        }
    }
}