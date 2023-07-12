package com.work.thirukkural.ui.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.work.thirukkural.databinding.FragmentAlarmBinding
import com.work.thirukkural.view.ShakingBell
import dagger.hilt.android.AndroidEntryPoint

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
        val alarmViewModel by viewModels<AlarmViewModel>()

        _binding = FragmentAlarmBinding.inflate(inflater, container, false)
        val root: View = binding.root

        alarmBell = binding.alarmBell
        alarmSwitch = binding.alarmSwitch

        alarmSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) alarmBell.shake() else alarmBell.setAlarmOff()}

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}