package com.example.mynotesapp.feature_note.domain.use_case

import com.example.mynotesapp.feature_note.domain.module.InvalidNoteException
import com.example.mynotesapp.feature_note.domain.module.Note
import com.example.mynotesapp.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("Title of the note cannot be empty!")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("Content of the note cannot be empty!")
        }
        repository.insertNote(note)
    }

}