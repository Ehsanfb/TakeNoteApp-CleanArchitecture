package com.example.takenoteapp_cleanarchitecture.feature_take_note.presentation.edit_notes

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = false
)
