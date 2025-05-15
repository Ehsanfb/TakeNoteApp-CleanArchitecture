package com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.use_case

import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.model.Note
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.flow
import okio.IOException

class GetNoteUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(id: Int): Note? {

        return repository.getNoteById(id)



        /*try {
            emit(repository.getNoteById(id))
        } catch (e: IOException) {

        }*/
    }

}