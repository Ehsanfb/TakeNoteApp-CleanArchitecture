package com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.use_case

import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.model.InvalidNoteException
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.model.Note
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.repository.NoteRepository

class InsertNoteUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(note: Note) {

        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }

        repository.insertNote(note)
    }

}