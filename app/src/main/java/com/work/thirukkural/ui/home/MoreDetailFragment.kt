package com.work.thirukkural.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.work.thirukkural.R
import com.work.thirukkural.databinding.FragmentMoreDetailBinding
import com.work.thirukkural.utils.shareMoreContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreDetailFragment : Fragment() {

    private var _binding: FragmentMoreDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val moreDetailViewModel by viewModels<MoreDetailViewModel>()

        _binding = FragmentMoreDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val titleTextViewArray = listOf(binding.moreContent.titleOne, binding.moreContent.titleTwo)
        val contentTextViewArray = listOf(binding.moreContent.contentOne, binding.moreContent.contentTwo)
//
        val moreDetailKey = arguments?.getInt("key") ?: 1
        val moreDetailTitle = arguments?.getString("title") ?: "No Title"

        binding.background.setBackgroundResource(moreDetailViewModel.moreDetailImage.get(moreDetailKey) ?:  R.drawable.valluvar)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = moreDetailTitle
//
        val moreData = moreDetailViewModel.moreDetailData
        val moreDetail = moreData.get(moreDetailKey)
        var index = 0
        moreDetail?.forEach{(key, value) ->
            if(key != -1) {
                titleTextViewArray[index].text = getString(key)
            }
            contentTextViewArray[index].text = getString(value)
            index += 1
        }

        val shareFab = binding.fab
        shareFab.setOnClickListener { shareMoreContent(moreDetailKey, requireContext()) }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}