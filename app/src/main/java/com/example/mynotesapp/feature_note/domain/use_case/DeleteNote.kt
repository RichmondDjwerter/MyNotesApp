package com.example.mynotesapp.feature_note.domain.use_case

import com.example.mynotesapp.feature_note.domain.module.Note
import com.example.mynotesapp.feature_note.domain.repository.NoteRepository

class DeleteNote(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}