package com.example.tic_tac_toe.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlayerRepository @Inject constructor(private val playerDao: PlayerDao) {

    fun insert(player: Player) = playerDao.insert(player)

    fun getPlayers() = playerDao.getPlayers()

    fun getPlayer(name: String) = playerDao.getPlayer(name)

    fun updatePoints(id : Long) = playerDao.updatePoints(id)

    fun deletePlayers() = playerDao.deletePlayers()

    fun checkPlayer(name : String) = playerDao.checkPlayer(name)
}