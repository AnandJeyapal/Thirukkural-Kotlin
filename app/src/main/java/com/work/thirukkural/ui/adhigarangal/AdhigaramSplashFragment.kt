package com.work.thirukkural.ui.adhigarangal

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.work.thirukkural.databinding.FragmentAdhigaramSplashBinding
import com.work.thirukkural.databinding.FragmentAdhigarangalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdhigaramSplashFragment : Fragment() {

    private var _binding: FragmentAdhigaramSplashBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdhigaramSplashBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adhigaramName = arguments?.getString("adhigaramName") ?: ""
        val adhigaramId = arguments?.getInt("adhigaramId") ?: ""

        val adhigaramNameTextView = binding.adhigaramName
        adhigaramNameTextView.text = adhigaramName
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

//        requireActivity().window?.apply {
//            decorView.systemUiVisibility =
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
//                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                var flags: Int = decorView.systemUiVisibility
//                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//                decorView.systemUiVisibility = flags
//            }
//
//        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}