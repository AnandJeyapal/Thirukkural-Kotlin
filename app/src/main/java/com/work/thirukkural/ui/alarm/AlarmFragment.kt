package com.work.thirukkural.ui.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.work.thirukkural.databinding.FragmentAlarmBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlarmFragment : Fragment() {

    private var _binding: FragmentAlarmBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val alarmViewModel by viewModels<AlarmViewModel>()

        _binding = FragmentAlarmBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        alarmViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}