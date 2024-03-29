package com.example.mynotesapp.feature_note.presentation.notes

import com.example.mynotesapp.feature_note.domain.module.Note
import com.example.mynotesapp.feature_note.domain.util.NoteOrder
import com.example.mynotesapp.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
