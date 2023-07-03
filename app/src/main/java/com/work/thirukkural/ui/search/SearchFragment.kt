package com.work.thirukkural.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.work.thirukkural.R
import com.work.thirukkural.data.entities.Adhigaram
import com.work.thirukkural.data.entities.Kural
import com.work.thirukkural.databinding.FragmentSearchHomeBinding
import com.work.thirukkural.ui.adhigarangal.AdhigaramSplashActivity
import com.work.thirukkural.ui.kural.KuralActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var freeText: EditText
    private lateinit var adhigramOrKuralEnText: EditText
    private var _binding: FragmentSearchHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val radioGroup = binding.radioGroup
        adhigramOrKuralEnText = binding.kuralAdhigaraEn
        freeText = binding.textSearch
        val searchButton = binding.searchBtn
        adhigramOrKuralEnText.visibility = View.GONE
        freeText.visibility = View.GONE
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            run {
                adhigramOrKuralEnText.visibility =
                    if (checkedId == R.id.adhigara_en || checkedId == R.id.kural_en) View.VISIBLE else View.GONE
                freeText.visibility = if (checkedId == R.id.free_text) View.VISIBLE else View.GONE
                adhigramOrKuralEnText.hint =  if (checkedId == R.id.adhigara_en) "அதிகார எண்" else if(checkedId == R.id.kural_en) "குரல் எண்" else ""
            }
        }
        searchButton.setOnClickListener {
            if(validate(radioGroup.checkedRadioButtonId)) {
                search(radioGroup.checkedRadioButtonId)
            }
        }
        searchViewModel.adhigaram.observe(viewLifecycleOwner) {
            if(it != null) {
               onAdhigaramClicked(it)
            }
        }
        searchViewModel.kural.observe(viewLifecycleOwner) {
            if(it != null) {
                onKuralClicked(it)
            }
        }

        return root
    }

    private fun fetchKural(kuralNumber: Int?) {
       if(kuralNumber != null && kuralNumber > 0 && kuralNumber <= 1330) {
           searchViewModel.fetchKural(kuralNumber)
       } else {
           Toast.makeText(requireContext(), "தவறான குறள் எண்", Toast.LENGTH_SHORT).show();
       }
    }

    private fun fetchAdhigaram(adhigaramNumber: Int?) {
        if(adhigaramNumber != null && adhigaramNumber > 0 && adhigaramNumber <= 133) {
            searchViewModel.fetchAdhigaram(adhigaramNumber)
        } else {
            Toast.makeText(requireContext(), "தவறான அதிகார எண்", Toast.LENGTH_SHORT).show();
        }
    }

    private fun search(checkedRadioButtonId: Int) {
        when(checkedRadioButtonId) {
            R.id.kural_en -> fetchKural(adhigramOrKuralEnText.text.toString().toIntOrNull())
             R.id.adhigara_en ->  fetchAdhigaram(adhigramOrKuralEnText.text.toString().toIntOrNull())
            R.id.free_text -> navigateToResult(freeText.text.toString())
        }
    }

    private fun navigateToResult(query: String) {
        val action = SearchFragmentDirections.searchToResults(query)
        findNavController().navigate(action)
    }

    private fun validate(checkedRadioButtonId: Int): Boolean {
        return when(checkedRadioButtonId) {
            R.id.kural_en, R.id.adhigara_en -> adhigramOrKuralEnText.text.toString().isNotEmpty()
            R.id.free_text -> freeText.text.toString().isNotEmpty()
            else -> true
        }
    }

    fun onAdhigaramClicked(adhigaram: Adhigaram) {
        val bundle = bundleOf("adhigaramId" to adhigaram.number, "adhigaramName" to adhigaram.name, "start" to adhigaram.start,
            "end" to adhigaram.end)
        val intent = Intent(requireActivity(), AdhigaramSplashActivity::class.java)
        intent.putExtras(bundle)
        requireActivity().startActivity(intent)
    }

    fun onKuralClicked(kural: Kural) {
        val intent = Intent(requireActivity(), KuralActivity::class.java)
        val extras = bundleOf("kuralId" to kural.id)
        intent.putExtras(extras)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}