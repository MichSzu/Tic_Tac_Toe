package com.example.tic_tac_toe.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Player (
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    var name: String,
    var points: Int = 0,
    var drawable: String = ""
)