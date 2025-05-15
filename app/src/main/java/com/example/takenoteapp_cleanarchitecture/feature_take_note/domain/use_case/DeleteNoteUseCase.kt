package com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.use_case

import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.model.Note
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.repository.NoteRepository

class DeleteNoteUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }

}