package com.work.thirukkural.ui.paal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.work.thirukkural.adapter.AdhigaramAdapter
import com.work.thirukkural.adapter.AdhigaramClickListener
import com.work.thirukkural.data.entities.Adhigaram
import com.work.thirukkural.databinding.FragmentPaalBinding
import com.work.thirukkural.ui.adhigarangal.AdhigaramListFragmentDirections
import com.work.thirukkural.ui.adhigarangal.AdhigaramSplashActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaalFragment : Fragment(), AdhigaramClickListener {

    private var _binding: FragmentPaalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val paalViewModel by viewModels<PaalViewModel>()

        Log.d("XXX", "OnCreateView")

        _binding = FragmentPaalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val part = arguments?.getInt("part") ?: 1
        val partName = getPaalName(part)

        val adhigaramList = binding.adhigaramList
        adhigaramList.layoutManager = LinearLayoutManager(context)
        val adapter = AdhigaramAdapter(emptyList(), this)
        adhigaramList.adapter = adapter
        paalViewModel.adhigarams.observe(viewLifecycleOwner) {
            if(it != null) {
                adapter.adhigarams = it
                adapter.notifyDataSetChanged()
            }
        }
        paalViewModel.fetchAdhigarams(partName)
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getPaalName(part: Int): String {
        return when(part) {
            1 -> "அறத்துப்பால்"
            2 -> "பொருட்பால்"
            3 -> "காமத்துப்பால்"

            else -> "அறத்துப்பால்"
        }
    }

    override fun onAdhigaramClicked(adhigaram: Adhigaram) {
//        val action = AdhigaramListFragmentDirections.adhigaramToSplash(adhigaram.number ?: 1, adhigaram.name ?: "Test")

//        val action = AdhigaramListFragmentDirections.adhigaramToDetail(adhigaram.number ?: 1,
//            adhigaram.start ?: 1, adhigaram.end ?: 10, adhigaram.name ?: "Test")
//        findNavController().navigate(action)

        val bundle = bundleOf("adhigaramId" to adhigaram.number, "adhigaramName" to adhigaram.name, "start" to adhigaram.start,
        "end" to adhigaram.end)
        val intent = Intent(requireActivity(), AdhigaramSplashActivity::class.java)
        intent.putExtras(bundle)
        requireActivity().startActivity(intent)

    }
}

