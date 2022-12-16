package com.example.tic_tac_toe.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.tic_tac_toe.R
import com.example.tic_tac_toe.databinding.FragmentBoardBinding
import com.example.tic_tac_toe.databinding.FragmentNotificationsBinding
import com.example.tic_tac_toe.databinding.FragmentStartGameBinding
import com.example.tic_tac_toe.ui.BaseFragment
import com.example.tic_tac_toe.ui.notifications.NotificationsViewModel
import com.example.tic_tac_toe.ui.utils.ResourceUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardFragment : BaseFragment<BoardViewModel>(BoardViewModel::class.java) {

    private var _binding: FragmentBoardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBoardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = this.viewModel
        binding.lifecycleOwner = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}