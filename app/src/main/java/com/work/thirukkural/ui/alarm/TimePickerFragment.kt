package com.work.thirukkural.ui.alarm

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels

class TimePickerFragment: DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private val alarmViewModel by activityViewModels<AlarmViewModel>()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val hour = alarmViewModel.hour.value ?: 7
        val minute = alarmViewModel.minute.value ?: 0
        return TimePickerDialog(activity, this, hour, minute, true)
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        alarmViewModel.storeAlarmTime(hourOfDay, minute)
    }
}