package com.work.thirukkural.ui.favorites.ui.adhigarams

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.thirukkural.data.entities.Adhigaram
import com.work.thirukkural.repository.AdhigaramsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteAdhigaramsViewModel @Inject constructor(val adhigaramsRepository: AdhigaramsRepository): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _favoriteAdhigarams: MutableLiveData<List<Adhigaram>> = MutableLiveData()
    val favoriteAdhigarams = _favoriteAdhigarams

    fun fetchFavoriteAdhigarams() {
        viewModelScope.launch {
            adhigaramsRepository.getFavoriteAdhigarams().collect {
                _favoriteAdhigarams.value = it
            }
        }
    }

}