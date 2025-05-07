package com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.repository

import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

}