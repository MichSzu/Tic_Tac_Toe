package com.example.tic_tac_toe.ui.home

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tic_tac_toe.data.Player
import com.example.tic_tac_toe.data.PlayerRepository
import com.example.tic_tac_toe.ui.BaseViewModel
import com.example.tic_tac_toe.ui.NavigationCommand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartGameViewModel @Inject constructor(private val playerRepository: PlayerRepository) : BaseViewModel() {

    private val _player1 = MutableLiveData<Player>()
    val player1 = _player1

    private val _player2 = MutableLiveData<Player>()
    val player2 = _player2

    var namesList = arrayListOf<String>()

    override fun prepare(args: Bundle?) {
        super.prepare(args)
        _player1.value = Player(name = "", points = 0, drawable = "cross_yellow")
        _player2.value = Player(name = "", points = 0, drawable = "circle_yellow")
    }

    fun saveAddPlayerOrEdit() {
        viewModelScope.launch(Dispatchers.IO) {
            val playerToAdd = player1.value
            val p1_name = playerToAdd?.name.toString()
            val p1_check_name = playerRepository.checkPlayer(p1_name)

            if (playerToAdd != null && p1_name != p1_check_name) {
                playerRepository.insert(playerToAdd)
                namesList.add(playerToAdd.name)
            }else if(playerToAdd != null && p1_name == p1_check_name){
                namesList.add(playerToAdd.name)
            }

            val playerToAdd2 = player2.value
            val p2_name = playerToAdd2?.name.toString()
            val p2_check_name = playerRepository.checkPlayer(p2_name)
            if (playerToAdd2 != null && p2_name != p2_check_name) {
                playerRepository.insert(playerToAdd2)
                namesList.add(playerToAdd2.name)
            }else if(playerToAdd2 != null && p2_name == p2_check_name){
                namesList.add(playerToAdd2.name)
            }
        }
    }



    fun goToBoardWithPlayersIds(player1_name: String, player2_name: String) {
        var direction = StartGameFragmentDirections.actionStartGameFragmentToBoardFragment()
            .setPlayer1Name(player1_name).setPlayer2Name(player2_name)
        navigateTo(NavigationCommand.To(direction));
    }

    fun deletePlayers() {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.deletePlayers()
        }
    }
}




