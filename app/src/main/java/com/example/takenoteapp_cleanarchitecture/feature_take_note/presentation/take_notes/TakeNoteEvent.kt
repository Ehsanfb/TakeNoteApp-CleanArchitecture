package com.example.takenoteapp_cleanarchitecture.feature_take_note.presentation.take_notes

import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.model.Note
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.util.NoteOrder

// All possible user actions are represented as Events in this sealed class
sealed class TakeNoteEvent {
    data class DeleteNote(val note: Note): TakeNoteEvent()
    data class RestoreNote(val note: Note): TakeNoteEvent()
    data object ToggleOrderSection : TakeNoteEvent()
    data class Order(val noteOrder: NoteOrder): TakeNoteEvent()
}