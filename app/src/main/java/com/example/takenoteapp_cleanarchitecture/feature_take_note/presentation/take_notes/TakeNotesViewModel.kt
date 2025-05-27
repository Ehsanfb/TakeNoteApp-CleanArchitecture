package com.example.takenoteapp_cleanarchitecture.feature_take_note.presentation.take_notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.use_case.NoteUseCases
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.util.NoteOrder
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.util.OrderType
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

    private val _notesState = MutableStateFlow(NotesState())
    val notesState: StateFlow<NotesState> = _notesState

    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = useCases.getNotesUseCase(noteOrder).onEach { notes ->
            _notesState.value = notesState.value.copy(notes = notes, noteOrder = noteOrder)
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: TakeNoteEvent) {
        when (event) {
            is TakeNoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    useCases.deleteNoteUseCase(event.note)
                }
            }

            is TakeNoteEvent.RestoreNote -> {
                viewModelScope.launch {
                    useCases.insertNoteUseCase(event.note)
                }
            }

            is TakeNoteEvent.Order -> {
                if (notesState.value.noteOrder::class == event.noteOrder::class &&
                    notesState.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }

            is TakeNoteEvent.ToggleOrderSection -> {
                _notesState.value =
                    notesState.value.copy(isOrderSectionVisible = !notesState.value.isOrderSectionVisible)
            }
        }
    }

}