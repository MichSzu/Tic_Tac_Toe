package com.example.tic_tac_toe.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tic_tac_toe.data.Player
import com.example.tic_tac_toe.data.PlayerRepository
import com.example.tic_tac_toe.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(private val playerRepository: PlayerRepository) : BaseViewModel() {

    private val _playerList =  MutableLiveData<List<Player>>()
    val playerList : LiveData<List<Player>> = _playerList

    init{
        fetchPlayerList()
    }

    private fun fetchPlayerList(){
        viewModelScope.launch {
            playerRepository.getPlayers().collect {
                _playerList.postValue((it))
            }
        }
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}

