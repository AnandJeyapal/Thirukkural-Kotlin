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
class SearchViewModel @Inject constructor(
    val kuralsRepository: KuralsRepository,
    val adhigaramsRepository: AdhigaramsRepository
) : ViewModel() {


    private val _kural: MutableLiveData<Kural> = MutableLiveData()

    private val _adhigaram: MutableLiveData<Adhigaram> = MutableLiveData()


    val adhigaram: LiveData<Adhigaram> = _adhigaram


    val kural: LiveData<Kural> = _kural

    fun fetchKural(kuralNumber: Int) {
        viewModelScope.launch {
            _kural.value = kuralsRepository.getKural(kuralNumber)
        }
    }

    fun fetchAdhigaram(adhigaramNumber: Int) {
        viewModelScope.launch {
            adhigaramsRepository.getAdhigaram(adhigaramNumber).collect {
                _adhigaram.value = it
            }
        }
    }



}