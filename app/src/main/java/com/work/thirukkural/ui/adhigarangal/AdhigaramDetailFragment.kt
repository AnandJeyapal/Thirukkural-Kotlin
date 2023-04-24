package com.work.thirukkural.ui.adhigarangal

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.work.thirukkural.adapter.AdhigaramDetailAdapter
import com.work.thirukkural.adapter.KuralClickListener
import com.work.thirukkural.data.entities.Kural
import com.work.thirukkural.databinding.FragmentAdhigaramDetailBinding
import com.work.thirukkural.databinding.FragmentAdhigaramSplashBinding
import com.work.thirukkural.databinding.FragmentAdhigarangalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdhigaramDetailFragment : Fragment(), KuralClickListener {

    private var _binding: FragmentAdhigaramDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdhigaramDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adhigaramName = arguments?.getString("adhigaramName") ?: ""
        val adhigaramId = arguments?.getInt("adhigaramId") ?: ""
        val start = arguments?.getInt("start") ?: 1
        val end = arguments?.getInt("end") ?: 10

//        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        (requireActivity() as AppCompatActivity).supportActionBar?.title = adhigaramName

        val adhigaramDetailViewModel by viewModels<AdhigaramDetailViewModel>()

        val kuralList = binding.kuralList
        kuralList.layoutManager = LinearLayoutManager(context)
        val adapter = AdhigaramDetailAdapter(emptyList(), this)
        kuralList.adapter = adapter

        adhigaramDetailViewModel.kurals.observe(viewLifecycleOwner) {
            adapter.kurals = it
            adapter.notifyDataSetChanged()
        }
        adhigaramDetailViewModel.fetchKurals(start, end)


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onKuralClicked(kural: Kural) {
        val action = AdhigaramDetailFragmentDirections.detailToKural(kural.id ?: 1)
        findNavController().navigate(action)
    }
}