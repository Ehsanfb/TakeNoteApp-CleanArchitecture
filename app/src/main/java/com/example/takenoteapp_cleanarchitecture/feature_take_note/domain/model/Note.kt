package com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.takenoteapp_cleanarchitecture.ui.theme.BabyBlue
import com.example.takenoteapp_cleanarchitecture.ui.theme.LightGreen
import com.example.takenoteapp_cleanarchitecture.ui.theme.RedOrange
import com.example.takenoteapp_cleanarchitecture.ui.theme.RedPink
import com.example.takenoteapp_cleanarchitecture.ui.theme.Violet

@Entity
data class Note(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String) : Exception(message)
