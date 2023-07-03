package com.work.thirukkural.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.thirukkural.data.entities.Adhigaram
import com.work.thirukkural.data.entities.Kural
import com.work.thirukkural.repository.AdhigaramsRepository
import com.work.thirukkural.repository.KuralsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    val kuralsRepository: KuralsRepository,
    val adhigaramsRepository: AdhigaramsRepository
) : ViewModel() {


    private val _kurals: MutableLiveData<List<Kural>> = MutableLiveData()

    private val _adhigarams: MutableLiveData<List<Adhigaram>> = MutableLiveData()

    private val _statusMessage: MutableLiveData<String> = MutableLiveData();

    val adhigarams: LiveData<List<Adhigaram>> = _adhigarams


    val kurals: LiveData<List<Kural>> = _kurals

    fun fetchKurals(query: String) {
        viewModelScope.launch {
            _kurals.value = kuralsRepository.getKurals(query)
        }
    }

    fun fetchAdhigarams(query: String) {
        viewModelScope.launch {
            _adhigarams.value = adhigaramsRepository.getAdhigarams(query)
        }
    }



}