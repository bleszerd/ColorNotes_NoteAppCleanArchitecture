package com.github.bleszerd.cleanarchitecturenoteapp.feature_note.presentation.util

sealed class Screen(val route: String){
    object NoteScreen: Screen("note_screen")
    object AddEditNoteScreen: Screen("add_note_Screen")
}
