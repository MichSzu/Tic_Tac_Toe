package com.example.tic_tac_toe.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tic_tac_toe.databinding.FragmentNotificationsBinding
import com.example.tic_tac_toe.ui.BaseFragment
import com.example.tic_tac_toe.ui.home.HomeViewModel
import com.example.tic_tac_toe.ui.utils.ResourceUtils
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : BaseFragment<NotificationsViewModel>(NotificationsViewModel::class.java) {

    private var _binding: FragmentNotificationsBinding? = null

    private val binding get() = _binding!!

    lateinit var mAdView: AdView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        MobileAds.initialize(this@NotificationsFragment.requireContext()){}
        mAdView = binding.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = this.viewModel
        binding.resourceUtils = ResourceUtils
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}