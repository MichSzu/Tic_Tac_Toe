package com.example.tic_tac_toe.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tic_tac_toe.ui.BaseViewModel
import com.example.tic_tac_toe.ui.NavigationCommand

class HomeViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun startNewGame(){
        var direction = HomeFragmentDirections.actionNavigationHomeToStartGameFragment()
        navigateTo(NavigationCommand.To(direction));
    }
}