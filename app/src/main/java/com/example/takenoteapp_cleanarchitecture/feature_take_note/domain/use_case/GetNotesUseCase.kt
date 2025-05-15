package com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.use_case

import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.model.Note
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(
    private val repository: NoteRepository
) {

    operator fun invoke(): Flow<List<Note>> {

        return repository.getNotes()

    }

}