package com.example.takenoteapp_cleanarchitecture.feature_take_note.presentation.take_notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.model.Note
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TakeNotesViewModel @Inject constructor(
    private val useCases: NoteUseCases
) : ViewModel() {

    private val _notesState = MutableStateFlow<List<Note>>(emptyList())
    val notesState: StateFlow<List<Note>> = _notesState

    private var getNotesJob: Job? = null

    init {
        getNotes()
    }

    private fun getNotes() {
        getNotesJob?.cancel()
        getNotesJob = useCases.getNotesUseCase().onEach { note ->
            _notesState.value = note
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: TakeNoteEvent) {
        when(event) {
            is TakeNoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    useCases.deleteNoteUseCase(event.note)
                }
            }
        }
    }

}