package com.example.tic_tac_toe.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tic_tac_toe.databinding.FragmentHomeBinding
import com.example.tic_tac_toe.ui.BaseFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class HomeFragment : BaseFragment<HomeViewModel>(HomeViewModel::class.java) {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    lateinit var mAdView: AdView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        MobileAds.initialize(this@HomeFragment.requireContext()){}
        mAdView = binding.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = this.viewModel


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}