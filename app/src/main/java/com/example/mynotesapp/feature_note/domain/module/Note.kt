package com.example.mynotesapp.feature_note.domain.module

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(Color.Cyan, Color.Green, Color.Magenta, Color.Yellow, Color.Blue)
    }
}

class InvalidNoteException(message: String) : Exception(message)