package com.work.thirukkural.ui.paal

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
class PaalViewModel @Inject constructor(val adhigaramsRepository: AdhigaramsRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Adhigarangal Fragment"
    }
    val text: LiveData<String> = _text

    private val _adhigarams:MutableLiveData<List<Adhigaram>> = MutableLiveData()

    val adhigarams:LiveData<List<Adhigaram>> = _adhigarams

    fun fetchAdhigarams(paal: String) {
        viewModelScope.launch {
            _adhigarams.value = adhigaramsRepository.getAdhigaramsForPaal(paal)
        }
    }

}