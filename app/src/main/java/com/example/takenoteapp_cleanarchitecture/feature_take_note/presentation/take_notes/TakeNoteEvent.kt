package com.example.takenoteapp_cleanarchitecture.feature_take_note.presentation.take_notes

import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.model.Note

sealed class TakeNoteEvent {
    data class DeleteNote(val note: Note): TakeNoteEvent()

}