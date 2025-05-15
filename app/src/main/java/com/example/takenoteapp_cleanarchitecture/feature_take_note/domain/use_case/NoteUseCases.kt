package com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.use_case

data class NoteUseCases(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val insertNoteUseCase: InsertNoteUseCase,
    val getNoteUseCase: GetNoteUseCase
)
