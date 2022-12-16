package com.example.tic_tac_toe.data

import android.os.FileObserver.DELETE
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(player: Player) : Long

    @Query("SELECT * FROM Player WHERE name != '' ORDER BY points DESC")
    fun getPlayers() : Flow<List<Player>>

    @Query("SELECT * FROM Player WHERE name = :name")
    fun getPlayer(name: String) : Flow<Player>

    @Query("UPDATE Player SET points = points + 1 WHERE id = :id")
    fun updatePoints(id : Long) : Int

    @Query("DELETE FROM Player")
    fun deletePlayers() : Int

    @Query("SELECT name FROM Player WHERE name = :name")
    fun checkPlayer(name : String) : String


}