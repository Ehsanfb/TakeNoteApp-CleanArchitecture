package com.example.takenoteapp_cleanarchitecture.feature_take_note.presentation.edit_notes

import androidx.compose.ui.focus.FocusState

sealed class EditNoteEvent {
    data class EnterTitle(val text: String) : EditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState): EditNoteEvent()
    data class EnterContent(val text: String) : EditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState): EditNoteEvent()
    data class ChangeColor(val color: Int) : EditNoteEvent()
    object SaveNote : EditNoteEvent()
}
