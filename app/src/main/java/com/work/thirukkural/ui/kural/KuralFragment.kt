package com.work.thirukkural.ui.kural

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.work.thirukkural.adapter.AdhigaramClickListener
import com.work.thirukkural.data.entities.Adhigaram
import com.work.thirukkural.databinding.FragmentKuralBinding
import com.work.thirukkural.ui.adhigarangal.AdhigaramListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KuralFragment : Fragment() {

    private var _binding: FragmentKuralBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val kuralViewModel by viewModels<KuralViewModel>()

        _binding = FragmentKuralBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val kuralId = arguments?.getInt("kuralId") ?: 1

        val kuralText: TextView = binding.kuralView
        val kuralNumber: TextView = binding.kuralNumber
        val firstExpTextView: TextView = binding.firstExpView
        val secondExpTextView: TextView = binding.secondExpView
        val thirdExpTextView: TextView = binding.thirdExpView

        kuralViewModel.kural.observe(viewLifecycleOwner) {
            if(it != null) {
                kuralNumber.text = it.id.toString()
                kuralText.text = it.kural
                firstExpTextView.text = it.first_exp
                secondExpTextView.text = it.second_exp
                thirdExpTextView.text = it.third_exp
            }
        }
        kuralViewModel.fetchKural(kuralId)
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}

