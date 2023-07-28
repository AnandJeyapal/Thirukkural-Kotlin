package com.work.thirukkural.ui.alarm

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences by lazy {
        getApplication<Application>().getSharedPreferences("thirukkural", Context.MODE_PRIVATE)
    }
    fun storeAlarmTime(hourOfDay: Int, minute: Int) {
        val editor = sharedPreferences.edit()
        with(editor) {
            putInt("Hour", hourOfDay)
            putInt("Minute", minute)
            putBoolean("Set", true)
            apply()
        }
        _hour.value = hourOfDay
        _minute.value = minute
        setAlarm()
        val minuteString = "%02d".format(minute)
        _time.value = "$hourOfDay:$minuteString"
    }

    fun setAlarm() {
        _alarmSet.value = true
        val editor = sharedPreferences.edit()
        editor.putBoolean("Set", true)
        editor.apply()
    }

    fun clearAlarm() {
        _alarmSet.value = false
        val editor = sharedPreferences.edit()
        editor.putBoolean("Set", false)
        editor.apply()
    }

    private val _time = MutableLiveData<String>().apply {
        value ="7:00"
    }
    val time: LiveData<String> = _time

    private val _hour = MutableLiveData<Int>().apply {
        value = sharedPreferences.getInt("Hour", 7)
    }
    val hour: LiveData<Int> = _hour

    private val _minute = MutableLiveData<Int>().apply {
        value = sharedPreferences.getInt("Minute", 0)
    }
    val minute: LiveData<Int> = _minute


    private val _alarmSet = MutableLiveData<Boolean>().apply {
        sharedPreferences.getBoolean("Set", false)
    }
    val alarmSet: LiveData<Boolean> = _alarmSet

}