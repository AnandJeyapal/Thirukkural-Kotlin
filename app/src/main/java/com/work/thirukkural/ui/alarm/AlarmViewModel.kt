package com.work.thirukkural.ui.alarm

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.work.thirukkural.ThirukkuralApplication
import com.work.thirukkural.repository.KuralsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(application: Application, val kuralsRepository: KuralsRepository) : AndroidViewModel(application) {
    private val sharedPreferences by lazy {
        getApplication<Application>().getSharedPreferences("thirukkural", Context.MODE_PRIVATE)
    }
    private val alarmManager by lazy {
        getApplication<ThirukkuralApplication>().getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }
    fun storeAlarmTime(hourOfDay: Int, minute: Int) {
        // Clear existing alarm
        clearAlarm()
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
        if(_alarmSet.value == true) {
            return
        }
        _alarmSet.value = true
        val editor = sharedPreferences.edit()
        editor.putBoolean("Set", true)
        editor.apply()

        val notificationIntent = Intent(getApplication(), AlarmReceiver::class.java)

        viewModelScope.launch {
            val randomKuralIndex = java.util.Random().nextInt(1330)
            val kural = kuralsRepository.getKural(randomKuralIndex)
            notificationIntent.putExtra("KuralId", randomKuralIndex)
            notificationIntent.putExtra("KuralDescription", kural.kural)
            scheduleAlarm(notificationIntent)
        }

    }

    private fun scheduleAlarm(notificationIntent: Intent) {
        val pendingIntent = PendingIntent.getBroadcast(
            getApplication(),
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val calendar: java.util.Calendar = java.util.Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(java.util.Calendar.HOUR_OF_DAY, sharedPreferences.getInt("Hour", 7))
            set(java.util.Calendar.MINUTE, sharedPreferences.getInt("Minute", 0))
        }
        alarmManager.cancel(pendingIntent)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
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
        value = sharedPreferences.getBoolean("Set", false)
    }.also { updateTime() }
    val alarmSet: LiveData<Boolean> = _alarmSet

    private fun updateTime() {
        val minuteString = "%02d".format(_minute.value)
        _time.value = "${_hour.value}:$minuteString"
    }

}