package com.github.bleszerd.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.github.bleszerd.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.github.bleszerd.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository

class DeleteNotesUserCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}