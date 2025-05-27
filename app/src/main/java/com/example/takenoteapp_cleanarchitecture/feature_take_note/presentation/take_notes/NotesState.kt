package com.example.takenoteapp_cleanarchitecture.feature_take_note.presentation.take_notes

import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.model.Note
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.util.NoteOrder
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
