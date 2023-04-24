package com.work.thirukkural.ui.kural

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
class KuralViewModel @Inject constructor(val kuralRepository: KuralsRepository) : ViewModel() {

    private val _kural: MutableLiveData<Kural> = MutableLiveData()

    val kural: LiveData<Kural> = _kural

    private val _statusMessage: MutableLiveData<String> = MutableLiveData();

    val statusMessage: LiveData<String> = _statusMessage

    fun fetchKural(kuralId: Int = 1) {
        viewModelScope.launch {
            kuralRepository.fetchKural(kuralId).collect {
                _kural.value = it
            }
        }
    }

    fun onFavoriteClicked(kuralId: Int) {
        viewModelScope.launch {
            val rows = kuralRepository.updateFavorite(kuralId)
            if (rows != null) {
                _statusMessage.value =
                    if (rows) "பிடித்த குறள் சேர்க்கப்பட்டது" else "பிடித்த குறள் நீக்கப்பட்டது"
            }
        }
    }


}