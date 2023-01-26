package com.example.mynotesapp.feature_note.presentation.notes

import com.example.mynotesapp.feature_note.domain.module.Note
import com.example.mynotesapp.feature_note.domain.util.NoteOrder

sealed class NotesEvents {
    data class Order(val noteOrder: NoteOrder) : NotesEvents()
    data class DeleteNote(val note: Note) : NotesEvents()
    object RestoreNote : NotesEvents()
    object ToggleOrderSelection : NotesEvents()
}
