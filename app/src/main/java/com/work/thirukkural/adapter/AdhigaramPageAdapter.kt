package com.work.thirukkural.adapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.work.thirukkural.ui.paal.PaalFragment

class AdhigaramPageAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle){

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putInt("part", position+1)
        val paalFragment = PaalFragment()
        paalFragment.arguments = bundle
        return paalFragment
    }

}