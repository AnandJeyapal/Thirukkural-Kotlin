package com.work.thirukkural.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.work.thirukkural.data.moreData
import com.work.thirukkural.data.moreDataImage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreDetailViewModel @Inject constructor() : ViewModel() {

    val moreDetailData  = moreData

    val moreDetailImage = moreDataImage
}