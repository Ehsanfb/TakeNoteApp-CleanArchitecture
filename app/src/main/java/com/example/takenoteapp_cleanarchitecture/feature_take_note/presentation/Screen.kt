package com.example.takenoteapp_cleanarchitecture.feature_take_note.presentation

enum class Screen {
    TakeNoteScreen,
    EditNoteScreen,
}
sealed class NavigationItem(val route: String) {
    object TakeNoteScreen : NavigationItem(Screen.TakeNoteScreen.name)
    object EditNoteScreen : NavigationItem(Screen.EditNoteScreen.name)
}