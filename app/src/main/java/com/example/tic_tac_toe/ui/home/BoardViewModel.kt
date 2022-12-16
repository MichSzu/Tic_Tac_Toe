package com.example.tic_tac_toe.ui.home

import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tic_tac_toe.R
import com.example.tic_tac_toe.data.Player
import com.example.tic_tac_toe.data.PlayerRepository
import com.example.tic_tac_toe.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(private val playerRepository: PlayerRepository) : BaseViewModel() {

    private val _player1 = MutableLiveData<Player>()
    val player1 = _player1

    private val _player2 = MutableLiveData<Player>()
    val player2 = _player2

    override fun prepare(args: Bundle?) {
        super.prepare(args)

        val player1_name = args?.getString("player1_name")
        val player2_name = args?.getString("player2_name")

        fetchPlayer1(player1_name!!)
        fetchPlayer2(player2_name!!)

    }

    private fun fetchPlayer1(player1_name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.getPlayer(player1_name).collect {
                _player1.postValue(it)
            }
        }
    }
    private fun fetchPlayer2(player2_name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.getPlayer(player2_name).collect {
                _player2.postValue(it)
            }
        }
    }

    var gameActive = true

    var activePlayer = player1

    var filledPos = arrayListOf<String>("a", "a", "a", "a", "a", "a", "a", "a", "a","a", "a", "a", "a", "a", "a", "a", "a", "a",
                                        "a", "a", "a", "a", "a", "a","a")

    var playerTurn = MutableLiveData<String>()

    init {
        playerTurn.value = "Click on the box\nto put X symbol!"
    }

    fun boardClick(button: Button) {

        if(!gameActive){
            return
        }

        var player1_drawable = getPlayer1Drawable(player1.value?.drawable.toString())
        var player2_drawable = getPlayer2Drawable(player2.value?.drawable.toString())

        var clickedTag = Integer.parseInt(button.tag.toString())

        if (filledPos[clickedTag] != "a") {
            return
        }

        filledPos[clickedTag] = activePlayer.value?.id.toString()

        if (activePlayer == player1) {
            button.setCompoundDrawablesWithIntrinsicBounds(player1_drawable, 0, 0, 0)
            activePlayer = player2
            playerTurn.value = "${player2.value?.name} turn"
        } else {
            button.setCompoundDrawablesWithIntrinsicBounds(player2_drawable, 0, 0, 0)
            activePlayer = player1
            playerTurn.value = "${player1.value?.name} turn"
        }

        if(checkForWin(player1.value?.id.toString()) == "WIN"){
            updatePointsForPlayer1()
            gameActive = false
            playerTurn.value = "${player1.value?.name} is a winner"
        }else if(checkForWin(player2.value?.id.toString()) == "WIN"){
            updatePointsForPlayer2()
            gameActive = false
            playerTurn.value = "${player2.value?.name} is a winner"
        }else if(checkForDraw(player1.value?.id.toString(), player2.value?.id.toString()) == "DRAW"){
            gameActive = false
            playerTurn.value = "It's a draw"
        }
    }

    fun getPlayer1Drawable(string: String): Int {
        if(string == "cross_blue" || string == "circle_blue"){
            return R.drawable.g_cr_b
        } else if (string == "cross_green" || string == "circle_green") {
            return R.drawable.g_cr_g
        } else if (string == "cross_orange" || string == "circle_orange") {
            return R.drawable.g_cr_o
        } else if (string == "cross_pink" || string == "circle_pink") {
            return R.drawable.g_cr_pi
        } else if (string == "cross_purple" || string == "circle_purple") {
            return R.drawable.g_cr_pu
        } else if (string == "cross_red" || string == "circle_red") {
            return R.drawable.g_cr_r
        } else if (string == "cross_white" || string == "circle_white") {
            return R.drawable.g_cr_w
        }
        return R.drawable.g_cr_y
    }

    fun getPlayer2Drawable(string: String): Int {
        if(string == "circle_blue" || string == "cross_blue"){
            return R.drawable.g_ci_b
        } else if (string == "circle_green" || string == "cross_green") {
            return R.drawable.g_ci_g
        } else if (string == "circle_orange" || string == "cross_orange") {
            return R.drawable.g_ci_o
        } else if (string == "circle_pink" || string == "cross_pink") {
            return R.drawable.g_ci_pi
        } else if (string == "circle_purple" || string == "cross_purple") {
            return R.drawable.g_ci_pu
        } else if (string == "circle_red" || string == "cross_red") {
            return R.drawable.g_ci_r
        } else if (string == "circle_white" || string == "cross_white") {
            return R.drawable.g_ci_w
        }
        return R.drawable.g_ci_y
    }

    fun checkForWin(player_id : String): String {
        if ((filledPos[0] == player_id && filledPos[1] == player_id && filledPos[2] == player_id && filledPos[3] == player_id && filledPos[4] == player_id) ||
            (filledPos[5] == player_id && filledPos[6] == player_id && filledPos[7] == player_id && filledPos[8] == player_id && filledPos[9] == player_id) ||
            (filledPos[10] == player_id && filledPos[11] == player_id && filledPos[12] == player_id && filledPos[13] == player_id && filledPos[14] == player_id) ||
            (filledPos[15] == player_id && filledPos[16] == player_id && filledPos[17] == player_id && filledPos[18] == player_id && filledPos[19] == player_id) ||
            (filledPos[20] == player_id && filledPos[21] == player_id && filledPos[22] == player_id && filledPos[23] == player_id && filledPos[24] == player_id) ||
            (filledPos[0] == player_id && filledPos[5] == player_id && filledPos[10] == player_id && filledPos[15] == player_id && filledPos[20] == player_id) ||
            (filledPos[1] == player_id && filledPos[6] == player_id && filledPos[11] == player_id && filledPos[16] == player_id && filledPos[21] == player_id) ||
            (filledPos[2] == player_id && filledPos[7] == player_id && filledPos[12] == player_id && filledPos[17] == player_id && filledPos[22] == player_id) ||
            (filledPos[3] == player_id && filledPos[8] == player_id && filledPos[13] == player_id && filledPos[18] == player_id && filledPos[23] == player_id) ||
            (filledPos[4] == player_id && filledPos[9] == player_id && filledPos[14] == player_id && filledPos[19] == player_id && filledPos[24] == player_id) ||
            (filledPos[0] == player_id && filledPos[6] == player_id && filledPos[12] == player_id && filledPos[18] == player_id && filledPos[24] == player_id) ||
            (filledPos[4] == player_id && filledPos[8] == player_id && filledPos[12] == player_id && filledPos[16] == player_id && filledPos[20] == player_id) ){
            return "WIN"
        }else{
            return "PLAY"
        }
    }


    fun checkForDraw(player1_id: String, player2_id: String): String {
        var numbers : MutableList<Int> = mutableListOf()
        for(i in 0..24){
            if(filledPos[i] == player1_id || filledPos[i] == player2_id){
                numbers.add(i)
            }
        }
        if(numbers.size == 25){
            return "DRAW"
        }else{
            return "PLAY"
        }
    }

    fun restartGame(a1: Button, a2: Button,a3: Button, a4: Button,a5: Button,
                    b1: Button, b2: Button,b3: Button, b4: Button,b5: Button,
                    c1: Button, c2: Button,c3: Button, c4: Button,c5: Button,
                    d1: Button, d2: Button,d3: Button, d4: Button,d5: Button,
                    e1: Button, e2: Button,e3: Button, e4: Button,e5: Button,){
        filledPos = arrayListOf<String>("a", "a", "a", "a", "a", "a", "a", "a", "a","a", "a", "a", "a", "a", "a", "a", "a", "a",
                                        "a", "a", "a", "a", "a", "a","a")
        activePlayer = player1
        gameActive = true
        playerTurn.value = "${player1.value?.name} turn"
        a1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        a2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        a3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        a4.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        a5.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        b1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        b2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        b3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        b4.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        b5.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        c1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        c2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        c3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        c4.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        c5.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        d1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        d2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        d3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        d4.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        d5.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        e1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        e2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        e3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        e4.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        e5.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
    }


    fun updatePointsForPlayer1(){
        viewModelScope.launch(Dispatchers.IO){
            val playerIdToAddPoint = player1.value?.id
            if(playerIdToAddPoint != null){
                playerRepository.updatePoints(playerIdToAddPoint)
            }
        }
    }

    fun updatePointsForPlayer2(){
        viewModelScope.launch(Dispatchers.IO){
            val playerIdToAddPoint = player2.value?.id
            if(playerIdToAddPoint != null){
                playerRepository.updatePoints(playerIdToAddPoint)
            }
        }
    }

}







