package com.example.takenoteapp_cleanarchitecture.feature_take_note.presentation.edit_notes

import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.model.InvalidNoteException
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.model.Note
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val useCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = MutableStateFlow(NoteTextFieldState(hint = "Enter Title"))
    val noteTitle: StateFlow<NoteTextFieldState> = _noteTitle

    private val _noteContent = MutableStateFlow(NoteTextFieldState(hint = "Enter Something"))
    val noteContent: StateFlow<NoteTextFieldState> = _noteContent

    private val _noteColor = MutableStateFlow(Note.noteColors.random().toArgb())
    val noteColor: StateFlow<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                getNote(noteId)
            }
        }
    }

    private fun getNote(noteId: Int) {
        viewModelScope.launch {
            useCases.getNoteUseCase(noteId)?.also { note ->
                currentNoteId = note.id
                _noteTitle.value = _noteTitle.value.copy(text = note.title, isHintVisible = false)
                _noteContent.value =
                    _noteContent.value.copy(text = note.content, isHintVisible = false)
                _noteColor.value = note.color
            }
        }
    }

    fun onEvent(event: EditNoteEvent) {

        when (event) {
            is EditNoteEvent.EnterTitle -> {
                _noteTitle.value = _noteTitle.value.copy(text = event.text)
            }

            is EditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value =
                    _noteTitle.value.copy(isHintVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank())
            }

            is EditNoteEvent.EnterContent -> {
                _noteContent.value = _noteContent.value.copy(text = event.text)
            }

            is EditNoteEvent.ChangeContentFocus -> {
                _noteContent.value =
                    _noteContent.value.copy(isHintVisible = !event.focusState.isFocused && _noteContent.value.text.isBlank())
            }

            EditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        useCases.insertNoteUseCase(
                            Note(
                                currentNoteId,
                                noteTitle.value.text,
                                noteContent.value.text,
                                System.currentTimeMillis(),
                                noteColor.value
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                e.message ?: "note has not been saved"
                            )
                        )
                    }
                }
            }

            is EditNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }

        }

    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }

}