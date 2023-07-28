package com.work.thirukkural.ui.alarm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.work.thirukkural.databinding.FragmentAlarmBinding
import com.work.thirukkural.view.ShakingBell
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlarmFragment : Fragment() {

    private var _binding: FragmentAlarmBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var alarmBell: ShakingBell
    private lateinit var alarmSwitch: SwitchCompat

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val alarmViewModel by activityViewModels<AlarmViewModel>()

        _binding = FragmentAlarmBinding.inflate(inflater, container, false)
        val root: View = binding.root

        alarmBell = binding.alarmBell
        alarmSwitch = binding.alarmSwitch

        val timePanel = binding.timePanel
        timePanel.setOnClickListener { showTimePicker() }

        val timeTextView = binding.timeView

        alarmSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                alarmViewModel.setAlarm()
            } else {
                alarmViewModel.clearAlarm()
            }
        }

        alarmViewModel.time.observe(viewLifecycleOwner) {
             timeTextView.text = it
        }

        alarmViewModel.alarmSet.observe(viewLifecycleOwner) { alarmSet ->
            // New value received
            Log.d("XXX", "collecting $alarmSet")
            alarmSwitch.isChecked = alarmSet
            if(alarmSet) alarmBell.shake() else alarmBell.setAlarmOff()
        }

        return root
    }

    private fun showTimePicker() {
        TimePickerFragment().show(childFragmentManager, "timePicker")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}