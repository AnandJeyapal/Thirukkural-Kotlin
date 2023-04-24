package com.work.thirukkural.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.work.thirukkural.R
import com.work.thirukkural.databinding.FragmentFavoriteMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMainFragment : Fragment() {

    private var _binding: FragmentFavoriteMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val navView: BottomNavigationView = binding.navView

        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_favorites_fragment) as NavHostFragment
        val navController = navHostFragment.findNavController()

        navView.setupWithNavController(navController)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}