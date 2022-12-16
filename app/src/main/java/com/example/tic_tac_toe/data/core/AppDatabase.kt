package com.example.tic_tac_toe.data.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tic_tac_toe.data.Player
import com.example.tic_tac_toe.data.PlayerDao

@Database(
    entities = [Player::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}