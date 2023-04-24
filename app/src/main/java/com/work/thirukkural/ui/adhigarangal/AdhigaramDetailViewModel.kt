package com.work.thirukkural.ui.adhigarangal

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
class AdhigaramDetailViewModel @Inject constructor(
    val kuralsRepository: KuralsRepository,
    val adhigaramsRepository: AdhigaramsRepository
) : ViewModel() {


    private val _kurals: MutableLiveData<List<Kural>> = MutableLiveData()

    private val _adhigaram: MutableLiveData<Adhigaram> = MutableLiveData()

    private val _statusMessage: MutableLiveData<String> = MutableLiveData();

    val statusMessage: LiveData<String> = _statusMessage

    val adhigaram: LiveData<Adhigaram> = _adhigaram


    val kurals: LiveData<List<Kural>> = _kurals

    fun fetchKurals(start: Int = 1, end: Int = 10) {
        viewModelScope.launch {
            _kurals.value = kuralsRepository.getKurals(start, end)
        }
    }

    fun fetchAdhigaram(adhigaramId: Int) {
        viewModelScope.launch {
            adhigaramsRepository.getAdhigaram(adhigaramId).collect {
                _adhigaram.value = it
            }
        }
    }


    fun onFavoriteClicked(adhigaramId: Int) {
        viewModelScope.launch {
            val rows = adhigaramsRepository.updateFavorite(adhigaramId)
            if (rows != null) {
                _statusMessage.value =
                    if (rows) "பிடித்த அதிகாரம் சேர்க்கப்பட்டது" else "பிடித்த அதிகாரம் நீக்கப்பட்டது"
            }
        }
    }


}