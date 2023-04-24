package com.work.thirukkural.ui.paal

import android.util.Log
import androidx.lifecycle.*
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

    private val _aramAdhigarams:LiveData<List<Adhigaram>> = MutableLiveData()
    private val _porulAdhigarams:LiveData<List<Adhigaram>> = MutableLiveData()
    private val _inbamAdhigarams:LiveData<List<Adhigaram>> = MutableLiveData()

    private val _adhigarams:MutableLiveData<List<Adhigaram>> = MutableLiveData()



    val aramAdhigarams:LiveData<List<Adhigaram>> = _aramAdhigarams
    val porulAdhigarams:LiveData<List<Adhigaram>> = _porulAdhigarams
    val inbamAdhigarams:LiveData<List<Adhigaram>> = _inbamAdhigarams

    val adhigarams:LiveData<List<Adhigaram>> = _adhigarams

    fun fetchAdhigarams(paal: String) {
        Log.d("XXX", paal)
        viewModelScope.launch {
            _adhigarams.value = adhigaramsRepository.getAdhigaramsForPaal(paal)
        }
    }

//    init {
//        viewModelScope.launch {
//            _aramAdhigarams.apply { adhigaramsRepository.getAdhigaramsForPaal("").asLiveData()}
//            _porulAdhigarams.apply { adhigaramsRepository.getAdhigaramsForPaal("").asLiveData()}
//            _inbamAdhigarams.apply { adhigaramsRepository.getAdhigaramsForPaal("").asLiveData()}
//        }
//    }




}